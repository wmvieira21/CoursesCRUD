package com.cursosbackend.cursos.domain;

import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_course")
@SQLDelete(sql = "update tb_course set course_status = 'Deactivated' where id = ?")
@Where(clause = "course_status = 'Active'")

public class Course {

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
	@Length(max = 50)
	@Pattern(regexp = "backend|frontend")
	private String category;

	@JsonProperty("status")
	@NotNull
	@Length(max = 15)
	@Pattern(regexp = "Active|Deactivated")
	@Column(name = "course_status", nullable = false, length = 15)
	private String status = "Active";

	public Course() {
		super();
	}

	public Course(Long id, String name, String category) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
