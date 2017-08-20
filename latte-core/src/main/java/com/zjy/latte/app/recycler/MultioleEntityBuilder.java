package com.zjy.latte.app.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by 极速蜗牛 on 2017/8/16 0016.
 */

public class MultioleEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    MultioleEntityBuilder() {
        //清除之前的数据
        FIELDS.clear();
    }

    public final MultioleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }


    public final MultioleEntityBuilder setItemField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultioleEntityBuilder setItemFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);

    }


}
