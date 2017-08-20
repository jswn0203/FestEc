package com.zjy.latte.ec.icon.main.index;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjy.latte.app.recycler.DataConverter;
import com.zjy.latte.app.recycler.ItemType;
import com.zjy.latte.app.recycler.MultipleFields;
import com.zjy.latte.app.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by 极速蜗牛 on 2017/8/16 0016.
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            Log.e("zjy","------------------------------\n");
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            if (imageUrl != null){
                Log.e("zjy",imageUrl+"-");
            }
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            Log.e("zjy",id+"-");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannersize = banners.size();
                for (int j = 0; j < bannersize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            Log.e("zjy","------------------------------\n");
            final MultipleItemEntity entity = MultipleItemEntity.Builder()
                    .setItemField(MultipleFields.ITEM_TYPE,type)
                    .setItemField(MultipleFields.SAPN_SIZE,spanSize)
                    .setItemField(MultipleFields.ID,id)
                    .setItemField(MultipleFields.TEXT,text)
                    .setItemField(MultipleFields.IMAGE_URL,imageUrl)
                    .setItemField(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);
            Log.e("zz", (String) entity.getField(MultipleFields.IMAGE_URL)+  "*********"+i);
        }

        return ENTITIES;
    }
}
