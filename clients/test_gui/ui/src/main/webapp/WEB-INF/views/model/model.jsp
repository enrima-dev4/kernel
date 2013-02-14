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

<fieldset><legend>Sets specification</legend>
  <table class="display" id="highlight">
        <thead>
          <tr>
            <th>ID</th>
            <th>shortName</th>
            <th>Iterators</th>
            <th>Name</th>
            <th>Description</th>
            <th>Type</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${model.setSpec}" var="setSpec">
            <tr>
                <td>${setSpec.id}</td>
                <td>${setSpec.shortName}</td>
                 <td><c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,setSpec.iteratorContainer)}"></c:set>
                    ${iterators}
                </td>
                <td>${setSpec.name}</td>
                <td>${setSpec.description}</td>
                <td>${setSpec.type}</td>
           
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>

<fieldset><legend>Entities specification</legend>
  <table class="display" id="highlight1">
        <thead>
          <tr>
            <th>ID</th>
            <th>shortName</th>
            <th>Iterators</th>
            <th>Name</th>
            <th>Description</th>
            <th>Bounds</th>
            <th>Unit</th>
            <th>Math type</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${model.entitySpec}" var="entitySpec">
            <tr>
                <td>${entitySpec.id}</td>
                <td>${entitySpec.shortName}</td>
                      <td><c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,entitySpec.iteratorContainer)}"></c:set>
                    ${iterators}
                </td>
                <td>${entitySpec.name}</td>
                <td>${entitySpec.description}</td>
                <td>[<c:if test="${entitySpec.idLowerBound!=null}">
                <c:set var="lowerbnd" value="${symbolicModelSpecificationHelper.getEntitySpecById(model,entitySpec.idLowerBound)}"></c:set>
                     ${lowerbnd.shortName}
                </c:if>,<c:if test="${entitySpec.idUpperBound!=null}">
                    <c:set var="upperbnd" value="${symbolicModelSpecificationHelper.getEntitySpecById(model,entitySpec.idUpperBound)}"></c:set>
                     ${upperbnd.shortName}
                </c:if>]</td>
              <td>${entitySpec.unit}</td>
              <td>${entitySpec.mathType}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>



