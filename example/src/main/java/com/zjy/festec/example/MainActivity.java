package com.zjy.festec.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjy.latte.app.activitys.ProxyAcctivity;
import com.zjy.latte.app.delegate.LatteDelegate;

public class MainActivity extends ProxyAcctivity {

    @Override
    public LatteDelegate setRootDelegare() {
        return new demoDetegate();
    }
}
