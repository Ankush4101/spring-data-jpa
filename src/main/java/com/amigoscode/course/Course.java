package com.amigoscode.course;

import com.amigoscode.courseenrollment.CourseEnrollment;
import com.amigoscode.student.Student;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;

    @Column(
            columnDefinition = "TEXT",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            columnDefinition = "TEXT",
            nullable = false
    )
    private String department;

    @OneToMany(
            cascade = {CascadeType.PERSIST},
            mappedBy = "course"
    )
    private Set<CourseEnrollment> courseEnrollments = new HashSet<>();

    public Course() {}

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(Set<CourseEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    //    public void enroll(Student student) {
//        students.add(student);
//    }
//
//    public void removeStudent(Student student) {
//        students.remove(student);
//    }
}
