<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
Solver status: ${solverStatus} <br/>  

<c:if test="${solverStatus=='Initial'}">
  please click <a href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis/${idAnalysis}/solver"/>"> here
  to run solver.</a>
</c:if>