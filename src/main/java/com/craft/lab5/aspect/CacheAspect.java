package com.craft.lab5.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Order(3)
public class CacheAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheAspect.class);
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("@annotation(com.craft.lab5.aspect.SimpleCache)")
    public Object cacheResult(ProceedingJoinPoint pjp) throws Throwable {
        String key = pjp.getSignature().toShortString() + Arrays.toString(pjp.getArgs());

        if (cache.containsKey(key)) {
            log.info("CACHE   -> HIT  {}", key);
            return cache.get(key);          // the real method is NOT called
        }
        log.info("CACHE   -> MISS {}", key);
        Object result = pjp.proceed();      // call the real method
        cache.put(key, result);
        return result;
    }

    @AfterReturning("execution(* com.craft.lab5.service.StudentService.create(..)) || " +
            "execution(* com.craft.lab5.service.StudentService.update(..)) || " +
            "execution(* com.craft.lab5.service.StudentService.delete(..))")
    public void evict() {
        cache.clear();
        log.info("CACHE   -> cleared after a write");
    }
}