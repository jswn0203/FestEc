package com.zjy.latte.app.app;

import android.content.Context;

/**
 * Created by 极速蜗牛 on 2017/8/2 0002.
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatieConfigs()
                .put(ConfigType.APLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APLICATION_CONTEXT);
    }
}
