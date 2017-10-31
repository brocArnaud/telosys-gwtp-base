package com.telosys.gwtp.base.shared.dto;

import java.io.Serializable;

public class IdDto implements Serializable {

	private static final long serialVersionUID = 3998824148293869843L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}