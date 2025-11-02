package com.demo.musinsaclone.service;


import com.demo.musinsaclone.domain.RefreshToken;
import com.demo.musinsaclone.domain.User;
import com.demo.musinsaclone.dto.AuthDtos;
import com.demo.musinsaclone.jwt.JwtTokenProvider;
import com.demo.musinsaclone.repository.RefreshTokenRepository;
import com.demo.musinsaclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final com.demo.musinsaclone.jwt.JwtProperties props;

    public void signup(AuthDtos.SignupRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        var user = User.builder()
                .email(req.email())
                .passwordHash(passwordEncoder.encode(req.password()))
                .name(req.name())
                .role("USER")
                .build();
        userRepository.save(user);
    }

    public AuthDtos.TokenResponse login(AuthDtos.LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        var user = userRepository.findByEmail(req.email()).orElseThrow();
        String access = jwtTokenProvider.createAccessToken(user);
        String refresh = createRefreshToken(user);
        return new AuthDtos.TokenResponse(access, refresh);
    }

    public AuthDtos.TokenResponse refresh(AuthDtos.RefreshRequest req) {
        RefreshToken rt = refreshTokenRepository.findByToken(req.refreshToken())
                .orElseThrow(() -> new IllegalArgumentException("리프레시 토큰이 유효하지 않습니다."));
        if (rt.isRevoked() || rt.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("리프레시 토큰이 만료/폐기되었습니다.");
        }
        var user = rt.getUser();
        // 토큰 회전(rotate): 기존 토큰 revoke
        rt.setRevoked(true);
        refreshTokenRepository.save(rt);

        String newAccess = jwtTokenProvider.createAccessToken(user);
        String newRefresh = createRefreshToken(user);
        return new AuthDtos.TokenResponse(newAccess, newRefresh);
    }

    public void logout(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken).ifPresent(rt -> {
            rt.setRevoked(true);
            refreshTokenRepository.save(rt);
        });
    }

    private String createRefreshToken(User user) {
        String token = java.util.UUID.randomUUID().toString();
        var rt = RefreshToken.builder()
                .user(user)
                .token(token)
                .expiresAt(Instant.now().plusSeconds(props.refreshTokenValiditySeconds()))
                .revoked(false)
                .build();
        refreshTokenRepository.save(rt);
        return token;
    }
}