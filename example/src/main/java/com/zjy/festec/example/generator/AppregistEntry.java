package com.zjy.festec.example.generator;

import com.example.annotation.AppRegisterGenerator;
import com.zjy.latte.app.wechat.AppregistTemplate;

/**
 * Created by 极速蜗牛 on 2017/8/13 0013.
 */
@AppRegisterGenerator(
        packageName = "com.zjy.festec.example",
        registerTemplete = AppregistTemplate.class
)
public interface AppregistEntry {
}
