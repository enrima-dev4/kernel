package at.ac.iiasa.ime.enrima.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import at.ac.iiasa.ime.jira.JiraAdapter;
import at.ac.iiasa.ime.jira.domain.Comment;
import at.ac.iiasa.ime.jira.domain.IssueTypeEnum;
import at.ac.iiasa.ime.jira.domain.PriorityEnum;


import java.io.FileWriter;
import java.util.Properties;

public class ExceptionHandlerFilter implements Filter {

	private String errorMsg = "";

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse respone = (HttpServletResponse) servletResponse;
		final HttpSession session= request.getSession();
		
	
		if(session.isNew())
		{
				respone.sendRedirect(request.getContextPath()+"/");
				return;
		}
		try {
			filterChain.doFilter(request, servletResponse);
		} catch (Exception ex) {

			if(ex.getClass().toString().equals("class org.springframework.web.HttpSessionRequiredException"))
			{
				respone.sendRedirect(request.getContextPath()+"/timeout");
				return;
			}
			else
			{
				InputStream in = ExceptionHandlerFilter.class.getClassLoader()
						.getResourceAsStream("application.properties");
				Properties p = new Properties();
				p.load(in);
				String jiraUrl = p.getProperty("jira.url"); // "http://www.ime.iiasa.ac.at/jira/rpc/soap/jirasoapservice-v2";
				String projectId = p.getProperty("projectId"); // "ENRIMA" ;
				String componetId = p.getProperty("componetId"); // "10270";
				String jiraUserName = p.getProperty("jiraUserName"); // "comment_tool";
				String password = p.getProperty("password"); // "qwerty";
				String assignee = p.getProperty("assignee"); // "renh";
				String appName = p.getProperty("app");
				String version = p.getProperty("enrima.ver");
				String ver_type = p.getProperty("enrima.ver.type");
				JiraAdapter jiraAdapter = new JiraAdapter(jiraUrl, projectId,
						componetId, jiraUserName, password, assignee);

				Comment comment = new Comment();
				comment.setAppName(appName);
				comment.setAppVersion(version + "_" + ver_type);
				comment.setDescription("exception");

				comment.setIssueType(IssueTypeEnum.Bug);
				comment.setPriority(PriorityEnum.Critial);
				comment.setSummary("exception ");

				comment.setUsername(jiraUserName);
				comment.setDescription("please see the attachment");

				comment.setUserAgent(request.getHeader("user-agent"));
				File f = exceptionToHTMLFile(ex);
				if (f != null) {
					comment.setAttachment(f);
				}
				if (!ver_type.equalsIgnoreCase("d")) {
				  try {
					  jiraAdapter.sendRequest(comment);
				   } catch (IOException e) {
				 	e.printStackTrace();
				   }
				  }

				if (ver_type.equalsIgnoreCase("d")) {
					request.setAttribute("errorMsg", errorMsg);
				}

				request.getRequestDispatcher(
						"/WEB-INF/views/uncaughtException.jsp").forward(
						request, respone);
			}

			
		}

	}


	public void init(FilterConfig filterConfig) throws ServletException {

	}


	public void destroy() {

	}

	protected File exceptionToHTMLFile(Exception exc) {

		StringBuilder sb = new StringBuilder();

		sb.append("<html><body style=\"background-color : lightgreen;\">"
				+ "\n");

		Throwable e = exc;
		while (e != null) {

			// <![CDATA[ somehow doesn't work as expected
			String exceptionMsg = e.getMessage();
			if (exceptionMsg != null && !exceptionMsg.isEmpty()) {
				exceptionMsg = exceptionMsg.replaceAll("&", "&amp;");
				exceptionMsg = exceptionMsg.replaceAll("<", "&lt;");
				exceptionMsg = exceptionMsg.replaceAll(">", "&gt;");
				exceptionMsg = exceptionMsg.replaceAll("\n", "<br/>");
			}

			sb.append("<p>Exception: <b>" + e.getClass() + "</b>, message: <b>"
					+ exceptionMsg + "</b></p>" + "\n");

			sb.append("<p>" + "\n");
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			for (StackTraceElement ste : stackTraceElements) {

				// set background color
				String steStr = ste.toString();
				if (steStr.startsWith("pl.bn.bk"))
					steStr = setBackgroundColorToHtmlText(steStr, "lightblue");
				else if (steStr.startsWith("at.ac.iiasa"))
					steStr = setBackgroundColorToHtmlText(steStr, "lavender");

				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;" + steStr + "<br/>" + "\n");
			}
			sb.append("</p>" + "\n");

			Throwable cause = e.getCause();
			e = cause;

			if (e != null)
				sb.append("<p>Caused by:</p>" + "\n");
		}
		sb.append("</body></html>" + "\n");
		FileWriter fw;
		try {

			File f = new File("ex.html");
			if (!f.exists())
				f.createNewFile();
			// System.out.println("___file path: " + f.getAbsolutePath());
			fw = new FileWriter(f, false);
			fw.write(sb.toString());
			// System.out.println(sb.toString());
			errorMsg = sb.toString();
			fw.close();
			return f;

		} catch (IOException e1) {
			// System.out.println("IOIOIO error!");
			e1.printStackTrace();
			return null;
		}

	}

	private String setBackgroundColorToHtmlText(String text, String colorName) {
		String s = "<span style=\"background-color : " + colorName + ";\">"
				+ text + "</span>";
		return s;
	}

}
