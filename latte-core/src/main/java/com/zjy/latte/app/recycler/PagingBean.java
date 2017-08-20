package com.zjy.latte.app.recycler;

/**
 * Created by 极速蜗牛 on 2017/8/17 0017.
 */

public final class PagingBean {
    //当前第几页
    private int mPageIndex = 0;
    //总数居条数
    private int mTotal = 0;
    //一页显示几条数据
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCrrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PagingBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PagingBean setmTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PagingBean setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCrrentCount() {
        return mCrrentCount;
    }

    public PagingBean setmCrrentCount(int mCrrentCount) {
        this.mCrrentCount = mCrrentCount;
        return this;
    }

    public int getmDelayed() {
        return mDelayed;
    }

    public PagingBean setmDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    public PagingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
