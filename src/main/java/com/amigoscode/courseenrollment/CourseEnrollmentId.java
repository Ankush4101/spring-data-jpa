package com.amigoscode.courseenrollment;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CourseEnrollmentId {
    private Long studentId;
    private Long courseId;

    public CourseEnrollmentId() {}

    public CourseEnrollmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollmentId that = (CourseEnrollmentId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Override
    public String toString() {
        return "EnrollmentId{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
