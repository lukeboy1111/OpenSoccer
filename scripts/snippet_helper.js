function snippetHelper() {
	
	this.init = function() {
		this.lastSnippet = 0;
		this.lastZone = 0;
	}
	

	this.saveSnippet = function(snippetId) {
		this.snippetId = snippetId;
		this.elementName = "snippet"+snippetId;
		this.form = document.forms["snippetform"+snippetId];
		if(this.form) {
			this.element = this.form.elements[this.elementName];
			if(this.element) {
				this.contentValue = this.element.value;
				this.save();
			}
		}
	}
	
	this.saveSnippetMCE = function(snippetId) {
		this.contentValue = tinyMCE.activeEditor.getContent({format : 'html'});
		if(this.contentValue) {
			this.snippetId = snippetId;
			this.elementName = "snippet"+snippetId;
			this.form = document.forms["snippetform"+snippetId];
			this.element = this.form.elements["content"];
			this.save();
		}
		else {
			this.close();
		}
		
		
	}
	
	
	this.save = function() {
		var url = "projects.FileUploadServlet";
		var args = "snippetid="+this.snippetId+"&content="+this.contentValue;
		var o = new getPostXML(url, args, this.saveSnippetSuccess, this.fail);
		
	}
	
	this.saveSnippetSuccess = function(rxml) {
		if (rxml.readyState == 4) {
			var result = jsonParse(rxml.responseText);
	         
	        if(result.success.toString() == "true") {
	        	
	        	snippetService.zoneSuccess();
	        	snippetService.closeLastSnippet();
	        	snippetService.getLatestUpdates();
	        }
	        else {
	        	//alert("fail: "+result["error"]);
	        	snippetService.zoneFail(result["error"]);
	        	snippetService.closeLastSnippet();
	        	
	        }
	        
	       
		}
	}
	
	
	
	this.zoneSuccess = function() {
		this.getZoneContainer();
		if(this.zoneInfo) {
			this.zoneInfo.innerHTML = "<div class='infoLabel'>The change was processed successfully</div><div class='clear'></div>";
		}
		setTimeout("snippetService.timeOut()", 5500);
	}
	
	this.zoneFail = function(msg) {
		this.getZoneContainer();
		console.dir(this.zoneInfo);
		if(this.zoneInfo) {
			this.zoneInfo.innerHTML = "<div class='infoLabel'>"+msg+"</div><div class='clear'></div>";
		}
		setTimeout("snippetService.timeOut()", 5500);
	}
	
	this.getZoneContainer = function() {
		this.zoneInfo = document.getElementById("zoneUpdate");
	}
	
	this.timeOut = function() {
		this.getZoneContainer();
		if(this.zoneInfo) {
			this.zoneInfo.innerHTML = "";
		}
	}
	
	this.alterSnippet = function(snippetId) {
		if(this.lastSnippet) {
			this.close();
		}
		this.id = "snippet"+snippetId;
		this.el = document.getElementById(this.id);
		this.el.className = "transparency";
		this.el.style.display = "block";
		this.lastSnippet = snippetId;
		var leftPos = document.documentElement.scrollLeft;
		var topPos = document.documentElement.scrollTop;
		
		this.el.style.top = topPos+"px";
		


	}
	
	this.alterSnippetMCE = function(snippetId) {
		this.alterSnippet(snippetId);
		tinyMCE.init({
			mode : "textareas",
			theme : "simple"
		});
	}
	
	this.closeLastSnippet = function() {
		if(this.lastSnippet) {
			this.close();
		}
	}
	
	this.getLatestUpdates = function() {
		
		var snippetURL = "projects.XMLDispatcherServlet?action=snippet";
		var o2 = new getXML(snippetURL, this.getSnippetsSuccess, this.fail);
		
	}
	
	this.getSnippetsSuccess = function(rxml) {
		if (rxml.readyState == 4) {
			xml = rxml.responseXML.documentElement;
			if(xml) {
				tickElement = document.getElementById("checklinktick");
				searchTag = xml.getElementsByTagName("zones");
				if(searchTag) {
					zoneItems = xml.getElementsByTagName("zone");
					for(i = 0; i < zoneItems.length; i++) {
						var zone = zoneItems[i];
						var snippets = zone.getElementsByTagName("snippet");
						for(j = 0; j < snippets.length; j++) {
							theItem = snippets[j];
							var id = theItem.attributes.getNamedItem("id").value;
							var content = theItem.getElementsByTagName("content")[0].childNodes[0].nodeValue;
							var editContent = theItem.getElementsByTagName("editcontent")[0].childNodes[0].nodeValue;
							var contentElement = "snipContent"+id;
							var contentElementObject = document.getElementById(contentElement);
							if(contentElementObject) {
								contentElementObject.innerHTML = content;
							}
							var editFormID = "snippetform"+id;
							var editForm = document.forms[editFormID];
							if(editForm) {
								var editElement = editForm.elements["content"];
								if(editElement) {
									try {
										editElement.value = editContent;
									}
									catch(e) {
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	this.close = function() {
		this.id = "snippet"+this.lastSnippet;
		this.el = document.getElementById(this.id);
		if(this.el) {
			this.el.style.display = "none";
			this.el.className = "transparency-none";
			
		}
		
	}
	
	this.addImageURL = function(snippetId, url) {
		this.id = "snipContent"+snippetId;
		this.el = document.getElementById(this.id);
		if(this.el) {
			this.el.innerHTML = "<img src='"+url+"' alt='img' border='0' />";
		}
	}
	
	this.addImageError = function(snippetId, error) {
		this.id = "snipContent"+snippetId;
		this.el = document.getElementById(this.id);
		if(this.el) {
			this.el.innerHTML = "<div class='error'>Error: "+error+"</div>";
		}
	}
	
	this.disableImageUpload = function(snippetId) {
		this.close();
	}
	
	this.toggleZone = function(zoneId) {
		this.overallZone = "zoneContainer"+zoneId;
		this.navCloseZone = "zoneOpenNavigation"+zoneId;
		this.navOpenZone = "zoneCloseNavigation"+zoneId;
		this.zoneContainer = document.getElementById(this.overallZone);
		this.navOpenZoneContainer = document.getElementById(this.navOpenZone);
		this.navCloseZoneContainer = document.getElementById(this.navCloseZone);
		
		if(this.zoneContainer) {
			if(this.zoneContainer.style.display == "block") {
				this.zoneContainer.style.display = "none";
				this.navOpenZoneContainer.style.display = "none";
				this.navCloseZoneContainer.style.display = "block";
				this.lastZone = 0;
			}
			else {
				this.lastZone = zoneId;
				this.zoneContainer.style.display = "block";
				this.navOpenZoneContainer.style.display = "block";
				this.navCloseZoneContainer.style.display = "none";
			}
		}
		
	}
	
	
	
}

function addLoadEvent(f){var o=window.onload;if(typeof window.onload!='function'){window.onload=f}else{window.onload=function(){o();f()}}}

var snippetService = new snippetHelper();