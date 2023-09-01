package com.cursosbackend.cursos.enums.converters;

import java.util.stream.Stream;

import com.cursosbackend.cursos.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

	@Override
	public String convertToDatabaseColumn(Category attribute) {
		return (attribute == null ? null : attribute.getValue());
	}

	@Override
	public Category convertToEntityAttribute(String dbData) {
		return Stream.of(Category.values()).filter((c) -> c.getValue().equals(dbData)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
	}
}
