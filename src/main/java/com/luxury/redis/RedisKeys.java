package com.luxury.redis;

/**
 * ---------------------------------------------------------------------------
 * 类名称 ： RedisKeys 类描述 ： 存放Rediskey常量类 创建人 ： 龚恒 创建时间：2016年6月20日
 * 版权拥有：深圳市非洗不可网络科技有限公司
 * ---------------------------------------------------------------------------
 */
public class RedisKeys {

	/** 在线订单支付 */
	public static final String ONLINE_ORDER_KEY_ = "ONLINE_ORDER_KEY_";
	
	/**服务订单支付*/
	public static final String ARTIFICIAL_ORDER_KEY_ = "ARTIFICIAL_ORDER_KEY_";

	
	public static final String PLATFORMSERVER_NEWORDER = "platformserver_neworder";
	
	public static final String PLATFORMSERVER_NEWUSER = "platformserver_newuser";
	
	public static final String PLATFORMSERVER_NEWORDER_MICRO = "platformserver_neworder_micro";
	
	public static final String PLATFORMSERVER_NEWUSER_MICOR = "platformserver_newuser_micro";
	
	
	//订单-设备启动通知Key
	public static final String _START_UP_NOTICE_KEY = "START_UP_NOTICE_KEY_"; 
	
	//验证码
	public static final String SMS_CODE_KEY = "SMS_CODE_KEY_";
	
	//用户token
	public static final String USER_TOKEN_KEY = "USER_TOKEN_KEY_";
	
	/**记录最后登录**/
	public static final String THE_LAST_USERLOGIN = "THE_LAST_USERLOGIN_";
	
	//用户信鸽token
	public static final String USER_XG_TOKEN = "USER_XG_TOKEN_";
	
	//员工信鸽token
	public static final String EMPLOYEE_XG_TOKEN = "EMPLOYEE_XG_TOKEN_";
	
	//代理商信鸽token
	public static final String AGENT_XG_TOKEN = "AGENT_XG_TOKEN_";
	
	//消息key
	public static final String PUSH_MSG_KEY = "PUSH_MSG_KEY";
	
	//消息过期时间Key
	public static final String PUSH_MSG_TIME_KEY = "PUSH_MSG_TIME_KEY_";
	
	//对象数量
	public static final String PUSH_MSG_OBJ_NUM = "PUSH_MSG_OBJ_NUM_";
	
	//会员订单Key
	public static final String MEMBER_ORDER_KEY = "MEMBER_ORDER_KEY_";
	
	//发送错误消息次数
	public static final String SEND_ERROR_MSG_COUNT = "SEND_ERROR_MSG_COUNT_";
	
	//发送错误消息次数2
	public static final String SEND_ERROR_MSG_COUNT_TWO = "SEND_ERROR_MSG_COUNT_TWO_";
	
	//模块
	public static final String MODULE_TYPE = "MODULE_TYPE_";
	
	//优惠券分享次数
	public static final String COUPON_SHARE_COUNT = "COUPON_SHARE_COUNT_";
	
	//请求注册次数
	public static final String REQUEST_REGISTER_COUNT_KEY = "REQUEST_REGISTER_COUNT_KEY_";
	
	//请求发送验证码次数
	public static final String REQUEST_PUSHMSG_COUNT_KEY = "REQUEST_PUSHMSG_COUNT_KEY_";
	
	//钥匙柜订单
	public static final String CABINET_ORDER_KEY_ = "CABINET_ORDER_KEY_";
	
	//用户检测开柜次数
	public static final String USER_CHECK_CABINET_KEY_ = "USER_CHECK_CABINET_KEY_";
	
	//员工检测开柜次数
	public static final String STAFF_CHECK_CABINET_KEY_ = "STAFF_CHECK_CABINET_KEY_";
	
	
}
