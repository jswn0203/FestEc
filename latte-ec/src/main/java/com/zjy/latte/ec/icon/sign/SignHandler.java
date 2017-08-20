package com.zjy.latte.ec.icon.sign;

import com.alibaba.fastjson.JSON;
import com.zjy.latte.app.app.AccountManager;
import com.zjy.latte.ec.icon.database.DatabaseManager;
import com.zjy.latte.ec.icon.database.UserProfile;

import org.json.JSONObject;

/**
 * Created by 极速蜗牛 on 2017/8/10 0010.
 */

public class SignHandler {
    public static void onSignUp(String respone, ISignListener signListener) {
        final com.alibaba.fastjson.JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");
        final long useId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(useId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String respone, ISignListener signListener) {
        final com.alibaba.fastjson.JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");
        final long useId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(useId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

}
