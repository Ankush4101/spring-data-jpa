package com.amigoscode.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(s -> new StudentResponse(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getBooks(),
                        s.getCourseEnrollments(),
                        s.getCreatedAt()
                ))
                .toList();
    }

    @PostMapping
    public void addStudent(Student student) {

    }

}
