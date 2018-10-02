package com.weylan.blog.common.util.token;

import com.alibaba.fastjson.JSON;
import com.weylan.blog.config.jwt.JwtSetting;
import com.weylan.blog.model.user.vo.UserContent;
import com.weylan.blog.common.util.token.model.AccessToken;
import com.weylan.blog.common.util.token.model.JwtToken;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenFactory<T> {

    private final JwtSetting jwtSetting;

    @Autowired
    public JwtTokenFactory(JwtSetting jwtSetting) {
        this.jwtSetting = jwtSetting;
    }


    public AccessToken createAccessToken(UserContent userContent) {
        if (StringUtils.isBlank(userContent.getUserId())) {
            throw new IllegalArgumentException("can not create token without userId");
        }


        Claims claims = Jwts.claims().setSubject(userContent.getUserId());
        claims.put("userInfo", JSON.toJSONString(userContent));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSetting.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(jwtSetting.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSetting.getTokenSigningKey())
                .compact();

        return new AccessToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContent userContent) {

        if (StringUtils.isBlank(userContent.getUserId())) {
            throw new IllegalArgumentException("can not create token without userId");
        }
        Claims claims = Jwts.claims().setSubject(userContent.getUserId());

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSetting.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(jwtSetting.getRefreshExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSetting.getTokenSigningKey())
                .compact();

        return new AccessToken(token, claims);
    }


    public String getUserInfoFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSetting.getTokenSigningKey()).parseClaimsJws(token);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        Object userInfo = body.get("userInfo");
        return userInfo.toString();
    }

    public T getUserInfoFromToken(String token, Class<T> clazz) {
        String userInfo = getUserInfoFromToken(token);
        return JSON.toJavaObject(JSON.parseObject(userInfo), clazz);
    }

    public static void main(String[] args) throws InterruptedException {
        UserContent userContent = new UserContent();
        userContent.setUserId("2131");
        userContent.setUserName("weylan");
        JwtSetting setting = new JwtSetting();
        setting.setTokenExpirationTime(10000000);
        setting.setRefreshExpirationTime(2);
        setting.setTokenIssuer("http://weypage.com");
        setting.setTokenSigningKey("2XT2I207n7WphyCiOZCJ2A==");
        JwtTokenFactory tokenFactory = new JwtTokenFactory(setting);
        AccessToken accessToken = tokenFactory.createAccessToken(userContent);
        String token = accessToken.getToken();
        System.out.println(token);
        Thread.sleep(2000);

        String userInfoFromToken = tokenFactory.getUserInfoFromToken(token);
        System.out.println(userInfoFromToken);

    }
}
