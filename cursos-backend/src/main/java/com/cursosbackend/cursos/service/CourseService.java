package com.cursosbackend.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursosbackend.cursos.domain.Course;
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

	public Optional<Course> getCourseByID(Long id) {
		return courseRepository.findById(id);
	}

	public Course updateCourse(Long id, Course course) {
		Course tempCourse = this.getCourseByID(id).orElse(null);
		
		if (tempCourse != null) {
			tempCourse.setName(course.getName());
			tempCourse.setCategory(course.getCategory());
			return courseRepository.save(course);
		}
		return null;
	}
}
