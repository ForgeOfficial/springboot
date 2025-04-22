package com.maxime.gradesmanagement.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class GradeDto {
    @NotNull(message = "La note ne peut pas être vide")
    @DecimalMin(value = "0.0", message = "La note ne peut pas être en dessous de 0.0")
    @DecimalMax(value = "20.0", message = "La note ne peut pas dépasser 20.0")
    private Float value;

    @NotNull(message = "L'id de l'étudiant est requis")
    private Long studentId;

    @NotNull(message = "L'id du cours est requis")
    private Long courseId;

    // Getters & Setters
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
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
}