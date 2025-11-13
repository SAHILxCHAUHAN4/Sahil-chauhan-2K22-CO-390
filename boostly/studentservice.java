package com.boostly.boostly.service;

import com.boostly.boostly.dto.StudentDTO;
import com.boostly.boostly.exception.ResourceNotFoundException;
import com.boostly.boostly.exception.ValidationException;
import com.boostly.boostly.model.Student;
import com.boostly.boostly.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student createStudent(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new ValidationException("Student with email " + studentDTO.getEmail() + " already exists.");
        }
        Student student = new Student(studentDTO.getEmail(), studentDTO.getName());
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Transactional
    public void resetMonthlyAllowances() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            // Calculate carry-over
            int unusedCredits = student.getSendingBalance();
            int carryOver = Math.min(unusedCredits, 50);

            // Reset balance
            student.setSendingBalance(100 + carryOver);
            studentRepository.save(student);
        }
        System.out.println("Monthly credit allowances reset for " + students.size() + " students.");
    }
}