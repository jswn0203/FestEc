package com.zjy.festec.example.generator;

import com.example.annotation.PayEntryGenertator;
import com.zjy.latte.app.wechat.WPayXEntryTemplate;
import com.zjy.latte.app.wechat.WXEntryTemplate;

/**
 * Created by 极速蜗牛 on 2017/8/13 0013.
 */
@PayEntryGenertator(
        packageName = "com.zjy.festec.example",
        payEntryTemplete = WPayXEntryTemplate.class
)
public interface WeChatPayEntry {
}
