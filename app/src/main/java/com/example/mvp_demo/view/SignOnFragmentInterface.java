package com.example.mvp_demo.view;

import java.util.Map;

/**
 * Created by hasee on 2016/8/23.
 */
public interface SignOnFragmentInterface {

    Map<String, Object> getSignOnInfo();
    void showCheckError(String result);
    void showErrorByToast(String result);
    void showProgressDialog();
    void  dismissProgressDialog();
    void gotoSignInView();
}
