package test.yang.com.apptest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.StateSet;

import test.yang.com.apptest.util.AlarmManagerUtil;


/**
 * Created by yang on 2017/9/10
 */

public class MyAlarmReceiver extends BroadcastReceiver {

    private static String TAG="MyAlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"onReceive");
        String msg = intent.getStringExtra("msg");
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        if (intervalMillis != 0) {
            AlarmManagerUtil.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        }
        int flag = intent.getIntExtra("soundOrVibrator", 0);
        //收到广播后启动页面
        Intent clockIntent = new Intent(context, TtsMainActivity.class);
        clockIntent.putExtra("msg", msg);
        clockIntent.putExtra("flag", flag);
        clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(clockIntent);
    }
}
