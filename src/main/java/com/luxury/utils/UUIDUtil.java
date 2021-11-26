package com.luxury.utils;

import java.util.UUID;

/**
 * 描述：随机编码
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 19:04
 */
public class UUIDUtil {

    public static String createCode(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
