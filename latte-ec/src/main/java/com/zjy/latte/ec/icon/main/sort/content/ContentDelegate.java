package com.zjy.latte.ec.icon.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.net.RestClient;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 极速蜗牛 on 2017/8/20 0020.
 */

public class ContentDelegate extends LatteDelegate {
    @BindView(R2.id.rv_content_list)
    RecyclerView mRecyclerView = null;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    private List<SectionBean> mData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void initData() {
        RestClient.builder()
                .url("http://116.196.94.185:8080/testapi/sort_content_data_1.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String respone) {
                        mData = new SectionDataConverter().convert(respone);
                        final SectionAdapter adapter = new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
