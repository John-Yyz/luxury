package com.luxury.service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/3 0:46
 */
public interface WechatService {

    Map<String, Object> getWxResult(String openId);
}
