package com.cursosbackend.cursos.enums;

public enum Category {
	BACKEND("Back-end"), FRONTEND("Front-end");

	private String category;

	Category(String category) {
		this.category = category;
	}

	public String getValue() {
		return this.category;
	}
}
