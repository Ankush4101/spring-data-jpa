package com.amigoscode.book;

import com.amigoscode.student.Student;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            nullable = false
    )
    private ZonedDateTime createdAt;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "book_student_id_fk"
            ),
            nullable = false,
            unique = true
    )
    private Student student;

    public Book() {
    }

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
