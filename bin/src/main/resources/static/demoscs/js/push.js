let submitButton = document.getElementById("addButton");
let pONumber = document.getElementById("purOrdNum");
let sNNumber = document.getElementById("shipNotNum");
let destCountry = document.getElementById("destCountry");
let destState = document.getElementById("destState");
let destCity = document.getElementById("destCity");
let destStreet = document.getElementById("destStreet");
let destNumber = document.getElementById("destNumber");
let destZip = document.getElementById("destZip");
let supId = document.getElementById("supId");
let supName = document.getElementById("supName");
let shipDate = document.getElementById("shipDate");
let shipTime = document.getElementById("shipTime");
let delDate = document.getElementById("delDate");
let delTime = document.getElementById("delTime");
let skuIds = document.getElementById("skuIds");
let errorLabel = document.getElementById("errorLabel");
var protocol = window.location.protocol;
var requestUrl = window.location.host;
var apiVersion = "/v1";
var url = protocol + '//' + requestUrl + apiVersion + '/asn/'; //for server use
let asnData;
function httpPost(theUrl, data, callback){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.response);
    }
    xmlHttp.open("POST", theUrl, true); // true for asynchronous
    xmlHttp.setRequestHeader("Content-Type", "application/hal+json; utf-8");
    xmlHttp.setRequestHeader("Accept", "application/json");
    xmlHttp.send(data);
}
function defaultTime(){
	if(shipDate.value ==""){
		shipDate.value="1970-01-01";
	}
	if(delDate.value ==""){
		delDate.value="1970-01-01";
	}
	if(shipTime.value ==""){
		shipTime.value="00:01";
	}
	if(delTime.value ==""){
		delTime.value="00:01";
	}
}
submitButton.addEventListener("click", function(){
	defaultTime();
	let shipTimeStamp = ((shipDate.valueAsNumber/1000) + (shipTime.valueAsNumber/1000))*1000;
	let delTimeStamp = ((delDate.valueAsNumber/1000) + (delTime.valueAsNumber/1000))*1000;
	skuIds.value = skuIds.value.replace(/\s+/g, '');
	let array = skuIds.value.split(',').map(Number);
	let asn = {
			"purchaseOrderNumber": pONumber.value,
			"shipNoticeNumber": sNNumber.value,
			"destinationLocation": {
				"country": destCountry.value,
				"state": destState.value,
				"city": destCity.value,
				"street": destStreet.value,
				"number": destNumber.value,
				"zipCode": destZip.value
			},
			"supplier": {
				"id": supId.value,
				"name": supName.value,
			},
			"shipDate": shipTimeStamp,
			"deliveryDate": delTimeStamp,
			"skuIds": array
	}
	asnData = JSON.stringify(asn);
	//asnData = asn;
	httpPost(url, asnData, logResponse);
});

function logResponse(data){
	let response = JSON.parse(data);
	errorLabel.textContent = "ASN with id: "+response.postedAsn.asnId+" created successfully.";
	pONumber.value = "";
	sNNumber.value = "";
	destCountry.value = "";
	destState.value = "";
	destCity.value = "";
	destStreet.value = "";
	destNumber.value = "";
	destZip.value = "";
	supId.value = "";
	supName.value = "";
	shipDate.value = "";
	shipTime.value = "";
	delDate.value = "";
	delTime.value = "";
	skuIds.value = "";
}