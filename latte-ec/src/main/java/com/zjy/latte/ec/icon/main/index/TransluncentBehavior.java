package com.zjy.latte.ec.icon.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.zjy.latte.app.recycler.RgbValue;
import com.zjy.latte.ec.R;

/**
 * Created by 极速蜗牛 on 2017/8/18 0018.
 */

public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部距离
    private int mDistancY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;
    //定义颜色
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);

    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //增加滑动距离
        mDistancY += dy;
        //toolbar高
        final int tHeight = child.getBottom();

        if (mDistancY>0 && mDistancY <=tHeight){
            final float scale = (float)mDistancY/tHeight;
            final float alpha = scale*255;
            child.setBackgroundColor(Color.argb((int) alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }else if (mDistancY>tHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }
    }
}
