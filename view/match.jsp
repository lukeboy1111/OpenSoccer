<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:wrapper pageTitle="OpenFootie">
  <jsp:body>
    <t:sectionTitle sectionTitle="The Match" section="seo"></t:sectionTitle>
		
	<h3> Match Information </h3>
	
	<div id="matchInfo">
	<p>
	${homeTeamName} vs ${awayTeamName }
	</p>
	</div>
	<div>
		<h3> Match Score </h3>
		<div class="roundBoxLeft"> 
			<div id="score">
			${matchScore}
			</div>
		</div>
	</div>
	<div>
		<h3> Lineups </h3>
		<div class="roundBoxLeft">
			<h4> ${homeTeamName} </h4>
			${homeTeamLineup}
		</div>
		<div class="roundBoxLeft">
			<h4> ${awayTeamName} </h4>
			${awayTeamLineup}
		</div>
	</div>
	<div>
		<h3> Match Report </h3>
		<div class="roundBoxLeft">
			${matchReportAsString}
		</div>
	</div>
	<div>
		<h3> Match Stats </h3>
		<div class="roundBoxLeft">
			${matchStatsAsString}
		</div>
	</div>
		
		
	




</jsp:body>
</t:wrapper>	
	
	
	
	
	
