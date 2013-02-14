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
<input type="hidden" name="tuple" value="${tuple}">
${tuple}<input type="text" name="entityValue" value="${entityValue}" />(math type: ${entitySpec.mathType})<input type="submit" value="Save" name="_save" /> <input type="submit" value="Cancel" name="_cancel"/>
</fieldset>
</form>