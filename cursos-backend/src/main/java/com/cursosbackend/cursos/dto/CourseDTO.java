package com.cursosbackend.cursos.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
		@JsonProperty("_id") Long id, 
		@NotNull @NotBlank @Length(min = 5, max = 100) String name, 
		@NotNull @Length(max = 50) String category,
		List<LessonDTO> lessons) {}