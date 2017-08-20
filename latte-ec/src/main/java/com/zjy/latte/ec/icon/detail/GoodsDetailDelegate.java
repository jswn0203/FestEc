package com.zjy.latte.ec.icon.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 极速蜗牛 on 2017/8/18 0018.
 */

public class GoodsDetailDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
