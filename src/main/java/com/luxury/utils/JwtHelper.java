package com.luxury.utils;

import com.luxury.config.DomainData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 0:57
 */
public class JwtHelper {
    /**
     * @Description: 验证token
     * @param @param jsonWebToken
     * @param @param base64Security
     * @param @return
     * @return Claims
     * @throws
     * @author 龚恒
     * @date 2017年9月19日
     */
    public static Claims parseJWT(String jsonWebToken) {

        if (jsonWebToken == null || !jsonWebToken.startsWith("Bearer ")) {
            return null;
        }

        String HeadStr = jsonWebToken.substring(0, 6).toLowerCase();
        if (HeadStr.compareTo("bearer") == 0) {

            // 去除Bearer 后部分
            jsonWebToken = jsonWebToken.substring(7, jsonWebToken.length());

        } else {
            return null;
        }

        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(
                            DatatypeConverter
                                    .parseBase64Binary(DomainData._TOKEN_SCERET_KEY))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static String createJWT(String uid, int plaType, String name,
                                   long ttlmillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter
                .parseBase64Binary(DomainData._TOKEN_SCERET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,
                signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setSubject(name)
                .claim("platype", plaType).claim("uid", uid)
                .signWith(signatureAlgorithm, signingKey).setIssuedAt(now);

        // 添加Token过期时间
        if (ttlmillis >= 0) {
            long expMillis = nowMillis + ttlmillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // 生成JWT
        return builder.compact();
    }
}
