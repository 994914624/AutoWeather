package test.yang.com.apptest.bean;

import java.util.List;

/**
 * Created by yzk on 2018/10/22
 */

public class Weather4 {


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
     * basic : {"cid":"CN101180801","location":"开封","parent_city":"开封","admin_area":"河南","cnty":"中国","lat":"34.79705048","lon":"114.34144592","tz":"+8.00"}
     * update : {"loc":"2018-10-22 13:45","utc":"2018-10-22 05:45"}
     * status : ok
     * now : {"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"21","hum":"41","pcpn":"0.0","pres":"1019","tmp":"22","vis":"7","wind_deg":"275","wind_dir":"西风","wind_sc":"2","wind_spd":"9"}
     * daily_forecast : [{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2018-10-22","hum":"65","mr":"16:40","ms":"03:49","pcpn":"0.0","pop":"13","pres":"1021","sr":"06:35","ss":"17:38","tmp_max":"24","tmp_min":"10","uv_index":"4","vis":"20","wind_deg":"208","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"2"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2018-10-23","hum":"20","mr":"17:12","ms":"04:47","pcpn":"0.0","pop":"0","pres":"1023","sr":"06:36","ss":"17:37","tmp_max":"21","tmp_min":"9","uv_index":"3","vis":"20","wind_deg":"190","wind_dir":"南风","wind_sc":"1-2","wind_spd":"3"},{"cond_code_d":"101","cond_code_n":"305","cond_txt_d":"多云","cond_txt_n":"小雨","date":"2018-10-24","hum":"41","mr":"17:44","ms":"05:47","pcpn":"0.0","pop":"0","pres":"1020","sr":"06:36","ss":"17:35","tmp_max":"24","tmp_min":"12","uv_index":"0","vis":"20","wind_deg":"172","wind_dir":"南风","wind_sc":"3-4","wind_spd":"20"}]
     * lifestyle : [{"type":"comf","brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},{"type":"drsg","brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"},{"type":"flu","brf":"易发","txt":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},{"type":"sport","brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"type":"trav","brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},{"type":"uv","brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}]
     */

    private String status;
    private java.util.List<DailyForecastBean> daily_forecast;

    public static class DailyForecastBean{

        @Override
        public String toString() {
            return "DailyForecastBean{" +
                    "cond_code_d='" + cond_code_d + '\'' +
                    ", cond_code_n='" + cond_code_n + '\'' +
                    ", cond_txt_d='" + cond_txt_d + '\'' +
                    ", cond_txt_n='" + cond_txt_n + '\'' +
                    ", date='" + date + '\'' +
                    ", hum='" + hum + '\'' +
                    ", mr='" + mr + '\'' +
                    ", ms='" + ms + '\'' +
                    ", pcpn='" + pcpn + '\'' +
                    ", pop='" + pop + '\'' +
                    ", pres='" + pres + '\'' +
                    ", sr='" + sr + '\'' +
                    ", ss='" + ss + '\'' +
                    ", tmp_max='" + tmp_max + '\'' +
                    ", tmp_min='" + tmp_min + '\'' +
                    ", uv_index='" + uv_index + '\'' +
                    ", vis='" + vis + '\'' +
                    ", wind_deg='" + wind_deg + '\'' +
                    ", wind_dir='" + wind_dir + '\'' +
                    ", wind_sc='" + wind_sc + '\'' +
                    ", wind_spd='" + wind_spd + '\'' +
                    '}';
        }

        /**
         * cond_code_d : 101
         * cond_code_n : 101
         * cond_txt_d : 多云
         * cond_txt_n : 多云
         * date : 2018-10-22
         * hum : 65
         * mr : 16:40
         * ms : 03:49
         * pcpn : 0.0
         * pop : 13
         * pres : 1021
         * sr : 06:35
         * ss : 17:38
         * tmp_max : 24
         * tmp_min : 10
         * uv_index : 4
         * vis : 20
         * wind_deg : 208
         * wind_dir : 西南风
         * wind_sc : 1-2
         * wind_spd : 2
         */

        private String cond_code_d;
        private String cond_code_n;
        private String cond_txt_d;
        private String cond_txt_n;
        private String date;
        private String hum;
        private String mr;
        private String ms;
        private String pcpn;
        private String pop;
        private String pres;
        private String sr;
        private String ss;
        private String tmp_max;
        private String tmp_min;
        private String uv_index;
        private String vis;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;

        public String getCond_code_d() {
            return cond_code_d;
        }

        public void setCond_code_d(String cond_code_d) {
            this.cond_code_d = cond_code_d;
        }

        public String getCond_code_n() {
            return cond_code_n;
        }

        public void setCond_code_n(String cond_code_n) {
            this.cond_code_n = cond_code_n;
        }

        public String getCond_txt_d() {
            return cond_txt_d;
        }

        public void setCond_txt_d(String cond_txt_d) {
            this.cond_txt_d = cond_txt_d;
        }

        public String getCond_txt_n() {
            return cond_txt_n;
        }

        public void setCond_txt_n(String cond_txt_n) {
            this.cond_txt_n = cond_txt_n;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }

        public String getTmp_max() {
            return tmp_max;
        }

        public void setTmp_max(String tmp_max) {
            this.tmp_max = tmp_max;
        }

        public String getTmp_min() {
            return tmp_min;
        }

        public void setTmp_min(String tmp_min) {
            this.tmp_min = tmp_min;
        }

        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getWind_sc() {
            return wind_sc;
        }

        public void setWind_sc(String wind_sc) {
            this.wind_sc = wind_sc;
        }

        public String getWind_spd() {
            return wind_spd;
        }

        public void setWind_spd(String wind_spd) {
            this.wind_spd = wind_spd;
        }
    }

}
