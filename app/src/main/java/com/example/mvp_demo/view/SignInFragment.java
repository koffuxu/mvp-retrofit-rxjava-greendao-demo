package com.example.mvp_demo.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_demo.R;
import com.example.mvp_demo.activity.SignOnActivity;
import com.example.mvp_demo.presenter.SignInLogic;

/**
 * Created by Steven Sun on 2016/9/3.
 * Email:tyhj_sf@163.com
 */
public class SignInFragment extends Fragment implements SignInFragmentInterface {
    private SignInLogic SignInLogic;
    private ImageView returnBack;
    private Button signIn;
    private EditText username;
    private EditText password;
    private TextView gotoRegister;
    private  ProgressDialog progressDialog;

    public SignInFragment() {
    }

    public void setSignInLogic(SignInLogic signInLogic) {
        this.SignInLogic = signInLogic;
    }

    public SignInLogic getSignInLogic() {
        return SignInLogic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        returnBack = (ImageView) view.findViewById(R.id.login_back);
        signIn = (Button) view.findViewById(R.id.login_login);
        username=(EditText)view.findViewById(R.id.login_name);
        password=(EditText)view.findViewById(R.id.login_password);
        gotoRegister=(TextView)view.findViewById(R.id.login_register);
       //注册事件监听
        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInLogic.authority(username.getText().toString(),password.getText().toString());
            }
        });
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), SignOnActivity.class));
            }
        });
        progressDialog = new ProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showProgressDialog() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在登陆....");
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showAuthorityErrorToast() {
        Toast.makeText(getActivity(),"账号或密码错误，请检查！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCheckErrorToast(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }
}
