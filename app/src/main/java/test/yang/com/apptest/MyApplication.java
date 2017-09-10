package test.yang.com.apptest;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2017/7/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {


        initXunFeiVoice();
        super.onCreate();
    }

    private void initXunFeiVoice() {
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));
    }


    }
