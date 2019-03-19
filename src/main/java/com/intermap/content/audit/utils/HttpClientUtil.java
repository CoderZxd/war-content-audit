package com.intermap.content.audit.utils;

import com.alibaba.druid.util.HttpClientUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;


public class HttpClientUtil {

    private static final Log logger = LogFactory.getLog(HttpClientUtils.class);
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, Map<String, String> headers) {
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "close");
            // 获取所有响应头字段
            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> map = it.next();
                connection.setRequestProperty(map.getKey(), map.getValue());
            }
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！错误信息如下：" + e.getMessage());
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.error("关闭流异常。异常信息为:"+e2.getMessage());
            }
        }
        return result.toString();
    }

    /**
     * @FileName HttpClientUtils.java
     * @ClassName HttpClientUtils
     * @MethodName sendPost
     * @Desc post请求
     * @author zouxiaodong
     * @date 2019/03/15 14:12
     * @Params [postUrl, params, headers]
     * @return java.lang.String
     */
    public static String sendPost(String postUrl, Object params, Map<String,String>headers){
        HttpURLConnection httpURLConnection = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL url = new URL(postUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("connection", "close");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setConnectTimeout(20000);//设置超时时间为20s
            if(null != headers && !headers.isEmpty()){
                for(Map.Entry<String,String> header:headers.entrySet()){
                    httpURLConnection.setRequestProperty(header.getKey(),header.getValue());
                }
            }
            // 发送POST请求参数
            out = new PrintWriter(httpURLConnection.getOutputStream());
            out.write(params.toString());
            out.flush();
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuffer content = new StringBuffer();
                String tempStr = null;
                in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                while ((tempStr = in.readLine()) != null) {
                    content.append(tempStr);
                }
                result = content.toString();
            }else{
                result = "发送post请求失败.响应码为:"+httpURLConnection.getResponseCode();
            }
        }catch (Exception e){
            result = "发送post请求异常.异常信息为:"+e.getMessage();
            logger.error("发送post请求异常.异常信息为:"+e.getMessage());
        }finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                logger.error("关闭Http请求异常.异常信息为:"+e.getMessage());
            }
        }
        return result;
    }
}