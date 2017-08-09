package com.zjy.latte.app.net;

import com.zjy.latte.app.net.callback.IError;
import com.zjy.latte.app.net.callback.IFailure;
import com.zjy.latte.app.net.callback.IRequest;
import com.zjy.latte.app.net.callback.ISuccess;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 极速蜗牛 on 2017/8/6 0006.
 * 建造者模式
 */

public class RestClientBuilder {
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    private File mFile;

    private String mDownLoadDir;
    private String mExtension;
    private String mName;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder downLoadDir(String downLoadDir) {
        this.mDownLoadDir = downLoadDir;
        return this;
    }
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl,
                PARAMS,
                mIRequest,
                mDownLoadDir, mExtension, mName, mISuccess,
                mIFailure,
                mIError,
                mBody, mFile);
    }

}
