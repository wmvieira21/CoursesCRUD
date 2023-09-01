package com.cursosbackend.cursos;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cursosbackend.cursos.domain.Course;
import com.cursosbackend.cursos.enums.Category;
import com.cursosbackend.cursos.repository.CourseRepository;

@SpringBootApplication
public class CursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
	}

	@Bean
	CommandLineRunner initdatabase(CourseRepository courseRepository) {
		return args -> {
			Course course = new Course(null, "Angular", Category.FRONTEND);
			Course course1 = new Course(null, "Java8", Category.BACKEND);
			Course course2 = new Course(null, "React", Category.FRONTEND);
			Course course3 = new Course(null, "Srping Boot", Category.BACKEND);
			courseRepository.deleteAll();
			courseRepository.saveAll(List.of(course, course1, course2, course3));
		};
	}
}
