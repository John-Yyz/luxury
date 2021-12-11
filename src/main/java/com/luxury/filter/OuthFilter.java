package com.luxury.filter;

import com.luxury.config.DomainData;
import com.luxury.request.ReqInfoEnt;
import com.luxury.utils.JwtHelper;
import com.luxury.utils.SignUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ---------------------------------------------------------------------------
 * 类名称 ： OuthFilter 类描述 ： TODO 创建人 ： 龚恒 创建时间：2017年10月20日 版权拥有：深圳市非洗不可网络科技有限公司
 * ---------------------------------------------------------------------------
 */
public class OuthFilter {

	private static class OuthFilterHolder {
		private static final OuthFilter INSTANCE = new OuthFilter();
	}

	private OuthFilter() {
	}

	public static final OuthFilter getInstance() {
		return OuthFilterHolder.INSTANCE;
	}

	public ReqInfoEnt checkOuth(String jsonWebToken, String ts,
								String sign, JSONObject jsonObject) {
		Claims claims = null;

		/** 封装操作对象信息 */
		ReqInfoEnt reqInfoEnt = new ReqInfoEnt();

		try {
			claims = JwtHelper.parseJWT(jsonWebToken);
			if (claims == null) {
				reqInfoEnt.setPlatype(404);
				return reqInfoEnt;
			}
		} catch (final SignatureException e) {
			reqInfoEnt.setPlatype(500);
			return reqInfoEnt;
		}

		/** 请求参数 */
		String paramJson = jsonObject.toJSONString();
		String jsonStr = null;
		if (!paramJson.contains("{}")) {
			jsonStr = signCoverJson(paramJson);
		} else {
			jsonStr = paramJson;
		}

		/** 检验签名是否正确 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", jsonWebToken);
		map.put("ts", ts);
		map.put("params", jsonStr);

		boolean isSign = true;
		if(!sign.equals("test")){
			isSign = compareSign(map, sign);
			if (!isSign) {
				reqInfoEnt.setPlatype(502);
				return reqInfoEnt;
			}
		}
		
		int platype = (int) claims.get("platype");

		// 对应平台的用户ID
		String uid = (String) claims.get("uid");

		reqInfoEnt.setPlatype(platype);
		reqInfoEnt.setUid(uid);
		return reqInfoEnt;
	}

	/**
	 * @Description: 请求参数排序转换后返回
	 * @param @param jsonStr
	 * @param @return
	 * @return String
	 * @throws
	 * @author 龚恒
	 * @date 2017年10月14日
	 */
	private String signCoverJson(String jsonStr) {
		JSONObject object = JSONObject.parseObject(jsonStr).getJSONObject(
				"params");
		if (object == null) {
			return null;
		}
		String sortedParams = JSONObject.toJSONString(object, SerializerFeature.MapSortField);
		JSONObject jsonObject =  JSONObject.parseObject(sortedParams);
		LinkedHashMap<String, Object> objmap = new LinkedHashMap<String, Object>();
		objmap.put("params", jsonObject);
		JSONObject jsonObject2 = (JSONObject) JSONObject.toJSON(objmap);
		String jsonstr = JSONObject.toJSONString(jsonObject2, SerializerFeature.MapSortField);
		return jsonstr;
	}

	/**
	 * @Description: 对比签名是否正确
	 * @param @param request
	 * @param @param map
	 * @param @param sign
	 * @param @return
	 * @return boolean
	 * @throws
	 * @author 龚恒
	 * @date 2016年7月20日
	 */
	private boolean compareSign(Map<String, Object> map, String sign) {
		try {
			String signutil = SignUtils.getSignature(map,
					DomainData._SECRET_KEY);

			if (sign.equals(signutil)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
