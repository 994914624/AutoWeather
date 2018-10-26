package test.yang.com.apptest;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.iflytek.cloud.SpeechUtility;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2017/7/11.
 */

public class MyApplication extends Application {

    private static String TAG="MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        initService();
        initXunFeiVoice();
        initUpdateVersion();
    }

    /**
     * keep live
     */
    private void initService() {
        startService(new Intent(this, MyService.class));
    }

    private void initXunFeiVoice() {
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));
    }


    /**
     * update
     */
    private static String REQUEST_URL ="";
    private void initUpdateVersion() {

        AllenVersionChecker
                .getInstance()
                .requestVersion()
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        Log.d(TAG,"onRequestVersionSuccess");
                        //如果是最新版本直接return null
                        String downloadUrl="https://download.fir.im/apps/5bcef2a6548b7a1d2506b098/install?download_token=b0b3e75ca6f1aa8ab8b1023870c6a526&release_id=5bd01037ca87a803533965a7";
                        return UIData.create().setDownloadUrl(downloadUrl);
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {

                        Log.d(TAG,"onRequestVersionFailure");
                    }
                });
    }



}
