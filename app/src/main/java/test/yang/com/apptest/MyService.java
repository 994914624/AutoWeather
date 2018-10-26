package test.yang.com.apptest;



import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import test.yang.com.apptest.util.AlarmManagerUtil;


/**
 * Created by yzk on 2018/10/18
 */

public class MyService extends Service {


    private static String TAG="MyService";
    private DynamicReceiver dynamicReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dynamicReceiver= new DynamicReceiver();
        IntentFilter filter= new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(dynamicReceiver,filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(dynamicReceiver);
        // 在此重新启动,使服务常驻内存
        startService(new Intent(this, MyService.class));
    }

    static class DynamicReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "DynamicReceiver onReceive");
            if("test.yang.com.apptest.alarm".equals(intent.getAction())) {

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
    }
}
