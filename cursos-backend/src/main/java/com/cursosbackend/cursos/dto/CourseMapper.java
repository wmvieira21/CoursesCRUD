package com.cursosbackend.cursos.dto;

import org.springframework.stereotype.Component;

import com.cursosbackend.cursos.domain.Course;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		return new CourseDTO(course.getId(), course.getName(), course.getCategory());
	}

	public Course toEntity(CourseDTO course) {
		return new Course(course.id(), course.name(), course.category());
	}
}
