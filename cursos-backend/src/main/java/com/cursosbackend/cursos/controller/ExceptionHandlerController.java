package com.cursosbackend.cursos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cursosbackend.cursos.exceptions.ObjetNotFound;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ObjetNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String ObjetNotFoundException(ObjetNotFound ex) {
		return ex.getMessage();
	}

}
