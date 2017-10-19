package com.telosys.gwtp.base.shared.dto;

import java.io.Serializable;

public class ListItemDto implements Serializable {
	private static final long serialVersionUID = 4087999999224804800L;
	private String value;
	private String label;

	public ListItemDto() {
	}

	public ListItemDto(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
