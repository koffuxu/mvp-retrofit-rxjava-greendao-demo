package com.example.mvp_demo.presenter;

import android.util.Log;

import com.example.mvp_demo.MyApplication;
import com.example.mvp_demo.model.Server;
import com.example.mvp_demo.model.User;
import com.example.mvp_demo.utility.GreenDaoManager;
import com.example.mvp_demo.view.SignOnFragmentInterface;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Steven Sun on 2016/8/24.
 * Email:tyhj_sf@163.com
 */
public class SignOnLogicImpl implements SignOnLogic {

    public SignOnFragmentInterface signOnFragmentInterface;

    public SignOnLogicImpl(SignOnFragmentInterface signOnFragmentInterface) {
        this.signOnFragmentInterface = signOnFragmentInterface;
    }

    public void setSignOnFragmentInterface(SignOnFragmentInterface signOnFragmentInterface) {
        this.signOnFragmentInterface = signOnFragmentInterface;
    }

    public SignOnFragmentInterface getSignOnFragmentInterface() {
        return signOnFragmentInterface;
    }

    protected boolean checkInput(Map<String, Object> userInfoMap) {
        String inputInfo = userInfoMap.get("mUserName").toString();
        String checkResult = null;
        if (inputInfo.trim().length() == 0) {
            checkResult = "用户名不能为空";
        } else if (inputInfo.length() < 6) {
            checkResult = "用户名太短";
        } else if (inputInfo.length() > 30) {
            checkResult = "用户名太长";
        } else if (!userInfoMap.get("mEmailAddress").toString().matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            checkResult = "邮箱格式错误，请检查";
        } else if (userInfoMap.get("mUserPassword1").toString().length() < 6) {
            checkResult = "密码太短";
        } else if (userInfoMap.get("mUserPassword1").toString().length() > 30) {
            checkResult = "密码太长";
        } else if (!userInfoMap.get("mUserPassword1").toString().equals(userInfoMap.get("mUserPassword2").toString())) {
            checkResult = "两次输入的密码不一致，请重新输入";
        }
        if (checkResult != null) {
            signOnFragmentInterface.showCheckError(checkResult);
            return false;
        }
        return true;
    }

    @Override
    public void authority(Map<String, Object> userInfoMap) {
//        boolean isSuccess=false;
        if (!checkInput(userInfoMap)) {
            return ;
        }
        final User user = new User();
        user.mUsername = userInfoMap.get("mUserName").toString();
        user.mUserPassword = userInfoMap.get("mUserPassword1").toString();
        user.mEmailAddress = userInfoMap.get("mEmailAddress").toString();
        signOnFragmentInterface.showProgressDialog();

        new Retrofit.Builder()
                .baseUrl(MyApplication.BASIC_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Server.class)
                .uploadUser(user)   //上传注册信息
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (s.equals("success")) {
                            //保存用户注册信息到客户端数据库
                            GreenDaoManager
                                    .getInstance()
                                    .getmDaoSession()
                                    .getUserDao()
                                    .insert(user);
                            Log.d("数据库测试", "保存user成功");
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        signOnFragmentInterface.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        signOnFragmentInterface.dismissProgressDialog();
                        signOnFragmentInterface.showErrorByToast("网络发生错误：" + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("success")) {
                            //界面跳转到
                            signOnFragmentInterface.gotoSignInView();
                        } else {
                            signOnFragmentInterface.showErrorByToast("服务端响应错误");
                        }
                    }
                });
    }
}
