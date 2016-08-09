<%@ tag description="Domains Tool Page Wrapper" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="pageTitle" required="true" %>


<!-- Beginning of tag -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Language" content="en" />
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />

	<title>${pageTitle}</title>

	<link href="css/overall.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" type="text/javascript" src="scripts/domain_helper.js"></script>
	<script language="JavaScript" type="text/javascript" src="scripts/curvycorners.js"></script>
	
	</head>
	<body>
		<div class="bigBase">
			<t:navigation></t:navigation>
			<div class="main">
				<jsp:doBody/>
			</div>
		</div>
	</body>
</html>	

