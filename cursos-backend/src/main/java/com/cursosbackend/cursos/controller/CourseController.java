package com.cursosbackend.cursos.controller;

import java.io.Console;
import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	//@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Course> createCourse(@RequestBody Course course){
		Course c = this.courseService.create(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
}
