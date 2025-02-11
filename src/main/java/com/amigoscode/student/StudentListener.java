package com.amigoscode.student;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;

public class StudentListener {

    @PostLoad
    public void postLoad(Student student) {
        System.out.println("from student listener");
        System.out.println("postLoad student " + student.getId());
    }

    @PrePersist
    public void prePersist(Student student) {
        System.out.println("from student listener");
        System.out.println("prePersist student " + student.getId());
    }
}
