
package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class App {

    // Create the EntityManagerFactory once for the application
    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    // Create an EntityManager for each operation
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    // CREATE

    public static void createStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(student);
        transaction.commit();

        entityManager.close();
    }


    // READ


    // Find one student by ID
    public static Student getStudentById(int id) {
        EntityManager entityManager = getEntityManager();

        Student student = entityManager.find(Student.class, id);

        entityManager.close();
        return student;
    }

    // Find all students using JPQL
    public static List<Student> getAllStudents() {
        EntityManager entityManager = getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s", Student.class);

        List<Student> students = query.getResultList();

        entityManager.close();
        return students;
    }


    // UPDATE


    // Update only the student's name
    public static void updateStudentName(String newName, int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Student student = entityManager.find(Student.class, id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
            transaction.rollback();
            entityManager.close();
            return;
        }

        student.setName(newName);

        transaction.commit();
        entityManager.close();
    }

    // Update the student's name and technology
    public static void updateStudentNameAndTech(
            String newName, String newTech, int id) {

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Student student = entityManager.find(Student.class, id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
            transaction.rollback();
            entityManager.close();
            return;
        }

        student.setName(newName);
        student.setTech(newTech);

        transaction.commit();
        entityManager.close();
    }

    // Update technology using a JPQL UPDATE query
    public static int updateStudentTech(int id, String newTech) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery(
                "UPDATE Student s SET s.tech = :newTech WHERE s.id = :id");

        query.setParameter("newTech", newTech);
        query.setParameter("id", id);

        int updatedCount = query.executeUpdate();

        transaction.commit();
        entityManager.close();

        return updatedCount;
    }


    // DELETE

    public static void deleteStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Student student = entityManager.find(Student.class, id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
            transaction.rollback();
            entityManager.close();
            return;
        }

        entityManager.remove(student);

        transaction.commit();
        entityManager.close();
    }


    // MAIN: DEMONSTRATE CRUD OPERATIONS

    public static void main(String[] args) {

        try {
            // ---------- CREATE ----------
            System.out.println("\n========== C: CREATE ==========");

            Student s1 = new Student();
            s1.setName("Selamawit Bekele");
            s1.setTech("Flutter");
            createStudent(s1);

            Student s2 = new Student();
            s2.setName("Nahom Tesfaye");
            s2.setTech("JavaScript");
            createStudent(s2);

            System.out.println(">>> Created student: " + s1);
            System.out.println(">>> Created student: " + s2);

            // ---------- READ ----------
            System.out.println("\n========== R: READ ==========");

            System.out.println(">>> Student by ID "
                    + s1.getId() + ": " + getStudentById(s1.getId()));

            System.out.println(">>> All students:");
            getAllStudents().forEach(
                    student -> System.out.println("      " + student));

            // ---------- UPDATE ----------
            System.out.println("\n========== U: UPDATE ==========");

            updateStudentName("Mekdes Alemu", s1.getId());

            System.out.println(">>> After changing name: "
                    + getStudentById(s1.getId()));

            updateStudentNameAndTech(
                    "Dawit Girma", "Python", s1.getId());

            System.out.println(">>> After changing name and technology: "
                    + getStudentById(s1.getId()));

            int rows = updateStudentTech(
                    s1.getId(), "Spring Framework");

            System.out.println(">>> JPQL changed " + rows + " row(s): "
                    + getStudentById(s1.getId()));

            // ---------- DELETE ----------
            System.out.println("\n========== D: DELETE ==========");

            deleteStudentById(s2.getId());

            System.out.println(">>> Student after deletion (ID "
                    + s2.getId() + "): "
                    + getStudentById(s2.getId()));

            System.out.println(">>> Remaining students:");
            getAllStudents().forEach(
                    student -> System.out.println("      " + student));

        } finally {
            // Close the factory when the application finishes
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}
