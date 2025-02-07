package com.amigoscode.courseenrollment;

import com.amigoscode.course.Course;
import com.amigoscode.student.Student;
import jakarta.persistence.*;

@Entity
public class CourseEnrollment {

    @EmbeddedId
    private CourseEnrollmentId courseEnrollmentId;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrollment_student_id_fk"
            )
    )
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrollment_course_id_fk"
            )
    )
    @MapsId("courseId")
    private Course course;

    public CourseEnrollment() {}

    public CourseEnrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.courseEnrollmentId = new CourseEnrollmentId(student.getId(), course.getId());
    }

    public CourseEnrollmentId getCourseEnrollmentId() {
        return courseEnrollmentId;
    }

    public void setCourseEnrollmentId(CourseEnrollmentId courseEnrollmentId) {
        this.courseEnrollmentId = courseEnrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
