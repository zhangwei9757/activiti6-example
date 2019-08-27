package com.example.jwt;

import com.example.enums.JwtExpiration;
import com.example.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import junit.framework.TestCase;

/**
 * Created by jwt on 2019-8-16
 * <p>
 */
public class CustomerTest extends TestCase {

    public void testJwt() throws Exception {
        String token = JwtUtil.generate("test", "testPassword", JwtExpiration.DAY);
        System.out.println("生成token: " + token);

        Claims claims = JwtUtil.verify(token);
        System.out.println("解析token: " + claims);
    }
}
