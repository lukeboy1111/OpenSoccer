<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Section Title Tag" pageEncoding="UTF-8"%>
<%@attribute name="sectionTitle" required="true" %>
<%@attribute name="section" required="true" %>
<%@attribute name="success" fragment="true" %>
<%@attribute name="error" fragment="true" %>
<%@attribute name="links" fragment="true" %>

	<h1 class="sectionTitle">${sectionTitle }</h1>
	<jsp:invoke fragment="links"/>
	<div class="actionIcons">
			<a href="teams.ListAllTeams" class="ThumbLink homeThumb">All Teams</a>
			<!--  
				<a href="projects.ProjectServlet?action=add" class="ThumbLink addThumb">New Project</a>
			-->
	</div>
	
<div class="clear"></div>
<jsp:invoke fragment="success"/>
<jsp:invoke fragment="error"/>
<div class="clear"></div>			
<br/>
	