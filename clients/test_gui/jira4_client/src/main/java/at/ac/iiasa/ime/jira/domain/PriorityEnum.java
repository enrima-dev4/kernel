package at.ac.iiasa.ime.jira.domain;

public enum PriorityEnum {

	Blocker(1), Critial(2),Major(3), Minor(4),Trivial(5);
	
	private final int elementCode;

	PriorityEnum(int elementCode) {
		this.elementCode = elementCode;
	}

	public int elementCode() {
		return elementCode;
	}

}
