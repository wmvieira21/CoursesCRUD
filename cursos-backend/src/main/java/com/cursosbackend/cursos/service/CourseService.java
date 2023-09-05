package com.cursosbackend.cursos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.dto.CourseDTO;
import com.cursosbackend.cursos.dto.CourseMapper;
import com.cursosbackend.cursos.exceptions.ObjetNotFound;
import com.cursosbackend.cursos.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public List<CourseDTO> findAllCourses() {
		return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
	}

	public CourseDTO create(CourseDTO courseDTO) {
		Course course = courseMapper.toEntity(courseDTO);
		course = courseRepository.save(course);
		return courseMapper.toDTO(course);
	}

	public CourseDTO findByID(Long id) {
		return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(() -> new ObjetNotFound(id));
	}

	public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
		CourseDTO tempCourse = this.findByID(id);

		Course course = courseMapper.toEntity(courseDTO);

		course = courseRepository.save(course);
		return courseMapper.toDTO(course);
	}

	public void delete(Long id) {
		CourseDTO dto = this.findByID(id);
		courseRepository.delete(courseMapper.toEntity(dto));
	}
}
