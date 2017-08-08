package com.zjy.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 极速蜗牛 on 2017/8/2 0002.
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatieConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APLICATION_CONTEXT.name());
    }

}
