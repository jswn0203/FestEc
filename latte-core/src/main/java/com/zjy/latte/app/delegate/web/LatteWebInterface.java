package com.zjy.latte.app.delegate.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by 极速蜗牛 on 2017/9/5 0005.
 */

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }


}
