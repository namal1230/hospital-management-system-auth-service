package com.example.AuthService.dto;

public class LoginRequest {
    private String username;
    private String password;

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }
}
