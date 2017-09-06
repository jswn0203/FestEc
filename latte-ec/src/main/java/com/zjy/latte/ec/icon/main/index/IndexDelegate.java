package com.zjy.latte.ec.icon.main.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.zjy.latte.app.delegate.bottom.BottomItemDelegate;
import com.zjy.latte.app.recycler.BaseDecoration;
import com.zjy.latte.app.recycler.PagingBean;
import com.zjy.latte.ec.icon.main.EcBottomDelegate;
import com.zjy.latte.ec.icon.refresh.RefreshHandler;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by 极速蜗牛 on 2017/8/14 0014.
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolBar = null;
    @BindView(R2.id.icon_index_sacn)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        /**
         * true 为有大到小出现
         * 120，300圈圈的位置
         */
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://116.196.94.185:8080/testapi/index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.creat(mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter(), new PagingBean());

    }
}
