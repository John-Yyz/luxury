package com.luxury.service.impl;

import com.luxury.service.ReqPostService;
import com.luxury.utils.network.Request;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/3 0:59
 */
@Service(value = "reqPostService")
public class ReqPostServiceImpl implements ReqPostService {

    public static HashMap<String, String> formHeads = new HashMap<String, String>();
    static{
        formHeads.put("Content-type","application/x-www-form-urlencoded");
        formHeads.put("connection", "Keep-Alive");
        formHeads.put("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    }
    /**
     * 发送http post请求，不关心回应
     * @param url 请求路径
     * @param body 消息内容
     * @param isJson 是否是JSON格式
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     */
    public void reqNoReply(String url,String body,boolean isJson,Map<String, String> heads){
        Request.sendPostNoReply(url,body, isJson, heads);
    }
    /**
     * 发送http post请求
     * @param url 请求路径
     * @param body 消息内容
     * @param isJson 是否是JSON格式
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     * @return
     */
    public String req(String url,String body,boolean isJson,Map<String, String> heads){
        return Request.sendPost(url,body, isJson, heads);
    }
    /**
     * 发送http post请求,以表单模式体检
     * @param url 请求路径
     * @param body 消息内容
     * @return
     */
    public String reqByForm(String url,String body){
        return Request.sendPost(url, body, false, formHeads);
    }
    /**
     * 发送http post请求,以表单模式体检
     * @param url 请求路径
     * @return
     */
    public String reqByForm(String url,Map<String,Object> map){
        String body = null;
        if(map != null){
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if(body == null){
                    body = entry.getKey()+"="+entry.getValue();
                }else
                    body+= "&"+entry.getKey()+"="+entry.getValue();
            }
        }
        return Request.sendPost(url, body, false, formHeads);
    }
}
