<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form>
<fieldset><legend>Add new preference value</legend>
Entity:  
<select name="idEntity">
      <c:forEach items="${entities}" var="entity">
         <option value="${entity.id}">${entity.shortName}</option>
      </c:forEach>
</select><br/>
Preference Type: <select name="preferenceType">
         <option value="MIN">MIN</option>
         <option value="MAX">MAX</option>
</select><br/>
Value:  <input type="text" name="value" />
</fieldset>
<input type="submit" value="Save"  name="_create"/> <input type="submit" value="Cancel" name="_cancel" />
</form:form>