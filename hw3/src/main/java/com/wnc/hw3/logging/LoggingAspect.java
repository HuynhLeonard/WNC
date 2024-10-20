package com.wnc.hw3.logging;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import java.util.Arrays;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.wnc.hw3.controller..*(..))") // Adjust this to your package structure
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = (HttpServletRequest) attributes.getRequest();

            logger.info("Request: {} {} with parameters {}", request.getMethod(), request.getRequestURI(), Arrays.toString(joinPoint.getArgs()));
        }

        Object result = joinPoint.proceed();

        logger.info("Response: {}", result);

        return result;
    }
}

