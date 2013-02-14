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
<form  method="get" action="<c:url value="/model/${modelId}/data/${dataId}/indexedsubSet/${setId}/members"/>">
<fieldset><legend>${setSpec.shortName} <c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,setSpec.iteratorContainer)}"></c:set>
                     ${iterators}</legend>

  ${setSpec.shortName}:<c:forEach items="${iteratorsMap}" var="entry">
    <select name="iterators">
      <c:forEach items="${entry.value}" var="member">
         <option value="${member.code}">${member.code}</option>
      </c:forEach>
    </select>
  
  </c:forEach>

  <input type="submit" value="Edit members" />
</fieldset>
</form>