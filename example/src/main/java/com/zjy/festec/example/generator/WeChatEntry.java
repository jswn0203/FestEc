package com.zjy.festec.example.generator;

import com.example.annotation.EntryGenerator;
import com.zjy.latte.app.wechat.WXEntryTemplate;

/**
 * Created by 极速蜗牛 on 2017/8/13 0013.
 */
@EntryGenerator(
        packageName = "com.zjy.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
