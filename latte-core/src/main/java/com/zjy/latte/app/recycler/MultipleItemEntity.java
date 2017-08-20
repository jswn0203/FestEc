package com.zjy.latte.app.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by 极速蜗牛 on 2017/8/16 0016.
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFRERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUENE);


    public MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFRERENCE.get().putAll(fields);
    }

    public static final MultioleEntityBuilder Builder() {
        return new MultioleEntityBuilder();
    }


    @Override
    public int getItemType() {
        return (int) FIELDS_REFRERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    public final <T> T getField(Object key) {
        return (T) FIELDS_REFRERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFRERENCE.get();
    }

    public final MultiItemEntity setField(Object key, Object value) {
        FIELDS_REFRERENCE.get().put(key, value);
        return this;
    }
}
