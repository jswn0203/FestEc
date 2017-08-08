package com.zjy.latte.app.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.zjy.latte.R;
import com.zjy.latte.app.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 极速蜗牛 on 2017/8/3 0003.
 */

public abstract class ProxyAcctivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegare();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout contenter = new ContentFrameLayout(this);
        contenter.setId(R.id.delegate_container);
        setContentView(contenter);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegare());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
