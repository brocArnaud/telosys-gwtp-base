package com.telosys.gwtp.base.shared.dto.player;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.telosys.gwtp.base.shared.dto.player.validation.ValidPlayer;

@ValidPlayer
public class PlayerDto implements Serializable {

	private static final long serialVersionUID = 7828224611035419587L;

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String team;

	public PlayerDto() {
	}

	public PlayerDto(Long id, String name, String team) {
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
