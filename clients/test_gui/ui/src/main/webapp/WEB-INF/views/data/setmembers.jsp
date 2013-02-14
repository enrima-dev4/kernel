<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" charset="utf-8">
$(document).ready(
 function(){
	 $('#highlight2').dataTable(
			 {       
			 }
			 );
 }		
);

</script>
<c:if test="${not empty message}">
				<div id="error" class="error">${message}</div>
			</c:if>
<fieldset><legend>${setSpec.shortName}<c:if test="${iterators!=null}">[${iterators}]</c:if>
<c:if test="${iterators==null}">
<a href="<c:url value="/model/${modelId}/data/${dataId}/set/${setId}/add"/>" title="add a new member"><img src="<c:url value="/resources/images/crud/add.png" />" /></a>
</c:if>
<c:if test="${iterators!=null}">
<a href="<c:url value="/model/${modelId}/data/${dataId}/set/${setId}/add?iterators=${iterators}"/>" title="add a new member"><img src="<c:url value="/resources/images/crud/add.png" />" /></a>
</c:if>
</legend>
  <table class="display" id="highlight2">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Operation</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${members}" var="member">
            <tr>
                <td>${member.code}</td>
                <td><c:if test="${member.description==null}">${member.code}</c:if><c:if test="${member.description!=null}">${member.description}</c:if></td>
                
                <c:if test="${iterators==null}">
                <td><a href="<c:url value="/model/${modelId}/data/${dataId}/set/${setId}/member/${member.code}/remove"/>"><img src="<c:url value="/resources/images/crud/del.png" />" /></a></td>
                </c:if>
                <c:if test="${iterators!=null}">
                <td><a href="<c:url value="/model/${modelId}/data/${dataId}/set/${setId}/member/${member.code}/remove?iterators=${iterators}"/>"><img src="<c:url value="/resources/images/crud/del.png" />" /></a></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>