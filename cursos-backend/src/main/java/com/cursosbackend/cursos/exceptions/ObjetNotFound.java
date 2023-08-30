package com.cursosbackend.cursos.exceptions;

public class ObjetNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetNotFound(Long id) {
		super("Object not found! ID=" + id);
	}

}
