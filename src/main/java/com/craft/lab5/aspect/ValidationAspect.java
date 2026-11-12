package com.craft.lab5.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ValidationAspect {

    @Before("execution(* com.craft.lab5.service.StudentService.getById(..)) && args(id)")
    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Student id must be positive");
        }
    }
}