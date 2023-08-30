package com.cursosbackend.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.dto.CourseDTO;
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

	public CourseDTO create(CourseDTO courseDTO) {
		Course course = new Course(null, courseDTO.name(), courseDTO.category());
		course = courseRepository.save(course);
		return new CourseDTO(course.getId(), course.getName(), course.getCategory());
	}

	public CourseDTO findByID(Long id) {
		return courseRepository.findById(id).map(c -> new CourseDTO(c.getId(), c.getName(), c.getCategory()))
				.orElseThrow(() -> new ObjetNotFound(id));
	}

	public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
		CourseDTO tempCourse = this.findByID(id);
		Course course = courseRepository.save(new Course(tempCourse.id(), courseDTO.name(), courseDTO.category()));
		return new CourseDTO(id, course.getName(), course.getCategory());
	}

	public void delete(Long id) {
		CourseDTO dto = this.findByID(id);
		courseRepository.delete(new Course(dto.id(), dto.name(), dto.category()));
	}
}
