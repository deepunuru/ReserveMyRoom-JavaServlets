function init() {
	completeField = document.getElementById("searchId");
	completeTable = document.getElementById("complete-table");
	autoRow = document.getElementById("auto-row");
	
	cityField = document.getElementById("city");
	cityTable = document.getElementById("cityTable");
	autoRow = document.getElementById("cityTblRow");
}

function doCompletion() {
	var url = "http://localhost/EWAProject/Controller/AjaxUtility?action=complete&searchId=" + escape(completeField.value);
	req = initRequest();
	req.open("GET",url,false);
	req.setRequestHeader("Content-type", "text/xml");	
	req.send(); 
	req.onreadystatechange = callback();	
}

function doCompletionCity() {
	var url = "http://localhost/EWAProject/Controller/CityAutoComplete?action=complete&city=" + escape(cityField.value);
	req = initRequest();
	req.open("GET",url,false);
	req.setRequestHeader("Content-type", "text/xml");	
	req.send(); 
	req.onreadystatechange = callbackCity();		
}

function initRequest() {
	if(window.XMLHttpRequest) {
		if(navigator.userAgent.indexOf('MSIE')!=-1){
			isIE = true;
		}		
		return new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function appendHotel(hotelName,hotelID){
	completeTable.style.display = 'table';
    var row = completeTable.insertRow(0);
    var cell = row.insertCell(0);
    var linkElement;
    linkElement = document.createElement("a");
    linkElement.setAttribute("href","/EWAProject/Controller/DisplayHotel?id=" + hotelID);
    linkElement.appendChild(document.createTextNode(hotelName));
    cell.appendChild(linkElement);    
}

function appendCity(cityName,stateName,stateID,zipCode){
	cityTable.style.display = 'table';
    var row = cityTable.insertRow(0);
    var cell = row.insertCell(0);
    var linkElement;
    linkElement = document.createElement("a");
    linkElement.setAttribute("href","javascript:void(0)");
    linkElement.setAttribute("onClick","insertText(this)");
    linkElement.setAttribute("name",cityName);
    linkElement.setAttribute("id",stateName);
    linkElement.appendChild(document.createTextNode(cityName + ", " + stateName + ", " + stateID + ", " + zipCode));
    cell.appendChild(linkElement); 
}

function insertText(txt){
	document.getElementById("city").value = txt.innerHTML;
	document.getElementById("cityHidden").value = txt.name;
	document.getElementById("stateHidden").value = txt.id;
	clearCityTable();
}

function parseMessages(responseXML) {
	if(responseXML == null){		
		return false;
	}
	else {
		var hotels = responseXML.getElementsByTagName("hotels")[0];
		if(hotels.childNodes.length > 0){
			completeTable.setAttribute("bordercolor","#DCDCDC");
			completeTable.setAttribute("border","1");			
			for(loop = 0;loop < hotels.childNodes.length;loop++){
				var hotel = hotels.childNodes[loop];
				var hotelName = hotel.getElementsByTagName("hotelName")[0];
				var hotelID = hotel.getElementsByTagName("id")[0];
				appendHotel(hotelName.childNodes[0].nodeValue,hotelID.childNodes[0].nodeValue);
			}
		}
	}
}

function parseCityMessages(responseXML) {
	if(responseXML == null){		
		return false;
	}
	else {
		var cities = responseXML.getElementsByTagName("cities")[0];
		if(cities.childNodes.length > 0){
			cityTable.setAttribute("bordercolor","#DCDCDC");
			cityTable.setAttribute("border","1");			
			for(loop = 0;loop < cities.childNodes.length;loop++){
				var city = cities.childNodes[loop];
				var cityName = city.getElementsByTagName("cityName")[0];
				var stateName = city.getElementsByTagName("stateName")[0];
				var stateID = city.getElementsByTagName("stateID")[0];
				var zipCode = city.getElementsByTagName("zipCode")[0];
				appendCity(cityName.childNodes[0].nodeValue,stateName.childNodes[0].nodeValue,stateID.childNodes[0].nodeValue,zipCode.childNodes[0].nodeValue);
			}
		}
	}
}

function clearTable() {
	if(completeTable.getElementsByTagName("tr").length > 0){
		completeTable.style.display = 'none';
		for(loop = completeTable.childNodes.length - 1;loop >= 0;loop--){
			completeTable.removeChild(completeTable.childNodes[loop]);
		}
	}
}
function clearCityTable() {
	if(cityTable.getElementsByTagName("tr").length > 0){
		cityTable.style.display = 'none';
		for(loop = cityTable.childNodes.length - 1;loop >= 0;loop--){
			cityTable.removeChild(cityTable.childNodes[loop]);
		}
	}
}

function callback() {
	clearTable();
	if(req.readyState == 4){		
		if(req.status == 200){			
			parseMessages(req.responseXML);
			document.getElementById('complete-table').style.backgroundColor = "#F5F5F5";
			document.getElementById('complete-table').style.width = "220px";
			document.getElementById("complete-table").style.borderRadius = "6px";
			document.getElementById('complete-table').style.fontSize = "13px";
			document.getElementById('complete-table').style.padding = "4px";
			document.getElementById('complete-table').style.border = "thin solid #DCDCDC";
		}
	}
}

function callbackCity() {
	clearCityTable();
	if(req.readyState == 4){		
		if(req.status == 200){			
			parseCityMessages(req.responseXML);
			document.getElementById('cityTable').style.backgroundColor = "#F5F5F5";
			document.getElementById('cityTable').style.width = "220px";
			document.getElementById('cityTable').style.borderRadius = "6px";
			document.getElementById('cityTable').style.fontSize = "13px";
			document.getElementById('cityTable').style.padding = "4px";
			document.getElementById('cityTable').style.border = "thin solid #DCDCDC";
		}
	}
}