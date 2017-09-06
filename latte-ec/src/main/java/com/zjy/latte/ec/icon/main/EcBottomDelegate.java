package com.zjy.latte.ec.icon.main;

import android.graphics.Color;

import com.zjy.latte.app.delegate.bottom.BaseBottomDelegate;
import com.zjy.latte.app.delegate.bottom.BottomItemDelegate;
import com.zjy.latte.app.delegate.bottom.BottomTabBean;
import com.zjy.latte.app.delegate.bottom.ItemBuilder;
import com.zjy.latte.ec.icon.main.diecover.DiscoverDelegate;
import com.zjy.latte.ec.icon.main.index.IndexDelegate;
import com.zjy.latte.ec.icon.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by 极速蜗牛 on 2017/8/14 0014.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
