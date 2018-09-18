package test.yang.com.apptest.util;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzk on 2018/9/17
 */

public class HtmlParseUtil {

    private static String TAG = "yyy";


    /**
     * <ul class="t clearfix">
     * <li class="sky skyid lv3 on"> <h1>17日（今天）</h1> <big class="png40 d02"></big> <big class="png40 n07"></big> <p title="阴转小雨" class="wea">阴转小雨</p> <p class="tem"> <span>25</span>/<i>19℃</i> </p> <p class="win"> <em> <span title="东北风" class="NE"></span> <span title="东北风" class="NE"></span> </em> <i>&lt;3级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv3"> <h1>18日（明天）</h1> <big class="png40 d08"></big> <big class="png40 n07"></big> <p title="中雨转小雨" class="wea">中雨转小雨</p> <p class="tem"> <span>21</span>/<i>18℃</i> </p> <p class="win"> <em> <span title="东北风" class="NE"></span> <span title="北风" class="N"></span> </em> <i>&lt;3级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv3"> <h1>19日（后天）</h1> <big class="png40 d07"></big> <big class="png40 n08"></big> <p title="小雨转中雨" class="wea">小雨转中雨</p> <p class="tem"> <span>23</span>/<i>18℃</i> </p> <p class="win"> <em> <span title="北风" class="N"></span> <span title="西北风" class="NW"></span> </em> <i>&lt;3级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv3"> <h1>20日（周四）</h1> <big class="png40 d07"></big> <big class="png40 n01"></big> <p title="小雨转多云" class="wea">小雨转多云</p> <p class="tem"> <span>24</span>/<i>18℃</i> </p> <p class="win"> <em> <span title="西风" class="W"></span> <span title="西北风" class="NW"></span> </em> <i>3-4级转4-5级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv1"> <h1>21日（周五）</h1> <big class="png40 d00"></big> <big class="png40 n00"></big> <p title="晴" class="wea">晴</p> <p class="tem"> <span>25</span>/<i>16℃</i> </p> <p class="win"> <em> <span title="西北风" class="NW"></span> <span title="西北风" class="NW"></span> </em> <i>4-5级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv1"> <h1>22日（周六）</h1> <big class="png40 d00"></big> <big class="png40 n00"></big> <p title="晴" class="wea">晴</p> <p class="tem"> <span>24</span>/<i>13℃</i> </p> <p class="win"> <em> <span title="西北风" class="NW"></span> <span title="西北风" class="NW"></span> </em> <i>4-5级转3-4级</i> </p>
     * <div class="slid"></div> </li>
     * <li class="sky skyid lv1"> <h1>23日（周日）</h1> <big class="png40 d00"></big> <big class="png40 n00"></big> <p title="晴" class="wea">晴</p> <p class="tem"> <span>22</span>/<i>11℃</i> </p> <p class="win"> <em> <span title="东北风" class="NE"></span> <span title="南风" class="S"></span> </em> <i>3-4级转&lt;3级</i> </p>
     * <div class="slid"></div> </li>
     * </ul>
     */
    static String html = "<html><head><title>First parse</title></head>"
            + "<body><p>Parsed HTML into a doc.</p></body></html>";

    private static String parseHtml() {

        try {

            Document doc = Jsoup.parseBodyFragment(html);
            Elements body = doc.getElementsByTag("body");
            Log.d(TAG, "doc2:" + body.toString());
            //获取所有目标标签
            Element element = doc.select("ul.weather_datemate").first();


            //
            Log.d(TAG, "222:" + element.toString());
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Element element1 : element.getElementsByTag("li")) {
                Elements p = element1.getElementsByTag("p");
                if (i >= 4) { // 前4个li标签中的内容
                    break;
                }
                for (Element element2 : p) {
                    String txt = element2.text();
                    if (txt.contains(".")) {
                        txt = txt.substring(0, txt.indexOf(".") - 2) +"  "+ txt.substring(txt.indexOf(".") + 1) + "号";
                    }

                    sb.append(txt).append("@");
                }
                sb.append("#");
                i++;
            }


            String str = sb.toString();
             str=str.replace('/',' ' );
             str=str.replace('-',' ');
            Log.d(TAG, "333:" + str);
            return str;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getInput() {

        InputStreamReader reader = null;
        BufferedReader in = null;
        try {

            URL url = new URL("http://m.nmc.cn/publish/forecast/AHA/kaifengshi.html");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(10000);
            reader = new InputStreamReader(connection.getInputStream());
            in = new BufferedReader(reader);
            String line; // 每行内容
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                if (line.contains("weather_w")) {
                    content.append(line);
                }
            }
            //Log.d(TAG,"line:"+line);
            content.insert(0, "<ul class=\"weather_datemate\">");
            content.append("</ul>\n");
            html = content.toString();
            Log.d(TAG, "####:" + html);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    Log.d(TAG, "关闭流出现异常!!!");
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.d(TAG, "关闭流出现异常!!!");
                }
            }
        }

        //解析 html
        return parseHtml();
    }
}
