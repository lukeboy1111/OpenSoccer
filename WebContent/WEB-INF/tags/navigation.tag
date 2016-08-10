<%@ tag description="Domains Tool Navigation Tag" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="navigation" scope="page" class="com.lukeyboy1.beans.NavigationBean" />
<jsp:setProperty name="navigation" property="requestedPage" value="${pageContext.request.requestURI}" />

<div class="menu">
	
	<a href="index.jsp" <jsp:getProperty name="navigation" property="homeClass" />><strong>Home</strong></a>
	<a href="teams.ListAllTeams" <jsp:getProperty name="navigation" property="teamClass" />><strong>Teams</strong></a>	
	<br clear="all" />
</div>
