package com.luxury.service.impl;

import com.luxury.service.ReqGetService;
import com.luxury.utils.network.Request;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:34
 */
@Service(value = "reqGetService")
public class ReqGetServiceImpl implements ReqGetService {
    /**
     * 发送http get请求，不关心回应
     *
     * @param url   请求路径
     * @param heads 请求头部信息(无需添加头部信息请设置null)
     */
    @Override
    public void reqNoReply(String url, Map<String, String> heads) {
        Request.sendGetNoReply(url, heads);
    }

    /**
     * 发送http get请求
     *
     * @param url   请求地址
     * @param heads 请求头部信息
     * @return
     */
    @Override
    public String req(String url, Map<String, String> heads) {
        return Request.sendGet(url, heads);
    }
}
