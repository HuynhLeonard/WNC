package com.wnc.hw3.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HttpServletRequest request;

    @Pointcut("within(com.wnc.hw3.controller..*) || within(com.wnc.hw3.exception..*) || within(com.wnc.hw3.service..*)")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("Request: {} {} with parameters {}", request.getMethod(), request.getRequestURI(), Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            logger.info("Method result: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Exception in method: {} - {}", joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        }
    }
}
