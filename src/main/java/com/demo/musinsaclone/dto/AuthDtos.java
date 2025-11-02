package com.demo.musinsaclone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDtos {

    public record SignupRequest(
            @Email @NotBlank String email,
            @Size(min = 8, message = "비밀번호는 8자 이상") String password,
            @NotBlank String name
    ) {}

    public record LoginRequest(
            @Email @NotBlank String email,
            @NotBlank String password
    ) {}

    public record TokenResponse(
            String accessToken,
            String refreshToken
    ) {}

    public record RefreshRequest(
            @NotBlank String refreshToken
    ) {}

    public record MeResponse(
            Long id, String email, String name, String role
    ) {}
}