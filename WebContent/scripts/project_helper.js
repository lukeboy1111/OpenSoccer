function showNewForm() {
	document.getElementById("issueform").style.display = "block";
}

function disableDomains() {
	var theForm = document.forms["addform"];
	if(theForm) {
		theValue = theForm.elements["nodomain"].checked;
		var websiteElement = theForm.elements["website"];
		var domainElement = theForm.elements["domain"];
		if(theValue) {
			document.getElementById("domainsweb").style.display = "none";
			websiteElement.value="";
			domainElement.value="";
		}
		else {
			document.getElementById("domainsweb").style.display = "block";
		}
	}
}



function noFolderForAddPage() {
	selectSearchResult("", "");
	return("");
}


function changeSelectWebsite() {
	var theForm = document.forms["addform"];
	if(theForm) {
		var websiteElement= theForm.elements["website"];
		var domainElement = theForm.elements["domain"];
		var websiteElementValue = websiteElement.value;
		var domainElementValue = domainElement.value;
		if(websiteElementValue) {
			domainElement.value="";
		}
	}
	
}

function changeSelectDomain() {
	var theForm = document.forms["addform"];
	if(theForm) {
		var websiteElement= theForm.elements["website"];
		var domainElement = theForm.elements["domain"];
		var websiteElementValue = websiteElement.value;
		var domainElementValue = domainElement.value;
		if(domainElementValue) {
			websiteElement.value="";
		}
	}
	
}

var isSearching = 0;
var currentlySelected = "";
function doSearch() {
	var theForm = document.forms["addform"];
	if(theForm) {
		var url = "projects.create.xml.PathSearchServlet?term=";
		var searchValue = theForm.elements["searchTerm"].value;
		searchValue = searchValue.replace(" ", "-");
		if(searchValue && searchValue.length > 1 && !(isSearching)) {
			url += searchValue;
			var o = new getXML(url,searchSuccess,searchFail);	
		}
		
	}	
}

function searchSuccess(rxml) {
	if (rxml.readyState == 4) {
		xml = rxml.responseXML.documentElement;
		if(xml) {
			isSearching = 0;
			
			f2 = xml.getElementsByTagName("option");
			var container = document.createElement("div");
			
			psDebugger.log("Length="+f2.length);
			var searchResultContainer = document.getElementById("selectedContainer");
			
			if(f2.length > 0) {
				var searchText = document.createElement("div");
				searchText.className = "heading";
				searchText.innerHTML = "Your search matches "+f2.length+" results, please choose: ";
				container.appendChild(searchText);
				var element = document.createElement("div");
				element.className = "searchResult";
				element.innerHTML = "<a href='JavaScript: selectSearchResult(\"\", \"\");'>I will choose manually</a>";
				container.appendChild(element);	
				
				searchResultContainer.innerHTML = "";
				container.className = "searchResults";
				var odd = 0;
				for(i = 0; i < f2.length; i++) {
					var item = f2[i];
					var theOptionValue = item.childNodes[0].nodeValue;
					var theOptionId = item.attributes.getNamedItem("value").value;
					var element = document.createElement("div");
					if(odd > 0) {
						odd = 0;
						element.className = "searchOdd";
					}
					else {
						odd = 1;
						element.className = "searchEven";
					}
					
					element.innerHTML = "<a href='JavaScript: selectSearchResult("+theOptionId+", \""+theOptionValue+"\");'>"+theOptionValue+"</a>";
					container.appendChild(element);
				}
				searchResultContainer.appendChild(container);
			}
			else {
				searchResultContainer.innerHTML = "";
				var searchText = document.createElement("div");
				searchText.className = "heading";
				searchText.innerHTML = "Your search matches "+f2.length+" results, please choose: ";
				container.appendChild(searchText);
				var element = document.createElement("div");
				element.className = "searchResult";
				element.innerHTML = '<a href="JavaScript: showChooser();">No Results were found. Enter URL manually....</a>';
				container.appendChild(element);
				searchResultContainer.appendChild(container);
				
			}
									
		}
	}
}

function clearSearchContainer() {
	var searchResultContainer = document.getElementById("selectedContainer");
	searchResultContainer.innerHTML = "";
}


function clearSearch() {
	isSearching = 0;
}

function showChooser() {
	document.getElementById("pathcontainer").style.display = "block";	
	document.getElementById("stage1container").style.display = "none";
	document.getElementById("hintcontainer").style.display = "block";
	var theForm = document.forms["addform"];
	if(theForm) {
		theForm.elements["path_manual"].value = "1";
		var searchValue = theForm.elements["searchTerm"].value;
		theForm.elements["suggested_url"].value = theForm.elements["searchTerm"].value;
	}
}

function selectSearchResult(id, theValue) {
	clearSearchContainer();
	// At this stage we want to do the following:
	//
	// put full URL in 
	//
	//
	var theForm = document.forms["addform"];
	if(theForm) {
		theForm.elements["path"].value = id;
		theForm.elements["suggested_url"].value = theValue;
		var theInfo = document.createElement("span");
		theInfo.className = "path";
		theInfo.innerHTML = theValue;
		document.getElementById("hintcontainer").style.display = "none";
		//document.getElementById("selectcontainer").innerHTML = "";
		//document.getElementById("selectcontainer").appendChild(theInfo);
	}
	document.getElementById("stage1container").style.display = "none";
	document.getElementById("pathcontainer").style.display = "block";
}

function chooseAgain() {
	clearSearchContainer();
	var theForm = document.forms["addform"];
	
	if(theForm) {
		theForm.elements["path"].value = "";
	}
	document.getElementById("hintcontainer").style.display = "none";
	document.getElementById("stage1container").style.display = "block";
	document.getElementById("pathcontainer").style.display = "none";
}

function checkValidLink() {
	var url = "projects.create.xml.CheckValidLinkServlet";
	var theForm = document.forms["addform"];
	
	if(theForm) {
		var theVal = theForm.elements["suggested_url"].value;
		if(theVal) {
			resultElement = document.getElementById("checklinkresult");
			tickElement = document.getElementById("checklinktick");
			tickElement.innerHTML = "<img src='images/loader.gif' border='0' alt='loading' />";
			url += "?link="+theVal;
			var o = new getXML(url,linkSuccess,linkFail);
		}
	}
}

function linkSuccess(rxml) {
	if (rxml.readyState == 4) {
		xml = rxml.responseXML.documentElement;
		if(xml) {
			resultElement = document.getElementById("checklinkresult");
			tickElement = document.getElementById("checklinktick");
			searchTag = xml.getElementsByTagName("search");
			if(searchTag) {
				theItem = xml.getElementsByTagName("status")[0];
				
				if(theItem) {
					theStatus = theItem.childNodes[0].nodeValue;
					
					var theFullURL = theItem.attributes.getNamedItem("url").value;
					if(theStatus == "200") {
						resultElement.innerHTML = "<span class='errorLabel'>This link ("+theFullURL+") already exists. Please try again.</span>";
						tickElement.innerHTML = "<img src='images/ticks/cross.png' border='0' alt='cross' />";
						document.getElementById("sub_button").disabled = true;
						document.getElementById("linkcheck").disabled = false;
					}
					else if(theStatus == "500") {
						errorElement = xml.getElementsByTagName("error")[0];
						errorMessage = errorElement.childNodes[0].nodeValue;
						resultElement.innerHTML = "<span class='error'>"+errorMessage+"</span>";
						tickElement.innerHTML = "";
						document.getElementById("sub_button").disabled = true;
						document.getElementById("linkcheck").disabled = false;
					}
					else {
						tickElement.innerHTML = "<img src='images/ticks/tick.png' border='0' alt='tick' />";
						resultElement.innerHTML = "<div class='infoLabel'>The link: "+theFullURL+" passes.</div>";	
						setTimeout("clearInfoElement()", 1250);
						document.getElementById("sub_button").disabled = false;
						document.getElementById("linkcheck").disabled = true;
					}
					
				}
			}

		}
	
	}
}

function clearTickElement() {
	resultElement = document.getElementById("checklinktick");
	document.getElementById("sub_button").disabled = true;
	document.getElementById("linkcheck").disabled = false;
	setDisplayMode("validateurlbox", "block");
	resultElement.innerHTML = "";
}

function setDisplayMode(theElementName, theMode) {
	var el = document.getElementById(theElementName);
	if(el) {
		el.style.display = theMode;
	}
}

function clearInfoElement() {
	resultElement = document.getElementById("checklinkresult");
	resultElement.innerHTML = "";
}

function linkFail(rxml) {
	
}

function showTemplatePreview() {
	var theForm = document.forms["addform"];
	if(theForm) {
		var templateId = theForm.elements["template"].value;
		
		var url = "view/project/template/preview/xml.jsp?id="+templateId;
		var o = new getXML(url,tplSuccess,tplFail);
	}
	
}

function tplSuccess(rxml) {
	if (rxml.readyState == 4) {
		xml = rxml.responseXML.documentElement;
		if(xml) {
			resultElement = document.getElementById("checklinkresult");
			tickElement = document.getElementById("checklinktick");
			searchTag = xml.getElementsByTagName("search");
			if(searchTag) {
				imgElement = xml.getElementsByTagName("image");
				if(imgElement) {
					img_url = imgElement[0].childNodes[0].nodeValue;
					var theElement = document.getElementById("tplpreview");
					if(theElement) {
						theElement.style.display = "block";
						//<a href="image1.jpg"><img src="thumb_image1.jpg" width="72" height="72" alt="" /></a>
						theElement.innerHTML = "<a id='imgpreview' href='"+img_url+"'><img src='"+img_url+"' height='155' border='0' alt='preview' /></a>";
						//document.getElementById("tplpreview").innerHTML = "test";
						$('#imgpreview').lightBox();
					}
				}
			}
		}
	}
}

function tplFail(rxml) {
		
}
var domainSearching = 0;
function domainSearch() {
	var url = "view/project/add/newPage/search/domain/xml.jsp";
	var theForm = document.forms["addform"];
	
	if(theForm && domainSearching == 0) {
		var theVal = theForm.elements["website_search"].value;
		if(theVal) {
			if(theVal.length > 0) {
				resultElement = document.getElementById("searchlinkresult");
				tickElement = document.getElementById("searchlinktick");
				if(tickElement) {
					tickElement.innerHTML = "<img src='images/loader.gif' border='0' alt='loading' />";
				}
				url += "?search="+theVal;
				domainSearching = 1;
				var o = new getXML(url,domainSearchSuccess,searchFail);
			}
		}
	}
}

function domainSearchSuccess(rxml) {
	if (rxml.readyState == 4) {
		xml = rxml.responseXML.documentElement;
		if(xml) {
			domainSearching = 0;
			searchTag = xml.getElementsByTagName("search");
			if(searchTag) {
				var container = document.createElement("div");
				count = xml.getElementsByTagName("count")[0].childNodes[0].nodeValue;
				if(count > 0) {
					var searchText = document.createElement("div");
					searchText.className = "heading";
					searchText.innerHTML = "Your search matches "+count+" results, please choose: ";
					container.appendChild(searchText);
					var element = document.createElement("div");
					element.className = "searchResult";
					element.innerHTML = "<a href='JavaScript: selectDomainSearchResult(\"\");'>I will choose manually</a>";
					container.appendChild(element);	
					listResults = xml.getElementsByTagName("result");
					var odd = 0;
					for(i = 0; i < listResults.length; i++) {
						domainResult = listResults[i];
						listSites = domainResult.getElementsByTagName("site");
						
						for(j = 0; j < listSites.length; j++) {
							theSite = listSites[j];	
							theSiteId = theSite.getElementsByTagName("id")[0].childNodes[0].nodeValue;
							theSiteFullName = theSite.getElementsByTagName("full")[0].childNodes[0].nodeValue;
							var element2 = document.createElement("div");
							if(odd > 0) {
								odd = 0;
								element2.className = "searchOdd";
							}
							else {
								odd = 1;
								element2.className = "searchEven";
							}
							element2.innerHTML = "<a href='JavaScript: selectDomainSearchResult("+theSiteId+");'>"+theSiteFullName+"</a>";
							container.appendChild(element2);	
						}
					}
				}
				else {
					var searchText = document.createElement("div");
					searchText.className = "heading";
					searchText.innerHTML = "Your search did not match any results.";
					container.appendChild(searchText);
					var element = document.createElement("div");
					element.className = "searchResult";
					element.innerHTML = "<a href='JavaScript: selectDomainSearchResult(\"\");'>I will choose manually</a>";
					container.appendChild(element);	
				}
				document.getElementById("domainsearch").innerHTML = "";
				document.getElementById("domainsearch").appendChild(container);
			}
			
		}
	}
}

function searchFail() {
	
}

function selectDomainSearchResult(theItem) {
	var theForm = document.forms["addform"];
	
	if(theForm) {
		if(theItem > 0) {
			theForm.elements["website"].value = theItem;	
		}
		document.getElementById("domainsearch").innerHTML = "";
		//searchTermBox
		showDiv("searchTermBox");
		showDiv("searchTermSelectAgain");
		showDiv("websiteManualSelect");
	}
}

function searchDomainAgain() {
	showDiv("searchTermBox");
	showDiv("searchTermSelectAgain");
	showDiv("websiteManualSelect");
}



function metaInformation() {
	showDiv("settings");
}

function showDiv(elementName) {
	var settingsObject = document.getElementById(elementName);
	if(settingsObject) {
		if(settingsObject.style.display == "none") {
			settingsObject.style.display = "block";
		}
		else {
			settingsObject.style.display = "none";
		}
	}
	
} 

function ajaxRequest() {
	   var activexmodes = ["Msxml2.XMLHTTP", "Microsoft.XMLHTTP"];
	   if (window.ActiveXObject) {for (var i = 0; i < activexmodes.length; i++) {try {return new ActiveXObject(activexmodes[i])}catch (e) {}}}
	   else return  (window.XMLHttpRequest) ? new XMLHttpRequest(): flase;
}

function getXML(a, b, c) {
	   var s = this;
	   this.XMLHTTP = new ajaxRequest();
	   this.Fail = c;
	   this.Success = function () { eval(b(this.XMLHTTP)) };
	   this.XMLHTTP.onreadystatechange = function () { stateChange(s.XMLHTTP, s); }
	   this.XMLHTTP.open("GET", a, true);
	   this.XMLHTTP.send(null);
	   function stateChange(d, e) {
	       if(d.readyState===4){(d.status === 200 | d.status === 0)?e.Success():e.Fail()}
	   }

}

function getPostXML(url, params, successFn, failFn) {
	   var s = this;
	   this.XMLHTTP = new ajaxRequest();
	   this.Fail = failFn;
	   this.Success = function () { eval(successFn(this.XMLHTTP)) };
	   this.XMLHTTP.onreadystatechange = function () { stateChange(s.XMLHTTP, s); }
	   this.XMLHTTP.open("POST", url, true);
	   
	   //Send the proper header information along with the request
	   this.XMLHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   this.XMLHTTP.setRequestHeader("Content-length", params.length);
	   this.XMLHTTP.setRequestHeader("Connection", "close");
	   this.XMLHTTP.send(params);
	   function stateChange(d, e) {
	       if(d.readyState===4){(d.status === 200 | d.status === 0)?e.Success():e.Fail()}
	   }
}

function psDebugObject() {
	this.log = function(a) {
		try {
			console.log(a);
		}
		catch(e) {
			
		}
		
	}	
}
var psDebugger = new psDebugObject();
