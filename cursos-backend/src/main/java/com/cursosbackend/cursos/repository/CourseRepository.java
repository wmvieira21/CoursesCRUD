package com.cursosbackend.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosbackend.cursos.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
