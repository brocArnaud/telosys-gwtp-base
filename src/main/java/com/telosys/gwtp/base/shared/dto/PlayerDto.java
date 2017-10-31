package com.telosys.gwtp.base.shared.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PlayerDto extends IdDto {

	private static final long serialVersionUID = 7828224611035419587L;

	@NotNull
	private String name;

	@NotNull
	private String team;

	public PlayerDto() {
	}

	public PlayerDto(Long id, String name, String team) {
		setId(id);
		this.name = name;
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "PlayerDto [id=" + getId() + ", name=" + name + ", team=" + team + "]";
	}
}
