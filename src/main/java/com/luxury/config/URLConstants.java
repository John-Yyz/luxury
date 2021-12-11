package com.luxury.config;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/5 10:58
 */
public class URLConstants {

    /** Controller_URL */

    // 验签控制URl
    public final static String JWT_CONTROLLER = "/outh/v1/jwtController";

    //用户控制URL
    public final static String USER_INFO_CONTROLLER = "/api/v1/userInfoController";

    //用户注册控制URL
    public final static String REGIST_CONTROLLER = "/outh/v1/registController";

    /** Controller_URL */



    /** Request_URL */

    //检查是否注册
    public final static String CHECKISREGIST_ANDROID = "/checkIsRegistForAndroid";

    public final static String CHECKISREGIST_IOS = "/checkIsRegistForIOS";

    public final static String CHECKISREGIST_WECHAT = "/checkIsRegistForWechat";

    public final static String CHECKISREGIST_APPLET_WECHAT = "/checkIsRegistForAppletWechat";

    public final static String CHECKISREGIST_APPLET_ALI = "/checkIsRegistForAppletAli";


    //注册（APP，IOS）
    public final static String REGISTER_ANDROID = "/registerForAndroid";

    public final static String REGISTER_IOS = "/registerForIOS";

    //第三方注册
    public final static String THIRD_REGISTER = "/thirdRegister";


    //微信注册（微信、微信小程序、支付宝小程序）
    public final static String WECHATREGISTER_WECHAT = "/weChatRegisterForWechat";

    public final static String WECHATREGISTER_APPLET_WECHAT = "/weChatRegisterForAppletWechat";

    public final static String WECHATREGISTER_APPLET_ALI = "/weChatRegisterForAppletAli";






    /** Request_URL */
}
