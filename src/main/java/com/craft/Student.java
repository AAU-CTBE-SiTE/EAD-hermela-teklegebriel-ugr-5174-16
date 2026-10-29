package com.craft;

import lombok.Data;
import java.util.List;

@Data
public class Student {

    private int id;
    private String name;
    private int age;
    private Address address;
    private List<Subject> subjects;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
