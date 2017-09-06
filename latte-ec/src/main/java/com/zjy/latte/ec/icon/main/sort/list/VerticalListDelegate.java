package com.zjy.latte.ec.icon.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.net.RestClient;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.app.recycler.MultipleItemEntity;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.R2;
import com.zjy.latte.ec.icon.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 极速蜗牛 on 2017/8/20 0020.
 */

public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }


    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://116.196.94.185:8080/testapi/sort_list_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String respone) {
                        final List<MultipleItemEntity> data = new VerticalListDataConverter().setJsonData(respone)
                                .convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
