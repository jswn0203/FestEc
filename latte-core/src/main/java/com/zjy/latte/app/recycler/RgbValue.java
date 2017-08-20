package com.zjy.latte.app.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by 极速蜗牛 on 2017/8/18 0018.
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }
}
