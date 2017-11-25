package com.example.mvp_demo.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.mvp_demo.R;
import com.example.mvp_demo.presenter.SignOnLogicImpl;
import com.example.mvp_demo.view.SignOnFragment;

public class SignOnActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_on);

        SignOnFragment signOnFragment=SignOnFragment.newInstance();
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.add(R.id.activity_fragment_container_sign_on,signOnFragment,"SignOnFragment");
        transaction.commit();
        SignOnLogicImpl signOnLogic=new SignOnLogicImpl(signOnFragment);
        signOnFragment.setSignOnLogicInterface(signOnLogic);
    }
}
