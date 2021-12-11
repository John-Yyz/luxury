package com.luxury.service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:33
 */
public interface ReqPostService {

    /**
     * 发送http post请求，不关心回应
     * @param url 请求路径
     * @param body 消息内容
     * @param isJson 是否是JSON格式
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     */
    void reqNoReply(String url,String body,boolean isJson,Map<String, String> heads);

    /**
     * 发送http post请求
     * @param url 请求路径
     * @param body 消息内容
     * @param isJson 是否是JSON格式
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     * @return
     */
    String req(String url,String body,boolean isJson,Map<String, String> heads);

    /**
     * 发送http post请求,以表单模式体检
     * @param url 请求路径
     * @param body 消息内容
     * @return
     */
    String reqByForm(String url,String body);

    /**
     * 发送http post请求,以表单模式体检
     * @param url 请求路径
     * @return
     */
    String reqByForm(String url,Map<String,Object> map);
}
