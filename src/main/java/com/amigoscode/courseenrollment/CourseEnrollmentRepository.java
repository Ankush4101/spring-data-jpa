package com.amigoscode.courseenrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrollmentRepository
    extends JpaRepository<CourseEnrollment, CourseEnrollmentId> {
}
