package com.maxime.gradesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseDto {
    @NotBlank(message = "Le nom du cours ne doit pas être vide")
    @Size(min = 5, max = 100, message = "Le nom du cours doit contenir entre 5 et 100 caractères")
    private String name;

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}