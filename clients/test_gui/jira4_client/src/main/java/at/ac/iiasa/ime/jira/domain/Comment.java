package at.ac.iiasa.ime.jira.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String appName;
	private String appVersion;
	private IssueTypeEnum issueType;
	private String summary;
	private PriorityEnum priority;
	private String description;
	private String url;
	private List<ApplicationStateParam> applicationStateParams;
	private String userAgent;
	private File attachment;
	private Date dueDate;
	
	public Comment() {}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public void setIssueType(IssueTypeEnum issueType) {
		this.issueType = issueType;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setApplicationStateParams(
			List<ApplicationStateParam> applicationStateParams) {
		this.applicationStateParams = applicationStateParams;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}


	public IssueTypeEnum getIssueType() {
		return issueType;
	}

	public String getSummary() {
		return summary;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public String getDescription() {
		return description;
	}

	public List<ApplicationStateParam> getApplicationStateParams() {
		return applicationStateParams;
	}

	public String getUserAgent() {
		return userAgent;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
