package com.maxime.gradesmanagement.repository;

import com.maxime.gradesmanagement.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByCourseId(Long courseId);

    @Query("SELECT AVG(g.value) FROM Grade g WHERE g.student.id = :studentId")
    Double getAverageByStudentId(Long studentId);

    @Query("SELECT AVG(g.value) FROM Grade g WHERE g.course.id = :courseId")
    Double getAverageByCourseId(Long courseId);
}