package com.amigoscode;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name = "student"
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            nullable = false
    )
    private Integer age;
    @Column(
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String email;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private StudentIdCard studentIdCard;

    public Student() {
    }

    public Student(Long id,
                   String firstName,
                   String lastName,
                   Integer age,
                   String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public Student(String firstName,
                   String lastName,
                   Integer age,
                   String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(age, student.age) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email);
    }

}
