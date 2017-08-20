package com.zjy.latte.ec.icon.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjy.latte.app.app.Latte;
import com.zjy.latte.app.net.RestClient;
import com.zjy.latte.app.net.callback.IError;
import com.zjy.latte.app.net.callback.IFailure;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.app.recycler.DataConverter;
import com.zjy.latte.app.recycler.MultipleFields;
import com.zjy.latte.app.recycler.MultipleItemEntity;
import com.zjy.latte.app.recycler.MultipleRecyclerAdapter;
import com.zjy.latte.app.recycler.PagingBean;
import com.zjy.latte.ec.icon.main.index.IndexDataConverter;

import java.util.ArrayList;

/**
 * Created by 极速蜗牛 on 2017/8/15 0015.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    private final PagingBean BEAAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    public static RefreshHandler creat(SwipeRefreshLayout refreshLayout,
                                       RecyclerView recyclerView,
                                       DataConverter converter, PagingBean bean) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, bean);
    }


    public RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);

    }


    public void firstPage(String url) {
        BEAAN.setmDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String respone) {
                        final JSONObject object = JSON.parseObject(respone);
                        BEAAN.setmTotal(object.getInteger("total"))
                                .setmPageSize(object.getInteger("page_size"));
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(respone));
//                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
