package com.luxury.utils.network;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:23
 */
public class Request {

    private static Logger logger = LoggerFactory.getLogger(Request.class);
    private final static ExecutorService cachedThreadPool = Executors.newFixedThreadPool(20);
    /**
     * 发送http get请求(无任何头部信息)
     * @param url 请求地址
     * @return
     */
    public static void sendGetNoReply(final String url,final Map<String, String> heads){
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> myHeads = heads;
                String result = Request.sendGet(url, myHeads);
                logger.info("HTTP RESULT:"+result);
            }
        });
    }
    /**
     * 发送http get请求
     * @param url 请求地址
     * @param heads 请求头部信息
     * @return
     */
    public static String sendGet(String url,Map<String, String> heads){
        Request req = new Request();
        return req.doGet(url,heads);
    }
    /**
     * 发送http post请求，同步等等响应
     * @param url 请求路径
     * @param body 字符串内容
     * @param isJson 是否为json格式
     * @param heads 请求头部信息（可为null）
     */
    public static String sendPost(String url,String body,boolean isJson,Map<String, String> heads){
        Request req = new Request();
        heads = req.addJsonContentType(isJson,heads);
        return req.doPost(url,body,heads);
    }


    /**
     * 发送http post请求，不关心回应
     * @param url 请求路径
     * @param body 字符串内容
     * @param isJson 是否为json格式
     * @param heads 请求头部信息（可为null）‰
     */
    public static void sendPostNoReply(final String url,final String body,final boolean isJson,final Map<String, String> heads){
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> myHeads = heads;
                String result = Request.sendPost(url, body, isJson, myHeads);
                logger.info("HTTP RESULT:"+result);
            }
        });
    }

    private Map<String, String> addJsonContentType(boolean isJson,Map<String, String> heads){
        if(isJson == true){
            if(heads == null)
                heads = new HashMap<String, String>();
            heads.put(HTTP.CONTENT_TYPE, "application/json");
        }
        return heads;
    }

    private String doGet(String url,Map<String, String> heads){
        try {
            HttpGet httpGet = new HttpGet(url);

            CloseableHttpClient client = HttpClients.createDefault();

            if(heads != null){
                for (Map.Entry<String, String> entry : heads.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity);
            return content;
        } catch (Exception error) {
            logger.error("发送http get请求失败:"+url, error);
            return null;
        }
    }

    private String doPost(String url,String body,Map<String, String> heads) {
        try {
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpClient client = HttpClients.createDefault();
            if(heads != null){
                for (Map.Entry<String, String> entry : heads.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if(body != null){
                StringEntity se = new StringEntity(body, "UTF-8");
                httpPost.setEntity(se);
            }

            HttpResponse httpResponse = client.execute(httpPost);

            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity);
            return content;
        } catch (Throwable error) {
            logger.error("发送http post请求失败:"+url, error);
            return null;
        }
    }
}
