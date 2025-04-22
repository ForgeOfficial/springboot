package com.maxime.gradesmanagement.service;

import com.maxime.gradesmanagement.dto.CourseDto;
import com.maxime.gradesmanagement.entity.Course;
import com.maxime.gradesmanagement.exception.GlobalExceptionHandler.ResourceNotFoundException;
import com.maxime.gradesmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec l'ID: " + id));
    }

    public Course updateCourse(Long id, CourseDto courseDto) {
        Course course = getCourseById(id);
        course.setName(courseDto.getName());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours non trouvé avec l'ID: " + id);
        }
        courseRepository.deleteById(id);
    }
}