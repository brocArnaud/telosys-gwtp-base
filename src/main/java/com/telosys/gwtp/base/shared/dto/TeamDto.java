package com.telosys.gwtp.base.shared.dto;

import javax.validation.constraints.NotNull;

public class TeamDto extends IdDto {
	private static final long serialVersionUID = 5735117549801608932L;

	@NotNull
	private String name;

	public TeamDto() {
	}

	public TeamDto(Long id, String name) {
		setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IdLabelDto [id=" + getId() + ", name=" + name + "]";
	}
}