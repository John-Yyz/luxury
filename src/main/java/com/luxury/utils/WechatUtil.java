package com.luxury.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * ---------------------------------------------------------------------------
 * 类名称   ： WechatUtil
 * 类描述   ：   TODO
 * 创建人   ： 龚恒
 * 创建时间：2018年10月11日
 * 版权拥有：深圳市非洗不可网络科技有限公司
 * ---------------------------------------------------------------------------
 */

/**
 * 微信工具类
 */
public class WechatUtil {

	static final Logger LOGGER = LoggerFactory.getLogger(WechatUtil.class);

	private static final String KEY_ALGORITHM = "AES";

	private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";

	/** 一键登录解析手机号 */
	public static String getPhone(String openId, String encryptedData, String sessionKey, String iv) {
		String phone = null;
		String wbInfo = decryptData2(encryptedData, sessionKey, iv);
		if (StringUtils.isNotBlank(wbInfo)) {
			JSONObject json = JSON.parseObject(wbInfo);
			phone = json.getString("phoneNumber");
		} else {
			LOGGER.info("openId: " + openId + " <-->没有获取到获取一键登录手机号<--> " + "encryptedData: " + encryptedData
					+ ",sessionKey: " + sessionKey + ",iv: " + iv);
		}
		return phone;
	}

	/** 解析用户基本信息 */
	public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {

		byte[] encryptedData = Base64.decode(encryptDataB64);

		byte[] keyBytes = Base64.decode(sessionKeyB64);

		byte[] ivs = Base64.decode(ivB64);

		byte[] encryptedText = null;
		try {
			int base = 16;
			if (keyBytes.length % base != 0) {
				int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
				keyBytes = temp;
			}

			Security.addProvider(new BouncyCastleProvider());
			Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM_STR, "BC");

			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
			encryptedText = cipher.doFinal(encryptedData);

			return new String(encryptedText, "UTF-8");

		} catch (Exception e) {
			LOGGER.info("解密小程序unionid异常", e);
		}

		return null;
	}

	public static String decryptData2(String encryptDataB64, String sessionKeyB64, String ivB64) {

		byte[] encryptedData = Base64.decode(encryptDataB64);

		byte[] keyBytes = Base64.decode(sessionKeyB64);

		byte[] ivs = Base64.decode(ivB64);

		byte[] encryptedText = null;
		try {
			int base = 16;
			if (keyBytes.length % base != 0) {
				int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
				keyBytes = temp;
			}

			Security.addProvider(new BouncyCastleProvider());
			Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM_STR, "BC");

			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
			encryptedText = cipher.doFinal(encryptedData);

			return new String(encryptedText, "UTF-8");

		} catch (Exception e) {
			LOGGER.info("解密小程序手机号异常", e);
		}
		return null;
	}

	public static void main(String[] args) {

		// javax.crypto.BadPaddingException: pad block corrupted

		System.out.println(getPhone("",
				"AeI7mTuCMMUI3DjkvceR92M96LbmQhI17mAJcqUCLoiwOps8645EwYmRDdnWG6Dc8qx+Pb1vHw7MTaNpKLmZehjTGuCfkV3hthWXo3E3D2aSiujisDNWBC8n0QJg/6y/e4A/k5Mueu7ree6KZaxF65bD6BEbNmuHPnZjH3MjkVIMVq3EQ8wcppkczkxxhGMW/mKkClSMB78y6NYBe1ilNQ==",
				"LhwoDiiMbVW5eAisv0kScQ==", "SIFI2bcyf2cd2kjzBOUxxw=="));

		System.out.println(decryptData(
				"pyHa3PjHS6UkiYDOBHxmLlGupxzZChHdGkSmNEtRlDT7dH1sNJZrkmGyYIq2px3VKjulTnylZ6Dcrhd8REdVNvjDLJdKo9fRi6EHTwOd4/k7CO1XDi0W78mmI5ZJRpkKOjScwSXCdDmuhUE8kpveT4q5qw9/lCHg71d7Ryron6cAXU+O+o0iWntGslABH9XBmJodCLN3Rw72pnvATOtp0RWejc2vBLSYPUGEt76UEEhUhULOtKrqGEsCiPLxOEAKSYTlEe7jNoPmyB3yqtDzg/HSCoRUe6yWS6l4WRc9xdc3hXFS4y+0hMq/zL7MaRiREUir5fOjcszJAeI8SNa3AfkCJqlRSrC7yrmCRtdswRIAXLVIEHYkiU7zT3kBtFroklf8g3e6rsAlwDd2P0hEg2da5hHVVIxbk7AxqHSA5bZSFk+w7/f9uSrMRx/vNdDSNjBpxSCf9itCIGYCFsJq2qE52nRChTaioSuvKL5fzrMhoWR0ZAW0Sqwa8SfUsmO/d8YpXnZt9cbo3KXM4gHILPQFno2u9RocPeRxZynqADQ=",
				"LhwoDiiMbVW5eAisv0kScQ==", "ICzf4H+3RMPUuZ3So4GfAQ=="));

	}
}