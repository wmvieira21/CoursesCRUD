package com.cursosbackend.cursos;

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
			courseRepository.deleteAll();
			courseRepository.save(course);
		};
	}

}
