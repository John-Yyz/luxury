package com.luxury.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:01
 */
public class SignUtils {

    /**
     * 签名生成方式： 1)
     * 将(requrl(请求链接)+ts(时间戳)+secret(双方拟定，不作为明文传递)+token)以其参数名的字典序升序进行排序
     * ,且按"key=value"格式拼接在一起 2) 使用MD5对待签名串求签 3) 将MD5输出的二进制结果转换为小写的十六进制
     */
    public static String getSignature(Map<String, Object> params, String secret)
            throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            basestring.append(param.getKey()).append("=")
                    .append(param.getValue());
            basestring.append("&");
        }
        basestring.append("secret=" + secret);

//		System.out.println("签名链接为----》 " + basestring.toString());

        // 使用MD5对待签名串求签
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
//		System.out.println("加密后签名--》 " + sign.toString());
        return sign.toString();
    }
}
