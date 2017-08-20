package com.zjy.latte.ec.icon.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zjy.latte.ec.icon.database.DaoMaster.OpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 极速蜗牛 on 2017/8/10 0010.
 */

public class ReleaseOpenHelper extends OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
