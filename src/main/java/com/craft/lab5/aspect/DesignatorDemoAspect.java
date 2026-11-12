package com.craft.lab5.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DesignatorDemoAspect {

    private static final Logger log = LoggerFactory.getLogger(DesignatorDemoAspect.class);

    // every method of every class in the controller package
    @Before("within(com.craft.lab5.controller..*)")
    public void withinDemo(JoinPoint jp) {
        log.info("WITHIN  -> controller method {}()", jp.getSignature().getName());
    }

    // only the bean named studentService, only its create method
    @Before("bean(studentService) && execution(* create(..))")
    public void beanDemo(JoinPoint jp) {
        log.info("BEAN    -> studentService.{}() called", jp.getSignature().getName());
    }

    // any method on an object whose target class is StudentService
    @Before("target(com.craft.lab5.service.StudentService) && execution(* delete(..))")
    public void targetDemo(JoinPoint jp) {
        log.info("TARGET  -> delete called on {}", jp.getTarget().getClass().getSimpleName());
    }
}