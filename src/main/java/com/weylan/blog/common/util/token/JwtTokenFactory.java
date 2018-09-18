package com.weylan.blog.common.util.token;

import com.alibaba.fastjson.JSON;
import com.weylan.blog.config.jwt.JwtSetting;
import com.weylan.blog.model.user.vo.UserContext;
import com.weylan.blog.common.util.token.model.AccessToken;
import com.weylan.blog.common.util.token.model.JwtToken;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenFactory {

    private final JwtSetting jwtSetting;

    @Autowired
    public JwtTokenFactory(JwtSetting jwtSetting) {
        this.jwtSetting = jwtSetting;
    }


    public AccessToken createAccessToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUserId())) {
            throw new IllegalArgumentException("can not create token without userId");
        }


        Claims claims = Jwts.claims().setSubject(userContext.getUserId());
        claims.put("userInfo", JSON.toJSONString(userContext));

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

    public JwtToken createRefreshToken(UserContext userContext) {

        if (StringUtils.isBlank(userContext.getUserId())) {
            throw new IllegalArgumentException("can not create token without userId");
        }
        Claims claims = Jwts.claims().setSubject(userContext.getUserId());

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

        return body.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        UserContext userContext = new UserContext();
        userContext.setUserId("2131");
        userContext.setUserName("weylan");
        JwtSetting setting = new JwtSetting();
        setting.setTokenExpirationTime(10);
        setting.setRefreshExpirationTime(2);
        setting.setTokenIssuer("weylan");
        setting.setTokenSigningKey("1234fasdfaaaaa");
        JwtTokenFactory tokenFactory = new JwtTokenFactory(setting);
        AccessToken accessToken = tokenFactory.createAccessToken(userContext);
        String token = accessToken.getToken();
        System.out.println(token.length());
//        Thread.sleep(2000);

        String userInfoFromToken = tokenFactory.getUserInfoFromToken(token);
        System.out.println(userInfoFromToken);

    }
}
