package com.weylan.blog.common.util.token.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class AccessToken implements JwtToken {

    private String rawToken;
    @JsonIgnore  private Claims claims;

    public AccessToken(String rawToken, Claims claims) {
        this.rawToken = rawToken;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return rawToken;
    }
}
