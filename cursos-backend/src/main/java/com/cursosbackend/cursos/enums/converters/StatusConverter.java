package com.cursosbackend.cursos.enums.converters;

import java.util.stream.Stream;

import com.cursosbackend.cursos.enums.Category;
import com.cursosbackend.cursos.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status attribute) {
		return (attribute == null ? null : attribute.getValue());
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		return Stream.of(Status.values()).filter((c) -> c.getValue().equals(dbData)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
	}
}
