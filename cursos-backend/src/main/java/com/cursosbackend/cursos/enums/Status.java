package com.cursosbackend.cursos.enums;

public enum Status {
	ACTIVE("Active"), INACTIVE("Inactive");

	private String status;

	Status(String status) {
		this.status = status;
	}

	public String getValue() {
		return this.status;
	}
}
