package com.craft.lab5.service;

import com.craft.lab5.aspect.RequireAdmin;
import com.craft.lab5.aspect.SimpleCache;
import com.craft.lab5.aspect.LogExecutionTime;
import com.craft.lab5.exception.StudentNotFoundException;
import com.craft.lab5.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "Hermela", 21, 90.5),
            new Student(2L, "Abel", 22, 78.0)
    ));

    @LogExecutionTime
    public List<Student> getAll() {
        return students;
    }

    public Student getById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student create(Student student) {
        students.add(student);
        return student;
    }

    public Student update(Long id, Student updated) {
        Student s = getById(id);
        s.setName(updated.getName());
        s.setAge(updated.getAge());
        s.setGrade(updated.getGrade());
        return s;
    }

    @RequireAdmin
    public void delete(Long id) {
        students.remove(getById(id));
    }

    @SimpleCache
    @LogExecutionTime
    public double getAverageGrade() {
        try {
            Thread.sleep(500);   // pretend this is expensive
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return students.stream().mapToDouble(Student::getGrade).average().orElse(0);
    }
}