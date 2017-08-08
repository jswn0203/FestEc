package com.zjy.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 极速蜗牛 on 2017/8/6 0006.
 */

public enum  EcIcons implements Icon {

    icon_scon('\ue606'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
