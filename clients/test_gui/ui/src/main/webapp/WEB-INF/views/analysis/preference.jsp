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
<c:if test="${editable}">
<a href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis/${idAnalysis}/preference/add"/>" title="add a preference value"><img src="<c:url value="/resources/images/crud/add.png" />" /></a></c:if>
Preference</legend>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>Entity</th>
            <th>Preference type</th>
            <th>Value</th>
            <th>Operation</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${preferences}" var="preference">
         <c:set var="entity" value="${symbolicModelSpecificationHelper.getEntitySpecById(model,preference.idEntitySpec)}"></c:set>
            <tr>
                <td>${entity.shortName}</td>
                <td>${preference.preferenceType}</td>
                <td>${preference.prefValue}</td>
                <td>
               <c:if test="${editable}"> <a href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis/${idAnalysis}/preference/${preference.idEntitySpec}/delete" />">
                <img src="<c:url value="/resources/images/crud/del.png" />" /></c:if>
                </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>