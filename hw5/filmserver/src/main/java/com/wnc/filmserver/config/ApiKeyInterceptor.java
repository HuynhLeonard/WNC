package com.wnc.filmserver.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestApiKey = request.getHeader("X-API-KEY");
        String requestApiSecret = request.getHeader("X-API-SECRET");

        if (apiKey.equals(requestApiKey) && apiSecret.equals(requestApiSecret)) {
            return true; // Cho phép tiếp tục xử lý yêu cầu
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false; // Yêu cầu không hợp lệ
    }
}
