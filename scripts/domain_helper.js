function addWebSite() {
	var theForm = document.forms["editform"];
	if(theForm) {
		var myArray = theForm.elements["domainId"];
		var requestedValue = "";
		for(i = 0; i < myArray.length; i++) {
			var isSelected = myArray[i].checked;
			if(isSelected) {
				requestedValue = myArray[i].value;
			}
		}
		
		if(requestedValue) {
			theURL = "domains.AddNewWebsiteServlet?domainId="+requestedValue+"&addwebbtn=1";
			document.location.href=theURL;
		}
	}
}

function viewDomain() {
	var theForm = document.forms["editform"];
	if(theForm) {
		var myArray = theForm.elements["domainId"];
		var requestedValue = "";
		for(i = 0; i < myArray.length; i++) {
			var isSelected = myArray[i].checked;
			if(isSelected) {
				requestedValue = myArray[i].value;
			}
		}
		
		if(requestedValue) {
			theURL = "domains.ViewDomainServlet?domainId="+requestedValue;
			document.location.href=theURL;
		}
	}
}

function ownerChange() {
	elementOther("addnew", "owner", "newowner", "-addnew-");
}

function hostChange() {
	elementOther("addnew", "host", "newhost", "-addnew-");
}

function elementOther(formName, theElementName, theDivElement, requestedValue) {
	var theForm = document.forms[formName];
	if(theForm) {
		var formValue = theForm.elements[theElementName].value;
		
		if(formValue == requestedValue) {
			document.getElementById(theDivElement).style.display = "block";
		}
		else {
			document.getElementById(theDivElement).style.display = "none";
		}
	}
}

function addTrackingData() {
	document.getElementById("tracker").style.display = "block";
	document.getElementById("addTracking").style.display = "none";
	document.getElementById("addTrackingSbt").style.display = "inline";
	document.getElementById("cancelAddTracking").style.display = "inline";
}

function changeLangsDo() {
	var myForm = document.getElementById("langForm");
	var inputs = myForm.getElementsByTagName("input");
	var primaryLanguage = document.getElementById("primaryLanguage").value;
	for(a in inputs){
		inputs[a].disabled=false;
	}
	if (primaryLanguage > 0) {
		document.getElementById("language_"+primaryLanguage).disabled = true;
	}
	document.getElementById("changeLangs").style.display = "none";
	document.getElementById("cancelLangs").style.display = "inline";
	document.getElementById("saveLangs").style.display = "inline";
	document.getElementById("enableLanguagesVariable").value = 1;
}

function makePrimaryLanguage(langId) {
	var enableLanguages = document.getElementById("enableLanguagesVariable").value;
	var oldPrimaryLanguage = document.getElementById("primaryLanguage").value;
	if (enableLanguages == 1) {
		if (oldPrimaryLanguage != langId) {
			if (oldPrimaryLanguage > 0) {
				document.getElementById("langCell"+oldPrimaryLanguage).style.fontWeight = "normal";
				var oldPrimaryLangCellBox = document.getElementById("language_"+oldPrimaryLanguage);
				oldPrimaryLangCellBox.disabled = false;
			}
			document.getElementById("primaryLanguage").value = langId;
			var primaryLangCell = document.getElementById("langCell"+langId);
			var primaryLangCellBox = document.getElementById("language_"+langId);
			primaryLangCell.style.fontWeight = "bold";
			primaryLangCellBox.checked  = true;
			primaryLangCellBox.disabled = true;
			document.getElementById("primaryLangText").innerHTML = primaryLangCell.innerHTML;
		}
	}
}

function doAlert(alertText) {
	alert(alertText);
}

function editTracking(myElem, trackingId) {
	addTrackingData();
	var row = myElem.parentNode.parentNode;
	var table = row.parentNode;
	var select = document.getElementById("type");
	var optionSize = select.getElementsByTagName("option").length;
	
	select.options[optionSize] = new Option(row.getElementsByTagName("td")[0].innerHTML, trackingId, true, true);
	document.getElementById("account").value = row.getElementsByTagName("td")[1].innerHTML;
	document.getElementById("siteId").value = row.getElementsByTagName("td")[2].innerHTML;
	var buttons = table.getElementsByTagName("button");
	for (i=0;i<buttons.length;i++) {
		buttons[i].style.display = "none";
	}
}

