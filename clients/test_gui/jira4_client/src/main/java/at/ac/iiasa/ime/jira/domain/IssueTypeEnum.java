package at.ac.iiasa.ime.jira.domain;

public enum IssueTypeEnum {

	Bug(1), NewFeature(2), Task(3), Improvement(4), SubTask(5), Question(6);
	
	private final int elementCode;

	IssueTypeEnum(int elementCode) {
		this.elementCode = elementCode;
	}

	public int elementCode() {
		return elementCode;
	}

}
