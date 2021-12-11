package com.luxury.config;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 1:04
 */
public class DomainData {
    //jwt密钥
    public final static String _TOKEN_SCERET_KEY = "50500BBF42C0BAC342570680E569389493";

    // token过期时间(30天)
    public final static long USERTTLMILLIS = 86400000 * 30;

    public final static String appid = "wx39041e4b5e1dd0b1";

    public final static String appSecret = "a34ff6e31915800d497807397a09a53e";

    public final static Integer isOffcialEnv = 0;

    //前后端交互密钥
    public final static String _SECRET_KEY = "YZ789D45DD47DCC1439DDF43E9ECF01A08";
}
