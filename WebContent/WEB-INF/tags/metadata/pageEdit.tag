<%@ tag description="Metadata Page Summary Edit" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="metadata" required="true" type="com.pokerstars.domains.metadata.bo.MetadataContainer"%>
<%@ attribute name="count" required="true" %>
<%@ attribute name="path" required="true" %>

<div class="cell">
	${path }
	<a style="color: #FFF" class="showHideButton" rel="expand[edit${count }, Hide]" href="javascript:void(0)"><em>Expand</em></em></a>
</div>

<div class="cell">
	Title length: ${fn:length(metadata.title)}
</div>

<div class="cell">
	Description length: ${fn:length(metadata.description)}
</div>

<div class="cell">
	<c:choose>
	<c:when test="${fn:length(metadata.canonical) == 0 }">
		No Canonical
	</c:when>
	<c:otherwise>
		${metadata.canonical }
	</c:otherwise>
	</c:choose>
</div>

<div class="clear"></div>

<div id="edit${count }" class="summaryContainer" style="display: none">
	
	<c:choose>
	<c:when test="${metadata.isPendingUpdate == true }">
	
	<div id="savingState${count }" style="margin: 5px 0 5px 0;">
		<span style="color: red; font-weight: bold;">There is a pending update, this item currently can not be updated. Try again soon.</span>
		<input type="hidden" id="willSave[${count }]" name="willSave[${count }]" value="0"/>
	</div>
	
	<p>
	<label for="title[${count }]">Title:</label><br/>
	<input type="text" size="150" name="title[${count }]" value="${metadata.title }" disabled="disabled"/>
	</p>
	
	<p>
	<label for="keywords[${count}]">Keywords: <em>(comma separated)</em></label><br/>
		<input type="text" size="150" 
							value="${metadata.keywords }" name="keywords[${count}]"
							disabled="disabled"/>
		<br/>
		
	</p>
		
	<p>	
	<label for="canonical[${count}]">Canonical:</em></label><br/>					
	<input type="text" size="150" value="${metadata.canonical }" name="canonical[${count}]"
						disabled="disabled"/>
	</p>
	
	<label for="description[${count }]">Description:</label><br/>
	<textarea rows="3" cols="150" name="description[${count }]" disabled="disabled">${metadata.description }</textarea>
	
	</c:when>
	
	<c:otherwise>
	
	<p>
	<label for="title[${count }]">Title:</label><br/>
	<input type="text" size="150" name="title[${count }]" value="${metadata.title }"/>
	</p>
	
	<p>
	<label for="keywords[${count}]">Keywords: <em>(comma separated)</em></label><br/>
		<input type="text" size="150" 
							value="${metadata.keywords }" name="keywords[${count}]"/>
		<br/>
		
	</p>
		
	<p>	
	<label for="canonical[${count}]">Canonical:</em></label><br/>					
	<input type="text" size="150" value="${metadata.canonical }" name="canonical[${count}]"/>
	</p>
	
	<label for="description[${count }]">Description:</label><br/>
	<textarea rows="3" cols="150" name="description[${count }]">${metadata.description }</textarea>

	<p>
		<button id="saveBtn[${count }]" onclick="return saveTrue(${count})">Save Changes</button>
		<button id="cancelBtn[${count }]" onclick="return saveFalse(${count})">Cancel</button>
	</p>

	<div id="savingState${count }" style="margin: 5px 0 5px 0;">
		<div id="saveText[${count}]" style="color: red;">This item will not be saved</div>
		<input type="hidden" id="willSave[${count }]" name="willSave[${count }]" value="0"/>
	</div>
	
	</c:otherwise>
	
	</c:choose>

</div>

<div class="clear"></div>