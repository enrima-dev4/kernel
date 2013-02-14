<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	 $('#highlight1').dataTable(
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
<!--  <a href="<c:url value="/fileUpload" />">Send request by uploading XML file</a>-->
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>Sets</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${model.setSpec}" var="set">
            <tr>
                <td> <a <c:if test="${set.id==setId}">class="left_menu_link_active"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}/set/${set.id}" />">${set.shortName}<c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,set.iteratorContainer)}"></c:set>
                     ${iterators}:&nbsp; ${set.name}</a></td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>


  <table class="display" id="highlight1">
        <thead>
          <tr>
            <th>Entities</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${model.entitySpec}" var="entitySpec">
            <tr>
                <td>
                  <c:choose>
                    <c:when test="${entitySpec.role=='PARAMETER'}">
                     <a <c:if test="${entitySpec.id==entityId}">class="left_menu_link_active"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}/entity/${entitySpec.id}" />">
                      ${entitySpec.shortName}<c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,entitySpec.iteratorContainer)}"></c:set>
                     ${iterators}: &nbsp;${entitySpec.name}
                    </a>
                </c:when>
                <c:otherwise>
                 ${entitySpec.shortName}<c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,entitySpec.iteratorContainer)}"></c:set>
                     ${iterators}: &nbsp;${entitySpec.name}
                </c:otherwise>
                </c:choose>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

