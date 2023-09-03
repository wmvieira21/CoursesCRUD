package com.cursosbackend.cursos.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_lesson")
public class Lesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 255, nullable = false)
	private String youtubeURL;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Course course;

	public Lesson() {
		
	}
	
	public Lesson(Long id, String name, String youtubeURL, Course course) {
		super();
		this.id = id;
		this.name = name;
		this.youtubeURL = youtubeURL;
		this.course = course;
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

	public String getYoutubeURL() {
		return youtubeURL;
	}

	public void setYoutubeURL(String youtubeURL) {
		this.youtubeURL = youtubeURL;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
		Lesson other = (Lesson) obj;
		return Objects.equals(id, other.id);
	}
}
