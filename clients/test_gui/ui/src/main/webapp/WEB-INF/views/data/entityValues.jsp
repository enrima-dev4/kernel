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
<fieldset><legend>${entitySpec.shortName} <c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,entitySpec.iteratorContainer)}"></c:set>
                     ${iterators} <a href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/add"/>"><img src="<c:url value="/resources/images/crud/add.png" />" /></a>
</legend>
  <table class="display" id="highlight2">
        <thead>
          <tr>
            <th>Tuple</th>
            <th>value</th>
            <th>Operation</th>
            </tr>
        </thead>
        <tbody>
       <c:if test="${entitySpec.mathType=='INTEGER'}">
         <c:forEach items="${tupleValueList}" var="tupleValue">
            <tr>
                <td>
                
                <c:set var="tuple" value="${symbolicModelSpecificationHelper.printAsTuple(tupleValue.tupleMember)}"></c:set>
                     ${tuple}
                </td>
                <td>${tupleValue.value.intValue}</td>
              <td>
              <a href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/edit?tuple=${tuple}&entityValue=${tupleValue.value.intValue}"/>"><img src="<c:url value="/resources/images/crud/edit.png" />" /></a>
              <a href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/remove?tuple=${tuple}"/>"><img src="<c:url value="/resources/images/crud/del.png" />" /></a></td>
            </tr>
        </c:forEach>
        </c:if>
         <c:if test="${entitySpec.mathType=='REAL'}">
         <c:forEach items="${tupleValueList}" var="tupleValue">
            <tr>
                <td><c:set var="tuple" value="${symbolicModelSpecificationHelper.printAsTuple(tupleValue.tupleMember)}"></c:set>
                     ${tuple}</td>
                <td>${tupleValue.value.doubleValue}</td>
                <td>
                              <a href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/edit?tuple=${tuple}&entityValue=${tupleValue.value.doubleValue}"/>"><img src="<c:url value="/resources/images/crud/edit.png" />" /></a>
                <a href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/remove?tuple=${tuple}"/>"><img src="<c:url value="/resources/images/crud/del.png" />" /></a></td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</fieldset>