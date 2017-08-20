package com.zjy.latte.app.delegate;

/**
 * Created by 极速蜗牛 on 2017/8/3 0003.
 */

public abstract class LatteDelegate extends PermissionCheckDelegate {
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
