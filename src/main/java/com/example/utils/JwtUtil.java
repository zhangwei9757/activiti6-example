package com.example.utils;


import com.example.enums.JwtExpiration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by jwt on 2019-8-16
 * <p>
 * 1、iss
 * issuer，是签发该证书的负责人。
 * <p>
 * 2、sub
 * subject，主体
 * <p>
 * 3、aud
 * Audience，是指jwt的接受者。
 * <p>
 * 4、exp
 * expiration time，过期时间。
 * <p>
 * 5、nbf
 * Not before，开始日期
 * <p>
 * 6、iat
 * issued at，是指jwt的发行时间
 * <p>
 * 7、jti
 * jwt id，jwt提供唯一的标识符。
 */
public class JwtUtil {
    private static final long EXPIRATIONTIME_DAY = 86400_000L;
    private static final long EXPIRATIONTIME_YEAR = 31_536_000_000L;
    private static final String SECRET = "ZhAnGWeI9757";

    /**
     * 生成jwt token.
     *
     * @param account
     * @param authorities
     * @param mode
     * @return
     */
    public static String generate(String account, String authorities, JwtExpiration mode) {
        return Jwts.builder()
                .claim("authorities", authorities)
                .setIssuer("zhangwei")
                .setSubject("jwt")
                .setAudience(account)
                .setExpiration(new Date(System.currentTimeMillis() + mode.getExpiration()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析jwt token.
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims verify(String token) throws Exception {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
