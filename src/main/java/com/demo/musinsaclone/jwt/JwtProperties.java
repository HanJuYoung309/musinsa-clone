package com.demo.musinsaclone.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String issuer,
        long accessTokenValiditySeconds,
        long refreshTokenValiditySeconds,
        String secret
) {}