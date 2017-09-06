package com.zjy.latte.ec.icon.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 极速蜗牛 on 2017/8/21 0021.
 */

public class SectionDataConverter {
    final List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json)
                .getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setmId(id);
            sectionTitleBean.setmIsMore(false);
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contenItem = goods.getJSONObject(j);
                final int goodsId = contenItem.getInteger("goods_id");
                final String goodsName = contenItem.getString("goods_name");
                final String goodsThumb = contenItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setmGoodsId(goodsId);
                itemEntity.setmGoodsName(goodsName);
                itemEntity.setmGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));

            }
            //商品内容循环结术
        }
        //section循环结束
        return dataList;
    }
}
