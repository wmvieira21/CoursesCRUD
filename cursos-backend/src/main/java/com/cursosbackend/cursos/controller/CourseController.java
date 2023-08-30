package com.cursosbackend.cursos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
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

	@GetMapping(value = "/{id}")
	public Course findByID(@PathVariable @NotNull @Positive Long id) {
		return courseService.findByID(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course createCourse(@RequestBody @Valid Course course) {
		Course c = this.courseService.create(course);
		return c;
	}

	@PutMapping(value = "/{id}")
	public Course updateCourse(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
		Course courseUpdated = courseService.updateCourse(id, course);

		return courseUpdated;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		this.courseService.delete(id);
	}
}
