package com.zjy.latte.app.app;

import com.zjy.latte.app.utils.storage.LattePreference;

/**
 * Created by 极速蜗牛 on 2017/8/11 0011.
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    //保存登录状态与否
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    ///判断是否登陆
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

}
