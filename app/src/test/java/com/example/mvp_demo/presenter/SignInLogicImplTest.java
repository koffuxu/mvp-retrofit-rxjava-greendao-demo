package com.example.mvp_demo.presenter;

import com.example.mvp_demo.view.SignInFragmentInterface;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Steven Sun on 2016/9/8.
 * Email:tyhj_sf@163.com
 */
public class SignInLogicImplTest {
    private  SignInLogicImpl signInLogic;
    private SignInFragmentInterface signInFragmentInterface =new SignInFragmentInterface() {
        @Override
        public void setSignInLogic(SignInLogic signInLogic) {

        }

        @Override
        public SignInLogic getSignInLogic() {
            return null;
        }

        @Override
        public void showProgressDialog() {

        }

        @Override
        public void dismissProgressDialog() {

        }

        @Override
        public void showAuthorityErrorToast() {

        }

        @Override
        public void showCheckErrorToast(String error) {

        }
    };
    @Before
    public void setUp() throws Exception {
        signInLogic=new SignInLogicImpl();
        signInLogic.setSignInFragmentInterface(signInFragmentInterface);
    }

    @Test
    public void testGetSignInInterface() throws Exception {

    }

    @Test
    public void testSetSignInInterface() throws Exception {

    }

    @Test
    public void testAuthority() throws Exception {

    }

    @Test
    public void testCheckInput() throws Exception {
        assertFalse("case11",signInLogic.checkInput("se","123"));
        assertFalse("case12",signInLogic.checkInput("sewerr","123"));
        assertFalse("case13",signInLogic.checkInput("sesdfsdfdf","123"));
        assertTrue("case14",signInLogic.checkInput("sefswewere","123dfefewfdf"));
        assertFalse("case15",signInLogic.checkInput("","123"));
        assertFalse("case16",signInLogic.checkInput("","123sfdfd"));
        assertFalse("case17",signInLogic.checkInput("sesfdfwerer",""));
        assertFalse("case18",signInLogic.checkInput("sesfdfwerer","              "));
        assertFalse("case19",signInLogic.checkInput("          ","sferwferewrer"));
    }
}