package test.yang.com.apptest.util;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import test.yang.com.apptest.bean.Weather2;
import test.yang.com.apptest.bean.Weather3;
import test.yang.com.apptest.bean.Weather4;

/**
 * Created by yzk on 2018/10/17
 */

public class WeatherDataUtil {

    private static String TAG = "WeatherDataUtil";

    /**
     * http://guolin.tech/api/weather?cityid=CN101180801&key=bc0418b57b2d4918819d3974ac1285d9
     * <p>
     * 天气 3 的 data List
     */
    public static ArrayList<String> getWeather_3_data(String response, StringBuilder sb) {
        try {
            if (response == null) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("HeWeather");
            ArrayList<String> arrayList = new ArrayList<String>();
            if (null != jsonArray) {
                String weatherContent = jsonArray.optJSONObject(0).toString();
                if (!"ok".equals(jsonArray.optJSONObject(0).optString("status"))) {
                    return null;
                }
                if (!TextUtils.isEmpty(weatherContent)) {
                    Weather3 weather3 = JSON.parseObject(weatherContent, Weather3.class);
                    if (null != weather3) {
                        List<Weather3.DailyForecastBean> list = weather3.getDaily_forecast();
                        for (int i = 0; i < list.size(); i++) {
                            if(i>3){
                                break;
                            }
                            Weather3.DailyForecastBean bean = list.get(i);
                            String date = bean.getDate();
                            String day;
                            if (i == 0) {
                                day = "今天 ";
                            } else if (i == 1) {
                                day = "明天 ";
                            } else if (i == 2) {
                                day = "后天 ";
                            } else {
                                day = "大后天 ";
                            }
                            sb.append(day).append(date.substring(date.indexOf("-")+1,date.lastIndexOf("-"))).append("月").append(date.substring(date.lastIndexOf("-")+1)).append("号");
                            String tianqi = bean.getCond().getTxt_d();
                            sb.append("  天气 ").append(tianqi);
                            arrayList.add(tianqi);
                            sb.append(" 最低气温  ").append(bean.getTmp().getMin()).append("℃");
                            sb.append(" 最高气温  ").append(bean.getTmp().getMax()).append("℃");
                            sb.append("          ");
                        }
                        Log.d(TAG, " 3 " + sb.toString());
                        return arrayList;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http://t.weather.sojson.com/api/weather/city/101180801
     * 天气 2 的 data List
     */
    public static ArrayList<String> getWeather_2_data(String response, StringBuilder readString) {
        try {
            if (response == null) {
                return null;
            }
            //解析天气信息
            Weather2 weather2 = JSON.parseObject(response, Weather2.class);
            ArrayList<String> arrayList = new ArrayList<String>();
            if (weather2 != null) {
                if (weather2.getStatus() != 200) {
                    return null;//天气请求失败
                }
                List<Weather2.DataBean.ForecastBean> list = weather2.getData().getForecast();

                for (int i = 0; i < list.size(); i++) {
                    if(i>3){
                        break;
                    }
                    Weather2.DataBean.ForecastBean bean = (Weather2.DataBean.ForecastBean) list.get(i);
                    String date = bean.getDate();
                    String day;
                    if (i == 0) {
                        day = "今天 ";
                    } else if (i == 1) {
                        day = "明天 ";
                    } else if (i == 2) {
                        day = "后天 ";
                    } else {
                        day = "大后天 ";
                    }
                    readString.append(day).append(bean.getDate().substring(0, bean.getDate().length() - 4)).append("号 ");
                    readString.append(bean.getDate().substring(bean.getDate().length() - 3, bean.getDate().length())).append("   ");
                    String tianqi = bean.getType();
                    readString.append("  天气 ").append(tianqi).append("   ");
                    arrayList.add(tianqi);
                    readString.append(bean.getFx()).append("  ").append(bean.getFl().substring(bean.getFl().length() - 2, bean.getFl().length())).append("   ");
                    readString.append(" 最低气温  ").append(bean.getLow().substring(3, bean.getLow().length() - 3)).append("℃");
                    readString.append(" 最高气温  ").append(bean.getHigh().substring(3, bean.getHigh().length() - 3)).append("℃");
                    readString.append("          ");
                }
                Log.d(TAG, " 2 " + readString.toString());
                return arrayList;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * https://free-api.heweather.com/s6/weather?key=0efbf8d05c334e8aa00803a88845596d&location=CN101180801
     *
     * 天气 4 的 data List
     *
     */
    public static ArrayList<String> getWeather_4_data(String response, StringBuilder sb){
        try{
            if (response == null) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("HeWeather6");
            ArrayList<String> arrayList = new ArrayList<String>();
            if (null != jsonArray) {
                String weatherContent = jsonArray.optJSONObject(0).toString();
                if (!"ok".equals(jsonArray.optJSONObject(0).optString("status"))) {
                    return null;
                }

                if (!TextUtils.isEmpty(weatherContent)) {
                    Weather4 weather4 = JSON.parseObject(weatherContent, Weather4.class);
                    if (null != weather4) {
                        List<Weather4.DailyForecastBean> list = weather4.getDaily_forecast();
                        for (int i = 0; i < list.size(); i++) {
                            if(i>3){
                                break;
                            }
                            Weather4.DailyForecastBean bean = list.get(i);
                            String date = bean.getDate();
                            String day;
                            if (i == 0) {
                                day = "今天 ";
                            } else if (i == 1) {
                                day = "明天 ";
                            } else if (i == 2) {
                                day = "后天 ";
                            } else {
                                day = "大后天 ";
                            }
                            sb.append(day).append(date.substring(date.indexOf("-")+1,date.lastIndexOf("-"))).append("月").append(date.substring(date.lastIndexOf("-")+1)).append("号");
                            String tianqi = bean.getCond_txt_d();
                            sb.append("  天气 ").append(tianqi);
                            sb.append(" ").append(bean.getWind_dir()).append(bean.getWind_sc()).append("级");
                            arrayList.add(tianqi);
                            sb.append(" 最低气温  ").append(bean.getTmp_min()).append("℃");
                            sb.append(" 最高气温  ").append(bean.getTmp_max()).append("℃");
                            sb.append("          ");
                        }
                        Log.d(TAG, " 4 " + sb.toString());
                        return arrayList;
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * http://m.nmc.cn/publish/forecast/AHA/kaifengshi.html
     * html 获取的天气信息
     */
    public static ArrayList<String> getHtmlWeather(StringBuilder sb) {


        String string = HtmlParseUtil.getInput();
        if (string == null) {
            return null;
        }
        Log.d(TAG, ":::::" + string);
        ArrayList<String> arrayList = new ArrayList<String>();
        try {

            String strArr[] = string.split("#");
            for (int i = 0; i < strArr.length; i++) {
                String s = strArr[i];
                StringBuilder stringBuilder =new StringBuilder();

                if (i == 0) {
                    String[] s1 = s.split("@");

                    String feng=s1[2];
                    String fengxiang=feng.substring(0,feng.indexOf("风")+1);
                    String fengli="";
                    if(feng.contains("级")){
                         fengli= feng.substring(feng.indexOf("级")-4,feng.indexOf("级")+1);
                    }
                    //
                    stringBuilder.append(s1[1].trim());
                    if(stringBuilder.toString().contains(" ")) {
                        stringBuilder.insert(stringBuilder.indexOf(" ")+1, "转");
                    }
                    arrayList.add(stringBuilder.toString());
                    sb.append(s1[0]).append("   天气 ").append(stringBuilder).append("   ").append(fengxiang).append(fengli).append("    ").append("     温度").append(s1[3]).append("         \n");
                }
                if (i == 1) {
                    String[] s1 = s.split("@");

                    String feng=s1[2];
                    String fengxiang=feng.substring(0,feng.indexOf("风")+1);
                    String fengli="";
                    if(feng.contains("级")){
                        fengli= feng.substring(feng.indexOf("级")-4,feng.indexOf("级")+1);
                    }
                    //
                    stringBuilder =new StringBuilder();
                    stringBuilder.append(s1[1].trim());
                    if(stringBuilder.toString().contains(" ")) {
                        stringBuilder.insert(stringBuilder.indexOf(" ")+1, "转");
                    }
                    arrayList.add(stringBuilder.toString());
                    sb.append(s1[0]).append("   天气 ").append(stringBuilder).append("   ").append(fengxiang).append(fengli).append("     最高温度").append(s1[3].substring(0, s1[3].indexOf(' '))).append(" 最低温度").append(s1[3].substring(s1[3].indexOf(' '))).append("          \n");
                }
                if (i == 2) {
                    String[] s1 = s.split("@");
                    //String tianqi=s1[1];


                    String feng=s1[2];
                    String fengxiang=feng.substring(0,feng.indexOf("风")+1);
                    String fengli="";
                    if(feng.contains("级")){
                        fengli= feng.substring(feng.indexOf("级")-4,feng.indexOf("级")+1);
                    }
                    //
                    stringBuilder =new StringBuilder();
                    stringBuilder.append(s1[1].trim());
                    if(stringBuilder.toString().contains(" ")) {
                        stringBuilder.insert(stringBuilder.indexOf(" ")+1, "转");
                    }
                    arrayList.add(stringBuilder.toString());
                    sb.append(s1[0]).append("   天气 ").append(stringBuilder).append("    ").append(fengxiang).append(fengli).append("     最高气温 ").append(s1[3].substring(0, s1[3].indexOf(' '))).append(" 最低气温").append(s1[3].substring(s1[3].indexOf(' '))).append("         \n");
                }
                if (i == 3) {
                    String[] s1 = s.split("@");

                    String feng=s1[2];
                    String fengxiang=feng.substring(0,feng.indexOf("风")+1);
                    String fengli="";
                    if(feng.contains("级")){
                        fengli= feng.substring(feng.indexOf("级")-4,feng.indexOf("级")+1);
                    }
                    //
                    stringBuilder =new StringBuilder();
                    stringBuilder.append(s1[1].trim());
                    if(stringBuilder.toString().contains(" ")) {
                        stringBuilder.insert(stringBuilder.indexOf(" ")+1, "转");
                    }
                    arrayList.add(stringBuilder.toString());
                    sb.append("   大后天 ").append(s1[0].substring(3)).append("   天气 ").append(stringBuilder).append("    ").append(fengxiang).append(fengli).append("     最高气温 ").append(s1[3].substring(0, s1[3].indexOf(' '))).append(" 最低气温").append(s1[3].substring(s1[3].indexOf(' ')));
                }

            }
            Log.d(TAG, "#####：" + sb.toString());
            Log.d(TAG, "arrayList：" + arrayList.toString());
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
