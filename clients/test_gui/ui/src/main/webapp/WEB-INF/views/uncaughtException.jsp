<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2/>Internal error</h2>
<p/>The execption will be reported to jira.
<div><a href="<c:url value="/" />">Home</a></div>
<%
   String s = (String)request.getAttribute("errorMsg");
   out.write(s);
%>
<p/>
<br/>

