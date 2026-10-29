package com.craft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        Student student = context.getBean("student", Student.class);
        student.getHello();

        context.close();
    }
}