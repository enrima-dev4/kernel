package at.ac.iiasa.ime.jira.domain;

import java.io.Serializable;

public class ApplicationStateParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	public ApplicationStateParam(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
