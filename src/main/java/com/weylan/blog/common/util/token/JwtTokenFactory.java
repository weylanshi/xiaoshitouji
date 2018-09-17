package com.weylan.blog.common.util.token;

import com.alibaba.fastjson.JSON;
import com.weylan.blog.config.jwt.JwtSetting;
import com.weylan.blog.model.user.vo.UserContext;
import com.weylan.blog.common.util.token.model.AccessToken;
import com.weylan.blog.common.util.token.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                        .plusMinutes(jwtSetting.getTokenExpirationTime())
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
}
