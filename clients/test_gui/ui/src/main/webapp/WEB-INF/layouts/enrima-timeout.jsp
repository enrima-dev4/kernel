<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ include file="html-head-common-content.html" %>
 <SCRIPT type="text/javascript">
	var myLayout;
	$(document).ready(function () {
	myLayout = $('body').layout();
	myLayout.sizePane("south", 16);
 	});
</SCRIPT>
</head>
 <body>
  <div class="ui-layout-north" id="header_toolbar">
  <div id="nav_top">
  <div id="app_area" >
     <fmt:message key="app_name" />
   </div>
   <div id="other_area"> 
    <a id="headerActiveLink1" target="_blank" href="<c:url value="/contact?usr=anonymous" />"><fmt:message key="contact" /></a>&nbsp; 
  </div>
</div>
<div id="navmenu">
<ul id="nav">
</ul>
</div>
  </div>
  <div  class="ui-layout-center">
     <tiles:insertAttribute name="body" />
  </div>

 <div class="ui-layout-south" id="footer_copyrights">
    <%@ include file="footer.jsp" %>
  </div>

</body>

</html>