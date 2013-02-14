<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="nav_top">
  <div id="app_area" >
     <fmt:message key="app_name" />
   </div>

   <div id="other_area">
   <!-- <a id="headerActiveLink1" target="_blank" href="<c:url value="/fileDownload" />">XML-templates</a>&nbsp; -->
    <a id="headerActiveLink1" target="_blank" href="<c:url value="/contact?usr=anonymous" />"><fmt:message key="contact" /></a>&nbsp; 
  </div>
</div>

<div id="navmenu">
<ul id="nav">
        <li><a  <c:if test="${whereWeAre=='sms'}">class="headerActiveLink_selected"</c:if> <c:if test="${whereWeAre!='sms'}">class="headerActiveLink"</c:if> href="<c:url value="/model/${modelId}" />"><fmt:message key="sms" /></a></li>
 	    <li><a  <c:if test="${whereWeAre=='data'}">class="headerActiveLink_selected"</c:if> <c:if test="${whereWeAre!='data'}">class="headerActiveLink"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}" />"><fmt:message key="data" /></a></li>
 	    <li><a <c:if test="${whereWeAre=='analysis'}">class="headerActiveLink_selected"</c:if> <c:if test="${whereWeAre!='analysis'}">class="headerActiveLink"</c:if>href="<c:url value="/model/${modelId}/instance/${modelInstanceId}/analysis" />"><fmt:message key="analysis" /></a></li>
        <li><a <c:if test="${whereWeAre=='fileUpload'}">class="headerActiveLink_selected"</c:if> <c:if test="${whereWeAre!='fileUpload'}">class="headerActiveLink"</c:if>href="<c:url value="/fileUpload" />" title="consume webservice by uploading XML file">Upload</a></li>
        <li><a  <c:if test="${whereWeAre=='datawrapper'}">class="headerActiveLink_selected"</c:if> <c:if test="${whereWeAre!='datawrapper'}">class="headerActiveLink"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}/datawrapper" />" >Data wrapper</a></li>
</ul>

</div>




