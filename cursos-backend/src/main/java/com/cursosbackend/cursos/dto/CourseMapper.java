package com.cursosbackend.cursos.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.domain.Lesson;
import com.cursosbackend.cursos.enums.Category;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		List<LessonDTO> lessons = course.getLessons().stream()
				.map(c -> new LessonDTO(c.getId(), c.getName(), c.getYoutubeURL())).toList();

		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
	}

	public Course toEntity(CourseDTO course) {
		return new Course(course.id(), course.name(), converteToCategory(course.category()));
	}

	public Category converteToCategory(String value) {
		return switch (value) {
		case "Back-end" -> Category.BACKEND;
		case "Front-end" -> Category.FRONTEND;
		default -> throw new IllegalArgumentException("Category invalid: " + value);
		};
	}
}
