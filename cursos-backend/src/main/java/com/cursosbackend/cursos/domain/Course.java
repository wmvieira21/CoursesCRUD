package com.cursosbackend.cursos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.cursosbackend.cursos.enums.Category;
import com.cursosbackend.cursos.enums.Status;
import com.cursosbackend.cursos.enums.converters.CategoryConverter;
import com.cursosbackend.cursos.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_course")
@SQLDelete(sql = "update tb_course set course_status = 'Deactivated' where id = ?")
@Where(clause = "course_status = 'Active'")

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("_id")
	private Long id;

	@JsonProperty("name")
	@Column(name = "course_name", length = 100, nullable = false)
	@NotNull
	@NotBlank
	@Length(min = 5, max = 100)
	private String name;

	@JsonProperty("category")
	@Column(name = "course_category", length = 50, nullable = false)
	@NotNull
	// @Pattern(regexp = "backend|frontend")
	// @Enumerated(EnumType.STRING)
	@Convert(converter = CategoryConverter.class)
	private Category category;

	@JsonProperty("status")
	@Column(name = "course_status", nullable = false, length = 15)
	@NotNull
	// @Length(max = 15)
	// @Pattern(regexp = "Active|Deactivated")
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
	private List<Lesson> lessons = new ArrayList<>();

	public Course() {
		super();
	}

	public Course(Long id, String name, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(id, other.id);
	}

}
