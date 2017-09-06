package com.zjy.latte.ec.icon.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 极速蜗牛 on 2017/8/21 0021.
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId=-1;

    public boolean ismIsMore() {
        return mIsMore;
    }

    public void setmIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }



}
