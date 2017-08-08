package com.zjy.festec.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zjy.latte.app.ConfigType;
import com.zjy.latte.app.Latte;
import com.zjy.latte.app.delegate.LatteDelegate;
import com.zjy.latte.app.net.RestClient;
import com.zjy.latte.app.net.RestClientBuilder;
import com.zjy.latte.app.net.RestCreator;
import com.zjy.latte.app.net.RestService;
import com.zjy.latte.app.net.callback.IError;
import com.zjy.latte.app.net.callback.IFailure;
import com.zjy.latte.app.net.callback.IRequest;
import com.zjy.latte.app.net.callback.ISuccess;

/**
 * Created by 极速蜗牛 on 2017/8/4 0004.
 */

public class demoDetegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        TextView textView = new TextView(getContext());
        textView.setText("111111111");
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
//        String host = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
//        Toast.makeText(getContext(),host, Toast.LENGTH_SHORT).show();

    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String respone) {
                        Toast.makeText(getContext(), respone, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
