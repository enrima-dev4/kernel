<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


 <c:forEach items="${entityValues}" var="entityValue">
   Entity: ${entityValue.entityShortName} <br/>
    <c:forEach items="${entityValue.tupleValues}" var="tupleValue">
   <c:if test="${not empty tupleValue.tuple}">Tuple: ${tupleValue.tuple},</c:if>  Value: ${tupleValue.value.doubleValue}${tupleValue.value.intValue} <br/>
    </c:forEach>
 
 </c:forEach>

