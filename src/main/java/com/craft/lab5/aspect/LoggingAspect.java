package com.craft.lab5.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.craft.lab5.service.*.*(..))")
    public void serviceMethods() {
    }

    @Before("serviceMethods()")
    public void before(JoinPoint jp) {
        log.info("BEFORE   -> {}() args={}", jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info("RETURNED -> {}() result={}", jp.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex) {
        log.error("THREW    -> {}() exception={}", jp.getSignature().getName(), ex.getMessage());
    }

    @After("serviceMethods()")
    public void after(JoinPoint jp) {
        log.info("AFTER    -> {}() finished", jp.getSignature().getName());
    }
}