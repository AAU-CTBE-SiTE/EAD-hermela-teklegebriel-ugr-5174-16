package com.craft.studentapiapplication.repository;

import com.craft.studentapiapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
