package com.telosys.gwtp.base.shared.dto;

import java.io.Serializable;

public class TeamDto implements Serializable {
	private static final long serialVersionUID = 5735117549801608932L;

	private Long id;

	private String name;

	public TeamDto() {
	}

	public TeamDto(Long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "IdLabelDto [id=" + id + ", name=" + name + "]";
	}

}
