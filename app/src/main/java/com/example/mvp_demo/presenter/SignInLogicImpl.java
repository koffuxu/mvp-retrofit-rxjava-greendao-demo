package com.example.mvp_demo.presenter;

import android.util.Log;

import com.example.mvp_demo.MyApplication;
import com.example.mvp_demo.model.Server;
import com.example.mvp_demo.model.User;
import com.example.mvp_demo.utility.GreenDaoManager;
import com.example.mvp_demo.view.SignInFragmentInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Steven Sun on 2016/9/3.
 * Email:tyhj_sf@163.com
 */
public class SignInLogicImpl implements SignInLogic {
    private SignInFragmentInterface signInFragmentInterface;

    public SignInLogicImpl(){

    }

    public SignInLogicImpl(SignInFragmentInterface signInFragmentInterface) {
        this.signInFragmentInterface = signInFragmentInterface;
    }

    public SignInFragmentInterface getSignInFragmentInterface() {
        return signInFragmentInterface;
    }

    public void setSignInFragmentInterface(SignInFragmentInterface signInFragmentInterface) {
        this.signInFragmentInterface = signInFragmentInterface;
    }

    @Override
    public void authority(String username, String password) {
        if (checkInput(username, password)==false){
            return ;
        }
        //上传进度提示
        signInFragmentInterface.showProgressDialog();
        //上传数据
        new Retrofit.Builder()
                .baseUrl(MyApplication.BASIC_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Server.class)
                .queryUser(username,password)
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        if(user!=null) {
                            GreenDaoManager.getInstance()
                                    .getmDaoSession()
                                    .getUserDao()
                                    .insert(user);
                        }
                        Log.d("数据测试","user="+user);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        signInFragmentInterface.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        signInFragmentInterface.dismissProgressDialog();
                        signInFragmentInterface.showAuthorityErrorToast();
                    }

                    @Override
                    public void onNext(User user) {
                        //界面跳转至主界面

                    }
                });
        return ;
    }

    protected boolean checkInput(String username, String password){
        if (username.length()<6){
            signInFragmentInterface.showCheckErrorToast("用户名不能小于6个字符");
            return false;
        }else if (username.contains(" ")){
            signInFragmentInterface.showCheckErrorToast("用户名不能含有空格");
            return false;
        }else if (password.length()<6){
            signInFragmentInterface.showCheckErrorToast("密码不能小于6个字符");
            return false;
        }else if (password.contains(" ")){
            signInFragmentInterface.showCheckErrorToast("密码不能含有空格");
            return false;
        }
        return true;
    }
}
