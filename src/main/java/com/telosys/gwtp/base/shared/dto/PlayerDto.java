package com.telosys.gwtp.base.shared.dto;

import java.io.Serializable;

public class PlayerDto implements Serializable {

	private static final long serialVersionUID = 7828224611035419587L;

	private Long id;
	private String name;
	private Long team;

	public PlayerDto() {
	}

	public PlayerDto(Long id, String name, Long team) {
		this.id = id;
		this.name = name;
		this.team = team;
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

	public Long getTeam() {
		return team;
	}

	public void setTeam(Long team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "PlayerDto [id=" + id + ", name=" + name + ", team=" + team + "]";
	}
}
