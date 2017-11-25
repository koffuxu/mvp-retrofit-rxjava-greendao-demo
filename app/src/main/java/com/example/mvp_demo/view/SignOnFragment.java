package com.example.mvp_demo.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp_demo.R;
import com.example.mvp_demo.activity.SignInActivity;
import com.example.mvp_demo.presenter.SignInLogic;
import com.example.mvp_demo.presenter.SignInLogicImpl;
import com.example.mvp_demo.presenter.SignOnLogic;

import java.util.HashMap;
import java.util.Map;

public class SignOnFragment extends android.app.Fragment implements SignOnFragmentInterface {

    private EditText mUsername;
    private EditText mUserPassword1;
    private EditText mUserPassword2;
    private EditText mEmailAddress;
    private Button mRegister;
    private ProgressDialog progressDialog;

    private SignOnLogic signOnLogicInterface;

    /**供系统调用*/
    public SignOnFragment() {
        // Required empty public constructor
    }

    public static SignOnFragment newInstance(){
        return new SignOnFragment();
    }

    public SignOnLogic getSignOnLogicInterface() {
        return signOnLogicInterface;
    }

    public void setSignOnLogicInterface(SignOnLogic signOnLogicInterface) {
        this.signOnLogicInterface = signOnLogicInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_on, container, false);
        mUsername=(EditText) view.findViewById(R.id.register_name);
        mEmailAddress=(EditText) view.findViewById(R.id.register_email);
        mUserPassword1=(EditText)view.findViewById(R.id.register_password1);
        mUserPassword2=(EditText)view.findViewById(R.id.register_password2);
        mRegister =(Button)view.findViewById(R.id.register_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOnLogicInterface.authority(getSignOnInfo());
            }
        });
        progressDialog=new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onDestroy() {
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    void updateView(Map<String, Object> map){

    }

    @Override
    public Map<String, Object> getSignOnInfo(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("mUserName",mUsername.getText().toString());
        map.put("mUserPassword1",mUserPassword1.getText().toString());
        map.put("mUserPassword2",mUserPassword2.getText().toString());
        map.put("mEmailAddress",mEmailAddress.getText().toString());
        return map;
    }

    @Override
    public void showCheckError(String result) {
        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorByToast(String result) {
        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在注册....");
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void gotoSignInView() {
        getActivity().startActivity(new Intent(getActivity(), SignInActivity.class));
//        SignInFragment signInFragment=new SignInFragment();
//        SignInLogic signInLogic=new SignInLogicImpl();
//        signInLogic.setSignInFragmentInterface(signInFragment);
//        signInFragment.setSignInLogic(signInLogic);
//        getActivity()
//                .getFragmentManager()
//                .beginTransaction()
//                .remove(getFragmentManager().findFragmentByTag("SignOnFragment"))
//                .add(R.id.activity_fragment_container_sign_on,signInFragment,"SignInFragment")
//                .commit();


    }
}
