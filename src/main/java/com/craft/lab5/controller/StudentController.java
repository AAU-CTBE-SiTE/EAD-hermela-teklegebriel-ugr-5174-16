package com.craft.lab5.controller;

import com.craft.lab5.model.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "Hermela", 21, 90.5),
            new Student(2L, "Abel", 22, 78.0)
    ));

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        for (Student existing : students) {
            if (existing.getId().equals(student.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(updated.getName());
                student.setAge(updated.getAge());
                student.setGrade(updated.getGrade());
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}