package com.cursosbackend.cursos;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.repository.CourseRepository;

@SpringBootApplication
public class CursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initdatabase(CourseRepository courseRepository) {
		return args -> {
			Course course = new Course(null, "Angular", "frontend");
			Course course1 = new Course(null, "Java", "backend");
			Course course2 = new Course(null, "React", "frontend");
			Course course3 = new Course(null, "Srping Boot", "backend");
			courseRepository.deleteAll();
			courseRepository.saveAll(List.of(course,course1,course2,course3));
		};
	}
}
