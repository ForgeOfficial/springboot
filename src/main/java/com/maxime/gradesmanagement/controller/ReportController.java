package com.maxime.gradesmanagement.controller;

import com.maxime.gradesmanagement.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Map<String, Object>> getCourseReport(@PathVariable Long courseId) {
        Map<String, Object> report = reportService.getAverageGradeByCourse(courseId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentReport(@PathVariable Long studentId) {
        Map<String, Object> report = reportService.getAverageGradeByStudent(studentId);
        return ResponseEntity.ok(report);
    }
}