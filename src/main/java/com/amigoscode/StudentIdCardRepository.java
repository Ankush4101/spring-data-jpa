package com.amigoscode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentIdCardRepository
    extends JpaRepository<StudentIdCard, Long> {

    @Query("SELECT c FROM StudentIdCard c JOIN FETCH c.student WHERE c.id = ?1")
    Optional<StudentIdCard> findStudentIdCardByIdWithStudent(Long id);
}
