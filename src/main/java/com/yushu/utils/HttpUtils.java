package com.yushu.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    public static String doGet(String url){
        url= url.replaceAll(" ", "%20");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // 请求超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setRedirectsEnabled(true).build();
        httpGet.setConfig(requestConfig);

        httpGet.addHeader("Content-Type", "application/json; chartset=UTF-8");
        //执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                //获得响应实体
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String sendPost(String url, String data) {
        String response = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httppost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setRedirectsEnabled(true).build();
            httppost.setConfig(requestConfig);
            httppost.addHeader("Content-Type", "application/json; chartset=UTF-8");

            StringEntity entity = new StringEntity(data, ContentType.create("text/json", "UTF-8"));
            httppost.setEntity(entity);

            httpResponse = httpClient.execute(httppost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            // 关闭连接
            try {
                httpClient.close();
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static String sendPost2(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果："+result);
        return result;
    }
}
