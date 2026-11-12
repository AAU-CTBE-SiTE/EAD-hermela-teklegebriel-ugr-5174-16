package com.craft.lab5.aspect;

import com.craft.lab5.service.StudentService;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProxyInfoRunner implements CommandLineRunner {

    private final StudentService service;

    public ProxyInfoRunner(StudentService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        System.out.println("Is AOP proxy?   " + AopUtils.isAopProxy(service));
        System.out.println("Is CGLIB proxy? " + AopUtils.isCglibProxy(service));
        System.out.println("Proxy class:    " + service.getClass().getName());
        System.out.println("Target class:   " + AopUtils.getTargetClass(service).getName());
    }
}