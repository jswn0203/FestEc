package com.zjy.latte.ec.icon.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.recycler.ItemType;
import com.zjy.latte.app.recycler.MultipleFields;
import com.zjy.latte.app.recycler.MultipleItemEntity;
import com.zjy.latte.app.recycler.MultipleRecyclerAdapter;
import com.zjy.latte.app.recycler.MultipleViewHolder;
import com.zjy.latte.ec.R;
import com.zjy.latte.ec.icon.main.sort.SortDelegate;
import com.zjy.latte.ec.icon.main.sort.content.ContentDelegate;

import java.util.List;

/**
 * Created by 极速蜗牛 on 2017/8/21 0021.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getField(MultipleFields.TEXT);
                final boolean isClicked = item.getField(MultipleFields.TAG);
                final AppCompatTextView name = helper.getView(R.id.tv_vertical_item_name);
                final View line = helper.getView(R.id.view_line);
                final View itemView = helper.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = helper.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的
                            item.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_blackground));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                helper.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
