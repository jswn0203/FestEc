package com.zjy.latte.app.net.Interceptors;

import android.support.annotation.RawRes;

import com.zjy.latte.app.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 极速蜗牛 on 2017/8/9 0009.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }


    private Response getRespone(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugRespone(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getRespone(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
//        if (url.contains(DEBUG_URL)) {
//            return debugRespone(chain, DEBUG_RAW_ID);
//        }
        return chain.proceed(chain.request());
    }
}
