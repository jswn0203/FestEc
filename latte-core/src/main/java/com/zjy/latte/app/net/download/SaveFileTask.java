package com.zjy.latte.app.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.zjy.latte.app.app.Latte;
import com.zjy.latte.app.net.callback.IRequest;
import com.zjy.latte.app.net.callback.ISuccess;
import com.zjy.latte.app.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by 极速蜗牛 on 2017/8/8 0008.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];

        final InputStream is = body.byteStream();

        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstall(file);
    }

    private void autoInstall(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent insatll = new Intent();
            insatll.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            insatll.setAction(Intent.ACTION_VIEW);
            insatll.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(insatll);

        }
    }

}
