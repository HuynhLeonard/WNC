package com.wnc.filmserver.config;

import com.wnc.filmserver.dto.ApiResponse;
import com.wnc.filmserver.exception.AppException;
import com.wnc.filmserver.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    long minuteThreshold = 1;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AppException {

        String token = request.getHeader("token");
        String time = request.getHeader("time");
        if(token == null || token.isEmpty() || time == null || time.isEmpty()){
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        String requestUrl = request.getRequestURL().toString();
        String secretToken = generateToken(requestUrl + time + apiSecret);
        // compare time between 2 date time
        LocalDateTime clientTime = LocalDateTime.parse(time);
        LocalDateTime currentTime = LocalDateTime.now();
        long minuteDifference = Duration.between(clientTime,currentTime).toMinutes();
        if (minuteDifference > minuteThreshold) {
            throw new AppException(ErrorCode.INVALID_TIME);
        }

        if (token.equals(secretToken)) {
            return true;
        }
        throw new AppException(ErrorCode.INVALID_TOKEN);
    }

    public String generateToken(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
