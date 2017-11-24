package test.yang.com.apptest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.TimePickerView;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.yang.com.apptest.Constant.URLConstant;
import test.yang.com.apptest.bean.Weather;
import test.yang.com.apptest.util.AlarmManagerUtil;
import test.yang.com.apptest.util.MyHttpURLUtils;

public class TtsMainActivity extends Activity implements OnClickListener {
    private static String TAG = TtsMainActivity.class.getSimpleName();
    // 语音合成对象
    private SpeechSynthesizer mTts;
    // 默认发音人
    private String voicer = "xiaoyan";
    // 云端/本地单选按钮
    private RadioGroup mRadioGroup;
    // 引擎类型 在线合成
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    private Toast mToast;
    //今天 明天 后天 大后天
    private ImageView img_today = null;
    private ImageView img_tomorrow = null;
    private ImageView img_nextDay = null;
    private ImageView img_nextnextDay = null;
    //时间选择器
    private TimePickerView timePickerView;
    private String pickTime;

    @SuppressLint("ShowToast")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ttsdemo);
        //权限申请
        requestPermissions();
        setStatusBarUpperAPI21();
        mToast=Toast.makeText(this,"",Toast.LENGTH_SHORT);

        //启动时尝试获取定位
        //gaodeLocation();
        //iniLocation();

        //初始化View
        initView();
        //时间选择器
        initTimePicker();

        //进来时时设置一次
        String pickTime=getTimeFromSharedPreference();
        Log.e(TAG,"::"+pickTime);
        if(TextUtils.isEmpty(pickTime)){
            //设置默认为19：30
            setAlarm(19,30);
        }else {
            showTip("设置播报时间为 "+pickTime);
                String[] times = pickTime.split(":");
                setAlarm(Integer.parseInt(times[0]),Integer.parseInt(times[1]));

        }

    }


    /**
     *高德定位
     */

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClientG = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListenerG = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {


            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    String city=aMapLocation.getCity();//城市信息
                    if(!TextUtils.isEmpty(city)){
                        Log.e(TAG,"gaode"+city);
                        //天气
                        getWeatherDatas(city);
                    }
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    showTip("gaode location error");
                    getWeatherDatas("郑州");
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOptionG = null;

    private void gaodeLocation() {
        //初始化AMapLocationClientOption对象
        mLocationOptionG = new AMapLocationClientOption();
        //初始化定位
        mLocationClientG = new AMapLocationClient(getApplicationContext());
        mLocationOptionG.setOnceLocation(true);
        //定位精度
        mLocationOptionG.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //给定位客户端对象设置定位参数
        mLocationClientG.setLocationOption(mLocationOptionG);

        //设置定位回调监听
        mLocationClientG.setLocationListener(mLocationListenerG);

        //启动定位
        mLocationClientG.startLocation();

    }


    /**
     * 时间选择器
     */
    private void initTimePicker() {
        timePickerView = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);

        //时间选择后回调
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                pickTime = new SimpleDateFormat("HH:mm").format(date);
                //保存在本地
                saveTimeToSharedPreference(pickTime);
                //拿到选择的时间后 去设置定时闹钟
                if(!TextUtils.isEmpty(pickTime)){
                    String[] times = pickTime.split(":");
                    setAlarm(Integer.parseInt(times[0]),Integer.parseInt(times[1]));
                }

            }
        });
    }

    /**
     * 定时闹钟 用于定时开启天气
     */
    private void setAlarm(int hour,int mimute){
        showTip("设置播报时间为："+hour+"："+mimute);
        AlarmManagerUtil.setAlarm(this.getApplicationContext(), 0, hour,mimute, 666, 0, "", 1);
    }

    private void cancelAlarm(){
        AlarmManagerUtil.cancelAlarm(this.getApplicationContext(),"",666);
    }

    /**
     * 初始化View
     */
    private void initView() {

        img_today = (ImageView) findViewById(R.id.img_today);
        img_tomorrow = (ImageView) findViewById(R.id.img_tomorrow);
        img_nextDay = (ImageView) findViewById(R.id.img_nextDay);
        img_nextnextDay = (ImageView) findViewById(R.id.img_nextnextDay);

        location_city = (TextView) findViewById(R.id.location_city);
        TextView tts_play= (TextView) findViewById(R.id.tts_play);
        tts_play.setOnClickListener(this);
        tts_play.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //展示时间选择器
                timePickerView.show();
                return false;
            }
        });


    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View view) {
        if (null == mTts) {
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            this.showTip("创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化");
            return;
        }
        Log.e(TAG,"onClick");
        switch (view.getId()) {
            case R.id.tts_play:
                if (readString != null) {
                    int code = mTts.startSpeaking(readString.toString(), mTtsListener);

                    if (code != ErrorCode.SUCCESS) {
                        showTip("语音合成失败,错误码: " + code);
                    }
                }else {
                    if(!TextUtils.isEmpty(location_city.getText())){
                        //点击时再次获取天气
                        getWeatherDatas(location_city.getText()+"");
                    }else{
                        getWeatherDatas("郑州");
                    }
                }
        }
    }

    /**
     * 设置天气图标
     */
    private void setImgIcon(String str, ImageView imageView) {
        if (str.contains("雷阵雨")) {
            updateUI(imageView, R.mipmap.thunder_rain);
        }
        else if(str.contains("多云转雷阵雨")) {
            updateUI(imageView, R.mipmap.cloud_then_rain);
        }

        else if (str.contains("多云")) {
            updateUI(imageView, R.mipmap.cloud);
        }  else if (str.contains("小雪")) {
            updateUI(imageView, R.mipmap.snow_little);
        } else if (str.contains("大雪")) {
            updateUI(imageView, R.mipmap.snow_big);
        }else if (str.contains("雨加雪")) {
            updateUI(imageView, R.mipmap.snow_and_rain);
        }
        else if(str.contains("晴")) {
            updateUI(imageView, R.mipmap.sun2);
        } else if (str.contains("阴")) {
            updateUI(imageView, R.mipmap.overcast);
        }
        else if (str.contains("雨") ) {
            updateUI(imageView, R.mipmap.rain);
        }

    }

    /**
     * 更新天气图标UI
     */
    private void updateUI(final ImageView imageView, final int res) {
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(res);
            }
        });

    }

    /**
     * 定位Listener
     */
    private TextView location_city = null;
    private LocationClient mLocationClient = null;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private StringBuilder readString = null;
    private BDLocationListener mLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            String city = bdLocation.getCity();
            if (TextUtils.isEmpty(city)) {
                //默认城市
                city = "郑州";
            }
            getWeatherDatas(city);

        }

    };

    /**
     * 发起定位
     */
    private void iniLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //定位参数设置
        initLocationParams();
        //注册监听函数
        mLocationClient.start();
        Log.e(TAG,"mLocationClient.restart()");
        mLocationClient.restart();

        mLocationClient.unRegisterLocationListener(mLocationListener);
        mLocationClient.registerLocationListener(mLocationListener);


    }

    /**
     * 定位参数
     */
    private void initLocationParams() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * Tts Listener
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败,错误码：" + code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    /**
     * tts语音合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            showTip("开始播放");
        }

        @Override
        public void onSpeakPaused() {
            showTip("暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            showTip("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            // 合成进度

        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成");
            } else if (error != null) {
                showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
        }
    };


    /**
     * 初始化tts语音 及参数设置
     *
     *
     */
    private void initTtsParam() {
        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(TtsMainActivity.this, mTtsInitListener);
        if (mTts != null) {
            // 清空参数
            mTts.setParameter(SpeechConstant.PARAMS, null);
            // 根据合成引擎设置相应参数
            if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
                mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
                // 设置在线合成发音人
                mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
                //设置合成语速
                mTts.setParameter(SpeechConstant.SPEED, "40");
                //设置合成音调
                mTts.setParameter(SpeechConstant.PITCH, "50");
                //设置合成音量
                mTts.setParameter(SpeechConstant.VOLUME, "70");
            } else {
                mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
                // 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
                mTts.setParameter(SpeechConstant.VOICE_NAME, "");
                /**
                 * TODO 本地合成不设置语速、音调、音量，默认使用语记设置
                 * 开发者如需自定义参数，请参考在线合成参数设置
                 */
            }
            //设置播放器音频流类型
            mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
            // 设置播放合成音频打断音乐播放，默认为true
            mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

            // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
            // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
            mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
            mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
        }
    }

    /**
     * Toast提示
     */
    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 权限申请
     */
    private void requestPermissions() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                int permission2 = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);
                if (permission != PackageManager.PERMISSION_GRANTED && permission2 != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.LOCATION_HARDWARE, Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_SETTINGS, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_CONTACTS}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 隐藏状态栏
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            //| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//        }
    }

    @TargetApi(21)
    private void setStatusBarUpperAPI21(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        //定位
        //iniLocation();
        gaodeLocation();
        //语音播报天气
        initTtsParam();
        if (readString != null) {

            mTts.startSpeaking(readString.toString(), mTtsListener);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mTts) {
            mTts.stopSpeaking();
            // 退出时释放连接
            mTts.destroy();
        }
        if (mLocationClient != null) {

            mLocationClient.unRegisterLocationListener(mLocationListener);
            mLocationClient.stop();
        }
    }

    /**
     * 保存设置的时间到本地
     */
    private void saveTimeToSharedPreference(String strTime){
        SharedPreferences sharedPreferences= getSharedPreferences("test.yang.com.apptest",
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("strTime", strTime);
        editor.commit();
    }

    /**
     * 从本地读取设置的时间
     *
     */
    private String getTimeFromSharedPreference(){

        SharedPreferences sharedPreferences= getSharedPreferences("test.yang.com.apptest",
                Activity.MODE_PRIVATE);
        String strTime =sharedPreferences.getString("strTime", "");
        return strTime;
    }

    /**
     * 获取天气数据
     */
    private void getWeatherDatas(final String city) {
        try {
            //设置城市
            location_city.setText(city+"");

            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {

                    //获取天气信息一
                    String weatherResult = MyHttpURLUtils.getNetStrings(URLConstant.URL_WEATHER_CITY_NAME + city);
                    //获取天气信息二
//                    String weatherResult2 = MyHttpURLUtils.getNetStrings(URLConstant.URL_WEATHER_CITY_NAME + city);
//                    Log.e(TAG,weatherResult2.toString()+"");
                    if (TextUtils.isEmpty(weatherResult)) {
                        return;
                    }
                    //天气一
                    getWeather_1(weatherResult);

                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     *天气1数据
     */
    private void getWeather_1(String weatherResult) {
        //解析天气信息
        Weather weather = JSON.parseObject(weatherResult, Weather.class);
        if (weather != null) {
            List listForecast = weather.getData().getForecast();

            Weather.DataBean.ForecastBean bean = (Weather.DataBean.ForecastBean) listForecast.get(0);
            readString = new StringBuilder();


            //今天天气
            readString.append("今天 " + bean.getDate().substring(0, bean.getDate().length() - 4) + "号 ");
            readString.append(bean.getDate().substring(bean.getDate().length() - 3, bean.getDate().length()) + "   ");
            readString.append("  天气 " + bean.getType() + "   ");
            readString.append(bean.getFengxiang() + "  " + bean.getFengli().substring(bean.getFengli().length() - 5, bean.getFengli().length() - 3) + "   ");
            readString.append("最低气温  " + bean.getLow().substring(3, bean.getLow().length()) + "  ");
            readString.append("最高气温  " + bean.getHigh().substring(3, bean.getHigh().length()) + "   ");
            //设置天气图标
            setImgIcon(bean.getType().toString(), img_today);

            //明天天气
            readString.append("          ");
            Weather.DataBean.ForecastBean bean2 = (Weather.DataBean.ForecastBean) listForecast.get(1);
            readString.append("明天 " + bean2.getDate().substring(0, bean2.getDate().length() - 4) + "号 ");
            readString.append(bean2.getDate().substring(bean2.getDate().length() - 3, bean2.getDate().length()) + "   ");
            readString.append("  天气 " + bean2.getType() + "   ");
            readString.append(bean2.getFengxiang() + "  " + bean2.getFengli().substring(bean2.getFengli().length() - 5, bean2.getFengli().length() - 3) + "   ");
            readString.append("最低气温  " + bean2.getLow().substring(3, bean2.getLow().length()) + "  ");
            readString.append("最高气温  " + bean2.getHigh().substring(3, bean2.getHigh().length()) + "   ");
            //设置天气图标
            setImgIcon(bean2.getType().toString(), img_tomorrow);

            //后天天气
            readString.append("          ");
            Weather.DataBean.ForecastBean bean3 = (Weather.DataBean.ForecastBean) listForecast.get(2);
            readString.append("后天 " + bean3.getDate().substring(0, bean3.getDate().length() - 4) + "号 ");
            readString.append(bean3.getDate().substring(bean3.getDate().length() - 3, bean3.getDate().length()) + "   ");
            readString.append("  天气 " + bean3.getType() + "   ");
            readString.append(bean3.getFengxiang() + "  " + bean3.getFengli().substring(bean3.getFengli().length() - 5, bean3.getFengli().length() - 3) + "   ");
            readString.append("最低气温  " + bean3.getLow().substring(3, bean3.getLow().length()) + "  ");
            readString.append("最高气温  " + bean3.getHigh().substring(3, bean3.getHigh().length()) + "   ");
            //设置天气图标
            setImgIcon(bean3.getType().toString(), img_nextDay);


            //大后天天气
            readString.append("          ");
            Weather.DataBean.ForecastBean bean4 = (Weather.DataBean.ForecastBean) listForecast.get(3);
            readString.append("大后天 " + bean4.getDate().substring(0, bean4.getDate().length() - 4) + "号 ");
            readString.append(bean4.getDate().substring(bean4.getDate().length() - 3, bean4.getDate().length()) + "   ");
            readString.append("  天气 " + bean4.getType() + "   ");
            readString.append(bean4.getFengxiang() + "  " + bean4.getFengli().substring(bean4.getFengli().length() - 5, bean4.getFengli().length() - 3) + "   ");
            readString.append("最低气温  " + bean4.getLow().substring(3, bean4.getLow().length()) + "  ");
            readString.append("最高气温  " + bean4.getHigh().substring(3, bean4.getHigh().length()) + "   ");
            //设置天气图标
            setImgIcon(bean4.getType().toString(), img_nextnextDay);

            Log.e(TAG, readString.toString()+"");
            if (mTts != null) {
                mTts.startSpeaking(readString.toString(), mTtsListener);
            }
        }
    }
}
