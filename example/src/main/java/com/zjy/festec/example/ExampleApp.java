package com.zjy.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zjy.latte.app.app.Latte;
import com.zjy.latte.app.net.Interceptors.DebugInterceptor;
import com.zjy.latte.ec.icon.database.DatabaseManager;
import com.zjy.latte.ec.icon.icon.FontEcModule;

/**
 * Created by 极速蜗牛 on 2017/8/2 0002.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        init();
        DatabaseManager.getInstance().init(this);
    }

    private void init() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
