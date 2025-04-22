package com.maxime.gradesmanagement.controller;


import com.maxime.gradesmanagement.dto.GradeDto;
import com.maxime.gradesmanagement.entity.Grade;
import com.maxime.gradesmanagement.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody GradeDto gradeDto) {
        Grade grade = gradeService.createGrade(gradeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(grade);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudentId(@PathVariable Long studentId) {
        return gradeService.getGradesByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Grade> getGradesByCourseId(@PathVariable Long courseId) {
        return gradeService.getGradesByCourseId(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody GradeDto gradeDto) {
        Grade grade = gradeService.updateGrade(id, gradeDto);
        return ResponseEntity.ok(grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}