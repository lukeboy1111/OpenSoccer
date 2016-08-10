<%@ tag description="Metadata Changes Review" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="count" required="true" %>
<%@ attribute name="path" required="true" %>

<div class="metadataDisplay" id="metaDisplay[${count }]" style="display:none;">
<div class="reviewHeading">${path }</div>



<table>
<tr><td class="boldCell">Title:</td><td><span id="title[${count }]"></span></td></tr>
<tr><td class="boldCell">Keywords:</td><td><span id="keywords[${count }]"></span></td></tr>
<tr><td class="boldCell">Canonical:</td><td><span id="canonical[${count }]"></span></td></tr>
<tr><td class="boldCell">Description:</td><td><span id="description[${count }]"></span></td></tr>
</table>

</div>

<div class="clear"></div>