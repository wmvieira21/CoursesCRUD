package com.cursosbackend.cursos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<Course> findByID(@PathVariable Long id) {
		return courseService.findByID(id).map(data -> ResponseEntity.ok().body(data))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	// @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course c = this.courseService.create(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
		Course courseUpdated = courseService.updateCourse(id, course);

		if (courseUpdated != null) {
			return ResponseEntity.ok().body(courseUpdated);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return this.courseService.findByID(id).map(c -> {
			this.courseService.delete(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().<Void>build());
	}
}
