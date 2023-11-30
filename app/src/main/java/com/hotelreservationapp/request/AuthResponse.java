package com.hotelreservationapp.request;

public class AuthResponse {
    private  String accessToken;
    private  String refreshToken;
    private int code;
    private  String message;

    public AuthResponse(String accessToken, String refreshToken, int code, String message) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.code = code;
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
