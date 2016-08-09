function promotionHelper() {
	
	this.init = function() {
		this.form = document.forms["addform"];
		
	}
	
	this.validate = function() {
		this.init();
		var url = "projects.PromotionLinkSuggestServlet";
		if(this.form) {
			var theVal = this.form.elements["searchTerm"].value;
			if(theVal) {
				resultElement = document.getElementById("checklinkresult");
				tickElement = document.getElementById("checklinktick");
				tickElement.innerHTML = "<img src='images/loader.gif' border='0' alt='loading' />";
				url += "?term="+theVal;
				var o = new getXML(url,promoHelper.linkSuccess,promoHelper.linkFail);
			}
		}
	} 
	
	this.linkSuccess = function(rxml) {
		if (rxml.readyState == 4) {
			xml = rxml.responseXML.documentElement;
			if(xml) {
				resultElement = document.getElementById("checklinkresult");
				tickElement = document.getElementById("checklinktick");
				searchTag = xml.getElementsByTagName("search");
				pathContainer = document.getElementById("pathcontainer");
				if(searchTag) {
					theItem = xml.getElementsByTagName("status")[0];
					if(theItem) {
						document.getElementById("suggested_url").value = xml.getElementsByTagName("suggest")[0].childNodes[0].nodeValue;
						var theStatus = theItem.childNodes[0].nodeValue;
						var theFullURL = theItem.attributes.getNamedItem("url").value;
						pathContainer.style.display = "block";
						
						if(theStatus == "200") {
							resultElement.innerHTML = "<span class='errorLabel'>This link ("+theFullURL+") already exists. Please try again.</span>";
							tickElement.innerHTML = "<img src='images/ticks/cross.png' border='0' alt='cross' />";
							document.getElementById("sub_button").disabled = true;
							document.getElementById("linkcheck").disabled = false;
							setDisplayMode("validateurlbox", "block");
							
						}
						else if(theStatus == "500") {
							errorElement = xml.getElementsByTagName("error")[0];
							errorMessage = errorElement.childNodes[0].nodeValue;
							resultElement.innerHTML = "<span class='error'>"+errorMessage+"</span>";
							tickElement.innerHTML = "";
							document.getElementById("sub_button").disabled = true;
							document.getElementById("linkcheck").disabled = false;
							setDisplayMode("validateurlbox", "block");
							
						}
						else {
							tickElement.innerHTML = "<img src='images/ticks/tick.png' border='0' alt='tick' />";
							resultElement.innerHTML = "<div class='infoLabel'>The link: "+theFullURL+" is available for use.</div>";	
							 
							document.getElementById("sub_button").disabled = false;
							document.getElementById("linkcheck").disabled = true;
							setDisplayMode("validateurlbox", "none");
							
						}
						
						
					} 
				}

			}
		
		}
	}
}
promoHelper = new promotionHelper();