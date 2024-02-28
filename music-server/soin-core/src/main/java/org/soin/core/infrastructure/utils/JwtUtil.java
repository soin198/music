package org.soin.core.infrastructure.utils;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-29 11:36
 **/
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -1035288872310384654L;

    /**
     * 过期时间
     */
    private final static long EXPIRATION = 60 * 60 * 30;

    /**
     * 秘钥
     */
    private final static String SECRET = "jfa-soin";


    /**
     * 根据人员ID生成token
     *
     * @param userId 人员ID
     * @return token
     */
    public static String generateToken(Long userId) {
        Assert.notNull(userId, "userId is invalid");
        Map<String, Object> claims = Maps.newHashMap();
        return doGenerateToken(claims, userId.toString());
    }

    /**
     * 生成TOKEN
     *
     * @param claims  参数
     * @param subject 用户ID
     * @return token
     */
    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

}


