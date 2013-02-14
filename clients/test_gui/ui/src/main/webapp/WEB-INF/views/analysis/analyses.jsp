<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" charset="utf-8">
$(document).ready(
 function(){
	 $('#highlight').dataTable(
			 {
				     "bJQueryUI":true,
		             "bPaginate": false,
		             "bInfo":false,
		             "bFilter":false,
		             "bSort": false  
			 }
			 );
 }		
);

</script>

<fieldset><legend>
<a href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis/new"/>" title="create a new analysis"><img src="<c:url value="/resources/images/crud/add.png" />" /></a>
Model analyses</legend>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>ID</th>
            <th>Short name</th>
            <th>Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Operation</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${analyses}" var="analysis">
            <tr>
                <td>${analysis.id}</td>
                <td>${analysis.shortName}</td>
                <td>${analysis.name}</td>
                <td>${analysis.description}</td>
                <td>${analysis.status}</td>
                <td>
                <a href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis/${analysis.id}" />">
                <img src="<c:url value="/resources/images/crud/edit.png" />" />
                </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>