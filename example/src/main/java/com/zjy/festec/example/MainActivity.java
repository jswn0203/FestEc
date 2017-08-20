package com.zjy.festec.example;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zjy.latte.app.activitys.ProxyAcctivity;
import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.ui.Launcher.ILauncherListener;
import com.zjy.latte.app.ui.Launcher.OnLauncherFinishTag;
import com.zjy.latte.ec.icon.launcher.LauncherDelegate;
import com.zjy.latte.ec.icon.launcher.LauncherScrollDelegate;
import com.zjy.latte.ec.icon.main.EcBottomDelegate;
import com.zjy.latte.ec.icon.sign.ISignListener;
import com.zjy.latte.ec.icon.sign.SignInDelegate;
import com.zjy.latte.ec.icon.sign.SignUpDelegate;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyAcctivity implements ISignListener
        , ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegare() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束用户已经登陆", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束用户没登陆", Toast.LENGTH_SHORT).show();
                startWithPop(new SignUpDelegate());
                break;
            default:
                break;
        }
    }
}
