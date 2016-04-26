package com.example.administrator.doinmvp.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Administrator on 2016/4/24.
 */
public class WeatherModel implements IWeatherModel {
    static String PART_URL = "http://api.map.baidu.com/telematics/v3/weather";
    static String AK = "B5dc31c34fcf5591939ded8c03db10ee";
    static String OutPut = "json";
    @Override
    public String requestData(String cityName) {
        String fullUrl = PART_URL + "?location=" + cityName + "&output=" + OutPut + "&ak=" + AK;
        String weatherStr = httpRequest(fullUrl);
        return weatherStr;
    }
    public String httpRequest(final String strUrl) {
        StringBuilder resultData = new StringBuilder("");
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);//设置连接超时
            urlConn.setRequestMethod("GET");//以get方式发起请求
            urlConn.connect();
            InputStreamReader isr = new InputStreamReader(urlConn.getInputStream());
            BufferedReader buffer = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = buffer.readLine()) != null) {
                resultData.append(inputLine);
            }
            buffer.close();
            isr.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData.toString();
    }
}
