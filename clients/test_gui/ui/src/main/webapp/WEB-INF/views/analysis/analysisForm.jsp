<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form modelAttribute="analysis">
<fieldset><legend>Create new analysis</legend>
<form:label path="shortName">Short name:</form:label> <form:input path="shortName"/> <br/>
<form:label path="name">Name: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</form:label> <form:input path="name"/> <br/>
<form:label path="description">Description:</form:label> <form:textarea path="description" rows="5" cols="30"/>
<form:input type="hidden" path="idModelInstance" />  
<form:input type="hidden" path="status" /> 
</fieldset>
<input type="submit" value="Create"  name="_create"/> <input type="submit" value="Cancel" name="_cancel" />
</form:form>