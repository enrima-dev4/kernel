<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form modelAttribute="member">

<fieldset><legend>Add a new member</legend>

<form:label path="code">Name: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</form:label> <form:input path="code"/> <br/>
<form:label path="description">Description:</form:label> <form:input path="description"/>
  
</fieldset>
<input type="submit" value="Add"  name="_create"/> <input type="submit" value="Cancel" name="_cancel" />
</form:form>