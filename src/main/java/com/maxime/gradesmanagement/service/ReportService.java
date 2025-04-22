package com.maxime.gradesmanagement.service;

import com.maxime.gradesmanagement.exception.GlobalExceptionHandler.ResourceNotFoundException;
import com.maxime.gradesmanagement.repository.CourseRepository;
import com.maxime.gradesmanagement.repository.GradeRepository;
import com.maxime.gradesmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ReportService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public ReportService(GradeRepository gradeRepository,
                         StudentRepository studentRepository,
                         CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Map<String, Object> getAverageGradeByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Cours non trouvé avec l'ID: " + courseId);
        }

        Double average = gradeRepository.getAverageByCourseId(courseId);
        if (average == null) {
            throw new ResourceNotFoundException("Aucune note trouvée pour ce cours");
        }

        Map<String, Object> report = new HashMap<>();
        report.put("courseId", courseId);
        report.put("moyenne", average);
        return report;
    }

    public Map<String, Object> getAverageGradeByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + studentId);
        }

        Double average = gradeRepository.getAverageByStudentId(studentId);
        if (average == null) {
            throw new ResourceNotFoundException("Aucune note trouvée pour cet étudiant");
        }

        Map<String, Object> report = new HashMap<>();
        report.put("studentId", studentId);
        report.put("moyenne", average);
        return report;
    }
}