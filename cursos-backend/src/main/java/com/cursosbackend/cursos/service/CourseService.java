package com.cursosbackend.cursos.service;

import java.util.List;

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

}
