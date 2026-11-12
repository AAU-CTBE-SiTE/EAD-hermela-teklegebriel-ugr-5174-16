package com.craft.lab5.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(0)   // runs before every other aspect
public class SecurityAspect {

    private static final Logger log = LoggerFactory.getLogger(SecurityAspect.class);

    @Before("@annotation(com.craft.lab5.aspect.RequireAdmin)")
    public void checkAdmin(JoinPoint jp) {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String role = (attrs == null) ? null : attrs.getRequest().getHeader("X-Role");

        if (!"ADMIN".equals(role)) {
            log.warn("SECURITY -> access denied to {}()", jp.getSignature().getName());
            throw new SecurityException("Admin role required for " + jp.getSignature().getName());
        }
        log.info("SECURITY -> admin access granted to {}()", jp.getSignature().getName());
    }
}