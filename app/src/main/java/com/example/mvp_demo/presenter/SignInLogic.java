package com.example.mvp_demo.presenter;

import com.example.mvp_demo.view.SignInFragmentInterface;

/**
 * Created by Steven Sun on 2016/9/3.
 * Email:tyhj_sf@163.com
 */
public interface SignInLogic {

    SignInFragmentInterface getSignInFragmentInterface();
    void setSignInFragmentInterface(SignInFragmentInterface signInFragmentInterface);
    void authority(String userName, String password);

}
