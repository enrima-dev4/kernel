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
<form:form>
<fieldset><legend>${setSpec.shortName} <c:if test="${iterators!=null}">[${iterators}], parent set: ${parent.shortName}</c:if></legend>
  <table class="display" id="highlight2">
        <thead>
          <tr>
            <th>Select</th>
            <th>Name</th>
            <th>Description</th>

            </tr>
        </thead>
        <tbody>
         <c:forEach items="${members}" var="member">
            <tr>
                <td><input type="checkbox" name="codes" value="${member.code}"></td>
                <td>${member.code}</td>
                <td><c:if test="${member.description==null}">${member.code}</c:if><c:if test="${member.description!=null}">${member.description}</c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>
 <input type="submit" value="Add" name="_add"/> <input type="submit" value="Cancel" name="_cancel" />
</form:form>