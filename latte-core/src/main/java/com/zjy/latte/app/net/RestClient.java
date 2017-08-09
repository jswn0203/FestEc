package com.zjy.latte.app.net;

import com.zjy.latte.app.net.callback.IError;
import com.zjy.latte.app.net.callback.IFailure;
import com.zjy.latte.app.net.callback.IRequest;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.app.net.callback.RequestCallbacks;
import com.zjy.latte.app.net.download.DownLoadHandler;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

/**
 * Created by 极速蜗牛 on 2017/8/6 0006.
 */

public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      String download_dir, String extension, String name, ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body, File file) {
        this.URL = url;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.FILE = file;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;

            case POST:
                call = service.post(URL, PARAMS);
                break;

            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;

            case PUT:
                call = service.put(URL, PARAMS);
                break;

            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;

            case DELETE:
                call = service.delete(URL, PARAMS);
                break;

            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part
                        .createFormData("file", FILE.getName());

                call = RestCreator.getRestService().upload(URL, body);
                break;

            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST,
                SUCCESS,
                FAILURE,
                ERROR);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownLoadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
