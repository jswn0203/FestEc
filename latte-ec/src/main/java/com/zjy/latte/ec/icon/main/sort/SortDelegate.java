package com.zjy.latte.ec.icon.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zjy.latte.app.delegate.bottom.BottomItemDelegate;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.icon.main.sort.content.ContentDelegate;
import com.zjy.latte.ec.icon.main.sort.list.VerticalListDelegate;

/**
 * Created by 极速蜗牛 on 2017/8/14 0014.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, listDelegate);
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
