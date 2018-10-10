package com.yushu.utils;

import com.yushu.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
//    JWT格式组成 头部、负载、签名
//    header+payload+signature
//
//    头部：主要是描述签名算法
//    负载：主要描述是加密对象的信息，如用户的id等，也可以加些规范里面的东西，如iss签发者，exp 过期时间，sub 面向的用户
//    签名：主要是把前面两部分进行加密，防止别人拿到token进行base解密后篡改token


    //发行者
    public static final String subject = "zhaojianfei";
    public static final String appsecret = "yushu";

    public static String generateJwt(User user){
        String jwt = Jwts.builder().setSubject(subject)
                .claim("id",user.getId())
                .claim("name",user.getName())
                .claim("headImg",user.getHeadImg())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256,appsecret).compact();//最后一下是压缩
        return jwt;
    }

    public static Claims decodeJwt(String jwt){
        try{
            Claims claims = Jwts.parser().setSigningKey(appsecret).parseClaimsJws(jwt).getBody();
//        Claims claims = Jwts.parser().setSigningKey(appsecret).parseClaimsJws(jwt).getHeader();
//        Claims claims = Jwts.parser().setSigningKey(appsecret).parseClaimsJws(jwt).getSignature();
            return claims;
        }catch (Exception ex){
            return null;
        }



    }
}
