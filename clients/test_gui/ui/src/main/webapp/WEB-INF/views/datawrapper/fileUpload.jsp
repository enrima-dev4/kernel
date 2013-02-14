<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="fileuploadContent">
	<h2>${title}</h2>
	<p>
	<form id="fileuploadForm" method="POST"
		enctype="multipart/form-data" class="cleanform">
		<div class="header">
			<c:if test="${not empty message}">
				<div id="message" class="success"><pre><c:out escapeXml="true" value="${message}"/></pre></div>
			</c:if>
		</div>
		<label for="file">CSV file:</label> <input id="file" type="file" name="file" />
		<p>
			<button type="submit">Upload</button>
		</p>
	</form>
	
</div>
