<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper pageTitle="OpenFootie">
  <jsp:body>
    <t:sectionTitle sectionTitle="Choose Teams" section="seo"></t:sectionTitle>
		
	<h3> Choose a team </h3>

	<form id="editform" action="matchServlet" method="post">
	
	
	Home Team:
	<br />
	<select name="homeid">
		<option value="">--Choose--</option>	
		<c:forEach items="${teams}" var="team">
			<option value="${team.teamId}">${team.teamName}</option>	
	  	</c:forEach>
  	</select>
    <br />
    Away Team:
	<br />
	<select name="awayid">
		<option value="">--Choose--</option>	
		<c:forEach items="${teams}" var="team">
			<option value="${team.teamId}">${team.teamName}</option>	
	  	</c:forEach>
  	</select>
    <br />
    <button type="submit">Play</button>
   	</form>
	<div class="clear"> </div>
	
		
		
	




</jsp:body>
</t:wrapper>	
	
	
	
	
	
