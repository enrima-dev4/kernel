package at.ac.iiasa.ime.jira;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import at.ac.iiasa.ime.jira.domain.ApplicationStateParam;
import at.ac.iiasa.ime.jira.domain.Comment;
import at.ac.iiasa.ime.jira.soap.JiraSoapService;
import at.ac.iiasa.ime.jira.soap.RemoteComponent;
import at.ac.iiasa.ime.jira.soap.RemoteIssue;
import sun.misc.BASE64Encoder;


public class JiraAdapter {

	private String jiraUrl;
	private String jiraProjectId;
	private String jiraComponentId;
	private String jiraUserName;
	private String jiraPassword;
	private String assignee;

	public JiraAdapter(String jiraUrl, String jiraProjectId,
			String jiraComponentId, String jiraUserName, String jiraPassword,
			String assignee) {
		this.jiraUrl = jiraUrl;
		this.jiraProjectId = jiraProjectId;
		this.jiraComponentId = jiraComponentId;
		this.jiraUserName = jiraUserName;
		this.jiraPassword = jiraPassword;
		this.assignee = assignee;
	}

	public RemoteIssue sendRequest(Comment comment)
			throws IOException {

		SOAPSession soapSession = new SOAPSession(new URL(jiraUrl));
		soapSession.connect(jiraUserName, jiraPassword);
		JiraSoapService jiraSoapService = soapSession.getJiraSoapService();
		String authToken = soapSession.getAuthenticationToken();
		RemoteIssue issue = convert2RemoteIssue(comment);
		RemoteIssue returnedIssue = jiraSoapService.createIssue(authToken,
				issue);
		final String issueKey = returnedIssue.getKey();
		File attachment = comment.getAttachment();
		
		if(attachment!=null)
		{
			 String[] filenames = new String[] {attachment.getName()};
		     InputStream is = new FileInputStream(attachment);
		     byte[] buff = new byte[(int) attachment.length()];
		     int read;
		        int offset = 0;
		        do {
		            read = is.read(buff, offset, buff.length - offset);
		            if (read > 0) {
		                offset += read;
		            }
		        } while (read < buff.length);
		      //  byte[][] data = new byte[][] {buff};
		        String base64encodedFileData=new BASE64Encoder().encode(buff); 
		        String[] encodedData= new String[] { base64encodedFileData };
		        jiraSoapService.addBase64EncodedAttachmentsToIssue(authToken, issueKey, filenames, encodedData);
		}
		
		
	

		return returnedIssue;

	}

	private RemoteIssue convert2RemoteIssue(Comment comment) {

		String environmentInfo = generateEnvironmentInfoString(comment);

		RemoteIssue issue = new RemoteIssue();
		issue.setProject(jiraProjectId);
		issue.setType("" + comment.getIssueType().elementCode());
		issue.setSummary(comment.getSummary());
		issue.setPriority("" + comment.getPriority().elementCode());
		Calendar cal = Calendar.getInstance();
		Date dueDate=comment.getDueDate();
		if(dueDate!=null)
		{
			cal.setTime(dueDate);
			issue.setDuedate(cal);
		}

		issue.setAssignee(assignee);
		issue.setReporter(jiraUserName);
		RemoteComponent component = new RemoteComponent();
		component.setId(jiraComponentId);
		issue.setComponents(new RemoteComponent[] { component });
		issue.setEnvironment(environmentInfo);
		issue.setDescription(comment.getDescription());
		return issue;
	}

	private String generateEnvironmentInfoString(Comment comment) {
		StringBuilder sb = new StringBuilder();
		sb.append("app-name: " + comment.getAppName() + "\n");
		sb.append("app-version: " + comment.getAppVersion() + "\n");
		sb.append("app-user-name: " + comment.getUsername() + "\n");
		sb.append("app-user-email: " + comment.getEmail() + "\n");
		sb.append("browser-type: " + comment.getUserAgent() + "\n");

		List<ApplicationStateParam> asps = comment.getApplicationStateParams();
		if (asps != null) {
			for (ApplicationStateParam asp : asps) {
				sb.append("app-state-param: " + asp.getName() + "="
						+ asp.getValue() + "\n");
			}
		}
		return sb.toString();
	}

}
