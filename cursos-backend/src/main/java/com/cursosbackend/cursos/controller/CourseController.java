package com.cursosbackend.cursos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> listCourses() {
		return courseService.findAllCourses();
	}

}
