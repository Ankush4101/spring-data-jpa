package com.amigoscode.student;

import com.amigoscode.book.Book;
import com.amigoscode.courseenrollment.CourseEnrollment;

import java.time.Instant;
import java.util.Set;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        Set<Book> books,
        Set<CourseEnrollment> courseEnrollments,
        Instant createdAt
) {
}
