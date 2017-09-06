package com.zjy.latte.ec.icon.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjy.latte.app.recycler.DataConverter;
import com.zjy.latte.app.recycler.ItemType;
import com.zjy.latte.app.recycler.MultipleFields;
import com.zjy.latte.app.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by 极速蜗牛 on 2017/8/21 0021.
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.Builder()
                    .setItemField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setItemField(MultipleFields.ID, id)
                    .setItemField(MultipleFields.TEXT, name)
                    .setItemField(MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG, true);

        }
        return dataList;
    }
}
