package multiAgent.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by zy on 17/5/15.
 */
public class MapUtil {

    //需要调用的方法
    public static  Map<String,List<MapObject>> searchAroundSite(List<String> keyWords , String longitude , String lat){

        Map<String,List<MapObject>> jsonObjects = new HashMap<String, List<MapObject>>();

        for(String keyword : keyWords) {
            //得到距离最近的10个关键词地点,如果没有map中就对应一个，暂时是1km为半径的圆
            List<MapObject> obs = new ArrayList<MapObject>();
            String jsonString = httpGet(keyword, longitude, lat);
            JSONObject jsonObject = JSON.parseObject(jsonString);
            if ((Integer) jsonObject.get("total") == 0) {
                jsonObjects.put(keyword, obs);
            }else {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                Iterator<Object> it = jsonArray.iterator();
                while (it.hasNext()) {
                    JSONObject ob = (JSONObject) it.next();
                    MapObject model = new MapObject();
                    model.setAddress(ob.getString("address"));
                    model.setName(ob.getString("name"));
                    JSONObject location = ob.getJSONObject("location");
                    model.setLat(String.valueOf(location.get("lat")));
                    model.setLng(String.valueOf(location.get("lng")));
                    JSONObject detail = ob.getJSONObject("detail_info");
                    model.setDistance(String.valueOf(detail.get("distance")));
                    model.setTag(detail.getString("tag"));
                    model.setOverall_rating(detail.getString("overall_rating"));
                    obs.add(model);
                }
            }
            jsonObjects.put(keyword,obs);
        }

        return jsonObjects;
    }

    private static String httpGet(String keyWord , String longitude ,String lat){
        String url = "http://api.map.baidu.com/place/v2/search?query=";
        url=url+keyWord+"&location="+lat+","+longitude+"&radius=1000&filter=sort_name:distance%7csort_rule:1&output=json&page_num=0&scope=2&ak=sqm5UqFMLzcb9XVx2baZVX3berIcLnEq";
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                url = URLDecoder.decode(url, "UTF-8");
                return strResult;
            } else {
                System.out.println("请求失败");
                return null ;
            }
        } catch (IOException e) {
            System.out.println("请求失败");
            return null;
        }
    }

    public static  void main(String[] args){
        List<String> keywords = new ArrayList<String>();
        keywords.add("超市");
        keywords.add("旅游景点");
        searchAroundSite(keywords,"120.90407","30.944314");
    }
}

