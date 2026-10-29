package com.craft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        Student student = context.getBean("student", Student.class);
        System.out.println(student);

        context.close();
    }
}
