package com.example.mvp_demo.view;

import com.example.mvp_demo.presenter.SignInLogic;

/**
 * Created by Steven Sun on 2016/9/3.
 * Email:tyhj_sf@163.com
 */
public interface SignInFragmentInterface {

    void setSignInLogic(SignInLogic signInLogic);
    SignInLogic getSignInLogic();
    void showProgressDialog();
    void dismissProgressDialog();
    void showAuthorityErrorToast();
    void  showCheckErrorToast(String error);

}
