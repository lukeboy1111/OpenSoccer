<%@ tag description="Metadata Changes Review" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="update" required="true" type="com.pokerstars.domains.metadata.bo.MetadataUpdate" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="count" required="true" %>

<c:choose>
	<c:when test="${count % 2 == 0 }">
		<c:set var="style" value="pendingHeading"/>
	</c:when>
	<c:otherwise>
		<c:set var="style" value="odd"/>
	</c:otherwise>
</c:choose>

<div class="${style }">

<c:choose>
	<c:when test="${update.errCode != 0 }">
		<span class="statusError">ERROR</span>
	</c:when>
	<c:otherwise>
		<span class="statusPending">PENDING</span>
	</c:otherwise>
</c:choose>

<a class="showHideButton" rel="expand[info${count }, ${path }]" href="javascript:void(0)">${path }</a></div>

<div class="pendingDisplay" id="info${count }" style="display:none;">
<table>
<tr><td class="boldCell">Title:</td><td>${update.meta.title }</td></tr>

<tr><td class="boldCell">Keywords:</td><td>${update.meta.keywords }</td></tr>

<tr><td class="boldCell">Canonical:</td><td>${update.meta.canonical }</td></tr>

<tr><td class="boldCell" valign="top">Description:</td><td>${update.meta.description }</td></tr>

</table>

<p>${update.requestString }</p>

</div>