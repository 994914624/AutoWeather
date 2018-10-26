package test.yang.com.apptest.bean;

import java.util.List;

/**
 * Created by yzk on 2018/10/17
 */

public class Weather3 {


    private String status;
    private java.util.List<DailyForecastBean> daily_forecast;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    /**
     * basic : {"cid":"CN101180801","location":"开封","parent_city":"开封","admin_area":"河南","cnty":"中国","lat":"34.79705048","lon":"114.34144592","tz":"+8.00","city":"开封","id":"CN101180801","update":{"loc":"2018-10-16 18:46","utc":"2018-10-16 10:46"}}
     * update : {"loc":"2018-10-16 18:46","utc":"2018-10-16 10:46"}
     * status : ok
     * now : {"cloud":"50","cond_code":"100","cond_txt":"晴","fl":"15","hum":"62","pcpn":"0.0","pres":"1018","tmp":"17","vis":"6","wind_deg":"27","wind_dir":"东北风","wind_sc":"2","wind_spd":"11","cond":{"code":"100","txt":"晴"}}
     * daily_forecast : [{"date":"2018-10-16","cond":{"txt_d":"晴"},"tmp":{"max":"21","min":"13"}},{"date":"2018-10-17","cond":{"txt_d":"多云"},"tmp":{"max":"19","min":"12"}},{"date":"2018-10-18","cond":{"txt_d":"晴"},"tmp":{"max":"20","min":"11"}},{"date":"2018-10-19","cond":{"txt_d":"多云"},"tmp":{"max":"19","min":"11"}},{"date":"2018-10-20","cond":{"txt_d":"多云"},"tmp":{"max":"20","min":"11"}},{"date":"2018-10-21","cond":{"txt_d":"多云"},"tmp":{"max":"21","min":"12"}},{"date":"2018-10-22","cond":{"txt_d":"多云"},"tmp":{"max":"20","min":"12"}}]
     * hourly : [{"cloud":"0","cond_code":"101","cond_txt":"多云","dew":"8","hum":"80","pop":"0","pres":"1021","time":"2018-10-16 22:00","tmp":"16","wind_deg":"46","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"17"},{"cloud":"1","cond_code":"100","cond_txt":"晴","dew":"6","hum":"87","pop":"0","pres":"1021","time":"2018-10-17 01:00","tmp":"14","wind_deg":"56","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"12"},{"cloud":"4","cond_code":"100","cond_txt":"晴","dew":"5","hum":"90","pop":"0","pres":"1021","time":"2018-10-17 04:00","tmp":"13","wind_deg":"61","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"18"},{"cloud":"67","cond_code":"100","cond_txt":"晴","dew":"9","hum":"89","pop":"0","pres":"1021","time":"2018-10-17 07:00","tmp":"13","wind_deg":"70","wind_dir":"东北风","wind_sc":"4-5","wind_spd":"26"},{"cloud":"68","cond_code":"100","cond_txt":"晴","dew":"8","hum":"78","pop":"0","pres":"1021","time":"2018-10-17 10:00","tmp":"17","wind_deg":"62","wind_dir":"东北风","wind_sc":"4-5","wind_spd":"26"},{"cloud":"19","cond_code":"100","cond_txt":"晴","dew":"5","hum":"66","pop":"0","pres":"1019","time":"2018-10-17 13:00","tmp":"18","wind_deg":"74","wind_dir":"东北风","wind_sc":"4-5","wind_spd":"31"},{"cloud":"1","cond_code":"100","cond_txt":"晴","dew":"4","hum":"61","pop":"0","pres":"1018","time":"2018-10-17 16:00","tmp":"18","wind_deg":"54","wind_dir":"东北风","wind_sc":"3-4","wind_spd":"24"},{"cloud":"3","cond_code":"100","cond_txt":"晴","dew":"4","hum":"56","pop":"0","pres":"1018","time":"2018-10-17 19:00","tmp":"16","wind_deg":"40","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"4"}]
     * aqi : {"city":{"aqi":"127","pm25":"96","qlty":"轻度污染"}}
     * suggestion : {"comf":{"type":"comf","brf":"舒适","txt":"今天夜间不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"sport":{"type":"sport","brf":"较适宜","txt":"天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。"},"cw":{"type":"cw","brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"}}
     */
   public static class DailyForecastBean{


        @Override
        public String toString() {
            return "DailyForecastBean{" +
                    "date='" + date + '\'' +
                    ", cond=" + cond +
                    ", tmp=" + tmp +
                    '}';
        }

        /**
         * date : 2018-10-16
         * cond : {"txt_d":"晴"}
         * tmp : {"max":"21","min":"13"}
         */

        private String date;
        private CondBean cond;
        private TmpBean tmp;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }

        public static class CondBean {
            @Override
            public String toString() {
                return "CondBean{" +
                        "txt_d='" + txt_d + '\'' +
                        '}';
            }

            /**
             * txt_d : 晴
             */

            private String txt_d;

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }
        }

        public static class TmpBean {
            @Override
            public String toString() {
                return "TmpBean{" +
                        "max='" + max + '\'' +
                        ", min='" + min + '\'' +
                        '}';
            }

            /**
             * max : 21
             * min : 13
             */

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }
    }

}
