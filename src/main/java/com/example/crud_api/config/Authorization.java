package com.example.crud_api.config;

import com.example.crud_api.pojo.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Authorization {
    public static BaseResponse validateTokenAndGetResponse(String token) {
        if (token == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            baseResponse.setMessage("Authorization Bearer Token is Empty");
            return baseResponse;
        }

        if (!validateToken(token)) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            baseResponse.setMessage("Unauthorized, Invalid Bearer Token");
            return baseResponse;
        }

        return null;
    }

    private static boolean validateToken(String token) {
        return JWTConfig.validateToken(token.substring(7));
    }
}
