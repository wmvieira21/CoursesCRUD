package com.cursosbackend.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.exceptions.ObjetNotFound;
import com.cursosbackend.cursos.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	public Course create(Course course) {
		return courseRepository.save(course);
	}

	public Course findByID(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new ObjetNotFound(id));
	}

	public Course updateCourse(Long id, Course course) {
		Course tempCourse = this.findByID(id);

		if (tempCourse != null) {
			tempCourse.setName(course.getName());
			tempCourse.setCategory(course.getCategory());
			return courseRepository.save(course);
		}
		return null;
	}

	public void delete(Long id) {
		courseRepository.delete(this.findByID(id));;
	}
}
