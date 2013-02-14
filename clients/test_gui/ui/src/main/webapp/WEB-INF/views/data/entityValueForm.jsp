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
<form  method="get" action="<c:url value="/model/${modelId}/data/${dataId}/entity/${entityId}/save"/>">
<fieldset><legend>${entitySpec.shortName} <c:set var="iterators" value="${symbolicModelSpecificationHelper.printIterators(model,entitySpec.iteratorContainer)}"></c:set>
                     ${iterators}</legend>

 <c:forEach items="${iteratorsMap}" var="entry">
    <select name="tuple">
      <c:forEach items="${entry.value}" var="member">
         <option value="${member.code}">${member.code}</option>
      </c:forEach>
    </select>
  </c:forEach>
  Value: <input type="text" name="entityValue"/>(math type: ${entitySpec.mathType})<input type="submit" value="Save" name="_save" /> <input type="submit" value="Cancel" name="_cancel"/>
</fieldset>
</form>