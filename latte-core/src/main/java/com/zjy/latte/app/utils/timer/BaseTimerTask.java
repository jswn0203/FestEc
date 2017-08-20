package com.zjy.latte.app.utils.timer;

import java.util.TimerTask;

/**
 * Created by 极速蜗牛 on 2017/8/9 0009.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mItimerListener = null;

    public BaseTimerTask(ITimerListener mItimerListener) {
        this.mItimerListener = mItimerListener;
    }

    @Override
    public void run() {
        if (mItimerListener!=null){
            mItimerListener.onTimer();
        }
    }
}
