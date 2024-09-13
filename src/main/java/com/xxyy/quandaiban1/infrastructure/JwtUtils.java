package com.xxyy.quandaiban1.infrastructure;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author xy
 * @date 2023-09-14 16:36
 */
@Component
public class JwtUtils {

    private static final String JWT_KEY="123456";

    private static final long JWT_EXPIRE=60*60*1000L;

    public String createToken(Object data)
    {
        long currentTime = System.currentTimeMillis();
        //过期时间
        long expireTime=currentTime+JWT_EXPIRE;

        //创建token
        String token= Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256,encodeSecret(JWT_KEY))
                .setExpiration(new Date(expireTime))
                .compact();
        return token;
    }

    //使用base64加密
    private SecretKey encodeSecret(String jwtKey) {
        byte[] encode = Base64.getEncoder().encode(jwtKey.getBytes());
        SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");
        return aes;
    }

    //解码token
    public Claims parseToken(String Token)
    {
        JwtParser parser = Jwts.parser();
        JwtParser jwtParser = parser.setSigningKey(encodeSecret(JWT_KEY));
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(Token);
        Claims body = claimsJws.getBody();
        return body; //Json对象
    }

    //重写方法直接返回类型
    public <T> T parseToken(String Token,Class<T> clazz)
    {
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(Token)
                .getBody();
        return JSON.parseObject(body.getSubject(),clazz);
    }
}