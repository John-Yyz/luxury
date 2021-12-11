package com.luxury.service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:33
 */
public interface ReqGetService {

    /**
     * 发送http get请求，不关心回应
     * @param url 请求路径
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     */
    void reqNoReply(String url, Map<String, String> heads);

    /**
     * 发送http get请求
     * @param url 请求地址
     * @param heads 请求头部信息
     * @return
     */
    String req(String url,Map<String, String> heads);
}
