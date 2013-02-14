<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
$(document).ready(
 function(){
	 $('#highlight').dataTable(
			 {
				    "bJQueryUI":true,
		            "bPaginate": false,
		            "bInfo":false,
		            "bFilter":false,
		            "bSort": false  
			 }
			 );
 }		
);

</script>

<table class="display" id="highlight">
        <thead>
          <tr>
            <th>Upload CSV files</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td> <a <c:if test="${type=='mainset'}">class="left_menu_link_active"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}/datawrapper/mainset" />">MainSet elements</a></td> 
            </tr>
            <tr>
                <td> <a <c:if test="${type=='entity'}">class="left_menu_link_active"</c:if> href="<c:url value="/model/${modelId}/data/${dataId}/datawrapper/entity" />">Entity values</a></td> 
            </tr>
        </tbody>
    </table>






