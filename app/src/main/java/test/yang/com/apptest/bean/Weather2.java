package test.yang.com.apptest.bean;

import java.util.List;

/**
 * Created by yzk on 2018/9/16
 */

public class Weather2 {

    /**
     * date : 20180916
     * message : Success !
     * status : 200
     * city : 北京
     * count : 1
     * data : {"shidu":"37%","pm25":5,"pm10":18,"quality":"优","wendu":"19","ganmao":"各类人群可自由活动","yesterday":{"date":"15日星期六","sunrise":"05:53","high":"高温 25.0℃","low":"低温 15.0℃","sunset":"18:25","aqi":16,"fx":"东北风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},"forecast":[{"date":"16日星期日","sunrise":"05:54","high":"高温 25.0℃","low":"低温 14.0℃","sunset":"18:23","aqi":19,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"17日星期一","sunrise":"05:55","high":"高温 27.0℃","low":"低温 17.0℃","sunset":"18:22","aqi":55,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"18日星期二","sunrise":"05:56","high":"高温 25.0℃","low":"低温 17.0℃","sunset":"18:20","aqi":66,"fx":"西南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"19日星期三","sunrise":"05:57","high":"高温 26.0℃","low":"低温 18.0℃","sunset":"18:18","aqi":59,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"20日星期四","sunrise":"05:58","high":"高温 29.0℃","low":"低温 16.0℃","sunset":"18:17","aqi":76,"fx":"西北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shidu : 37%
         * pm25 : 5
         * pm10 : 18
         * quality : 优
         * wendu : 19
         * ganmao : 各类人群可自由活动
         * yesterday : {"date":"15日星期六","sunrise":"05:53","high":"高温 25.0℃","low":"低温 15.0℃","sunset":"18:25","aqi":16,"fx":"东北风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}
         * forecast : [{"date":"16日星期日","sunrise":"05:54","high":"高温 25.0℃","low":"低温 14.0℃","sunset":"18:23","aqi":19,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"17日星期一","sunrise":"05:55","high":"高温 27.0℃","low":"低温 17.0℃","sunset":"18:22","aqi":55,"fx":"北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"18日星期二","sunrise":"05:56","high":"高温 25.0℃","low":"低温 17.0℃","sunset":"18:20","aqi":66,"fx":"西南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"19日星期三","sunrise":"05:57","high":"高温 26.0℃","low":"低温 18.0℃","sunset":"18:18","aqi":59,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"20日星期四","sunrise":"05:58","high":"高温 29.0℃","low":"低温 16.0℃","sunset":"18:17","aqi":76,"fx":"西北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 15日星期六
             * sunrise : 05:53
             * high : 高温 25.0℃
             * low : 低温 15.0℃
             * sunset : 18:25
             * aqi : 16
             * fx : 东北风
             * fl : 3-4级
             * type : 多云
             * notice : 阴晴之间，谨防紫外线侵扰
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            @Override
            public String toString() {
                return "ForecastBean{" +
                        "date='" + date + '\'' +
                        ", sunrise='" + sunrise + '\'' +
                        ", high='" + high + '\'' +
                        ", low='" + low + '\'' +
                        ", sunset='" + sunset + '\'' +
                        ", aqi=" + aqi +
                        ", fx='" + fx + '\'' +
                        ", fl='" + fl + '\'' +
                        ", type='" + type + '\'' +
                        ", notice='" + notice + '\'' +
                        '}';
            }

            /**
             * date : 16日星期日
             * sunrise : 05:54
             * high : 高温 25.0℃
             * low : 低温 14.0℃
             * sunset : 18:23
             * aqi : 19
             * fx : 北风
             * fl : <3级
             * type : 多云
             * notice : 阴晴之间，谨防紫外线侵扰
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;
            private String week;

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
