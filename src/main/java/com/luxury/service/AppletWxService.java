package com.luxury.service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:38
 */
public interface AppletWxService {

    /**
     *  通过jscode获取微信小程序信息
     * @param jscode
     * @return
     */
    Map<String, Object> getAppletWx(String jscode);
}
