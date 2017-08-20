package com.zjy.latte.ec.icon.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.net.RestClient;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 极速蜗牛 on 2017/8/10 0010.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("192.168.2.196:8088/testapi/user_profile.json")
//                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String respone) {
                            SignHandler.onSignUp(respone, mISignListener);
                            Toast.makeText(getContext(), "验证通过"+respone, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
            mISignListener.onSignInSuccess();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;

    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
