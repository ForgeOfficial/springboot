package com.maxime.gradesmanagement.service;

import com.maxime.gradesmanagement.dto.GradeDto;
import com.maxime.gradesmanagement.entity.Course;
import com.maxime.gradesmanagement.entity.Grade;
import com.maxime.gradesmanagement.entity.Student;
import com.maxime.gradesmanagement.exception.GlobalExceptionHandler.ResourceNotFoundException;
import com.maxime.gradesmanagement.repository.CourseRepository;
import com.maxime.gradesmanagement.repository.GradeRepository;
import com.maxime.gradesmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeService(GradeRepository gradeRepository,
                        StudentRepository studentRepository,
                        CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Grade createGrade(GradeDto gradeDto) {
        Student student = studentRepository.findById(gradeDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + gradeDto.getStudentId()));

        Course course = courseRepository.findById(gradeDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec l'ID: " + gradeDto.getCourseId()));

        Grade grade = new Grade();
        grade.setValue(gradeDto.getValue());
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Transactional(readOnly = true)
    public List<Grade> getGradesByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + studentId);
        }
        return gradeRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Grade> getGradesByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Cours non trouvé avec l'ID: " + courseId);
        }
        return gradeRepository.findByCourseId(courseId);
    }

    public Grade updateGrade(Long id, GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note non trouvée avec l'ID: " + id));

        Student student = studentRepository.findById(gradeDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + gradeDto.getStudentId()));

        Course course = courseRepository.findById(gradeDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec l'ID: " + gradeDto.getCourseId()));

        grade.setValue(gradeDto.getValue());
        grade.setStudent(student);
        grade.setCourse(course);

        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note non trouvée avec l'ID: " + id);
        }
        gradeRepository.deleteById(id);
    }
}