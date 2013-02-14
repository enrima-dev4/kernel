package at.ac.iiasa.ime.jira;

import java.io.File;
import java.io.IOException;

import at.ac.iiasa.ime.jira.domain.Comment;
import at.ac.iiasa.ime.jira.domain.IssueTypeEnum;
import at.ac.iiasa.ime.jira.domain.PriorityEnum;
import at.ac.iiasa.ime.jira.soap.RemoteIssue;

public class JiraClientExample {

	
	public static void main(String[] args)
	{
		String jiraUrl = "http://wwww.ime.iiasa.ac.at/jira/rpc/soap/jirasoapservice-v2";
		String projectId = "EMTRADE";
		String componetId ="10161";
		String jiraUserName ="comment_tool";
		String password = "qwerty";
		String assignee = "renh";
		JiraAdapter jiraAdapter = new JiraAdapter(jiraUrl,projectId,componetId,jiraUserName,password,assignee);

		
		Comment comment = new Comment();
		comment.setAppName("Emission Trading");
		comment.setAppVersion("D1.01");
		comment.setDescription("attachment test");
		comment.setEmail("hongtao.ren@gmail.com");
		comment.setIssueType(IssueTypeEnum.Improvement);
		comment.setPriority(PriorityEnum.Critial);
		comment.setSummary("jira4 test from jira client module");
		comment.setUsername("renh");
		comment.setDescription("desc test here");
		//comment.setApplicationStateParams(applicationStateParams);
		//comment.setUserAgent(userAgent);
		//comment.setUrl(url);
		String filePath = JiraClientExample.class.getResource("/").getPath() + "/attachment/plate10.png";
		File attachment = new File(filePath);
		comment.setAttachment(attachment);
		try {
			RemoteIssue issue=	jiraAdapter.sendRequest(comment);
			System.out.println(" the issue '"+issue.getKey()+"' was created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
