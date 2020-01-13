var searchAsnId = document.getElementById('asnId');
var searchPon = document.getElementById('pon');
var searchSnn = document.getElementById('snn');
var searchDestCountry = document.getElementById('destCountry');
var searchDestState = document.getElementById('destState');
var searchDestCity = document.getElementById('destCity');
var searchDestZip = document.getElementById('destZip');
var searchDestStreet = document.getElementById('destStreet');
var searchDestNumber = document.getElementById('destNumber');
var searchsupId = document.getElementById('supId');
var searchSupName = document.getElementById('supName');
var searchSkuIds = document.getElementById('skuIds');

var button = document.getElementById('reloadButton');
var label = document.getElementById('errorLabel');
var protocol = window.location.protocol;
var requestUrl = window.location.host;
var apiVersion = "/v1";
var baseUrl = protocol + '//' + requestUrl + apiVersion +'/asns/'; //for server use
//var baseUrl = 'http://localhost:32545/get/advancedShippingNotice/'; //for local use
var asnId;

let elementsArray = [
	searchAsnId, 
	searchPon, 
	searchSnn, 
	searchDestCountry, 
	searchDestState, 
	searchDestCity, 
	searchDestZip, 
	searchDestStreet, 
	searchDestNumber, 
	searchsupId, 
	searchSupName, 
	searchSkuIds
];
let elementsValueArray = [];

function buildHeader(table){
	let firstHeaderItems = ['ASN ID','PONumber','Ship Notice Number','Destination','Supplier','Shipping Date','Delivery Date','SKU IDs'];
	let secondHeaderItems = ['','','','Country','State','City','Zip Code','Street','Number','ID','Name','','',''];
	let searchHeaderItems = [
		'<input type="number" name="asnId" id="asnId" style="width: 50px;">',
		'<input type="number" name="pon" id="pon" style="width: 50px;">',
		'<input type="number" name="snn" id="snn" style="width: 50px;">',
		'<input type="text" name="destCountry" id="destCountry" style="width: 100px;">',
		'<input type="text" name="destState" id="destState" style="width: 100px;">',
		'<input type="text" name="destCity" id="destCity" style="width: 100px;">',
		'<input type="number" name="destZip" id="destZip" style="width: 70px;">',
		'<input type="text" name="destStreet" id="destStreet" style="width: 100px;">',
		'<input type="text" name="destNumber" id="destNumber" style="width: 50px;">',
		'<input type="number" name="supId" id="supId" style="width: 50px;">',
		'<input type="text" name="supName" id="supName" style="width: 100px;">',
		'',
		'',
		'<input type="number" name="skuIds" id="skuIds" style="width: 50px;">'
	];
	let tr = document.createElement('tr');
	let th;
	//build first header
	for(var i = 0; i < firstHeaderItems.length; i++){
		th = document.createElement('th');
		th.appendChild(document.createTextNode(firstHeaderItems[i]));
		if(firstHeaderItems[i] == 'Destination'){
			th.setAttribute('colspan', 6);
		}
		else if(firstHeaderItems[i] == 'Supplier'){
			th.setAttribute('colspan', 2);
		}
		else{
			th.setAttribute('colspan', 1);
		}
		tr.appendChild(th);
	}
	table.appendChild(tr);
	
	tr = document.createElement('tr');
	//build second header
	for(var i = 0; i < secondHeaderItems.length; i++){
		th = document.createElement('th');
		th.appendChild(document.createTextNode(secondHeaderItems[i]));
		tr.appendChild(th);
	}
	table.appendChild(tr);
	
	tr = document.createElement('tr');
	//build second header
	for(var i = 0; i < secondHeaderItems.length; i++){
		th = document.createElement('th');
		th.innerHTML=searchHeaderItems[i];
		tr.appendChild(th);
	}
	table.appendChild(tr);
	inputListeners();
	return table;
}

function unixToNormal(timestamp){
	let year = timestamp.getFullYear();
	let month = timestamp.getMonth()+1;
	let date = timestamp.getDate();
	let hour = timestamp.getHours();
	let min = "0" + timestamp.getMinutes();
	let sec = "0" + timestamp.getSeconds();
	let dateTime = date + '.' + month + '.' + year + ' ' + hour + ':' + min.substr(-2) + ':' + sec.substr(-2) ;
	return dateTime;
}

function buildTable(responseObj){
	var asns = Object.entries(JSON.parse(responseObj))[0][1].advancedShippingNotices;
	var oldTable = document.getElementById('outputTable');
	newTable = buildHeader(oldTable.cloneNode(false));
	let tr;

	var td;
	
	for(let i = 0; i < asns.length; i++){
		let asn = Object.entries(asns[i])
		tr = document.createElement('tr');
		for(let j = 0; j < asn.length; j++){
			if(asn[j][0] == '_links'){
				continue;
			}
			else{
				td = document.createElement('td');
				if(asn[j][0] == 'destinationLocation'){
					td.appendChild(document.createTextNode(asn[j][1].country));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].state));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].city));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].zipCode));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].street));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].number));
				}
				else if(asn[j][0] == 'supplier'){
					td.appendChild(document.createTextNode(asn[j][1].id));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(asn[j][1].name));
				}
				else if(asn[j][0] == 'shipDate' || asn[j][0] == 'deliveryDate'){
					let asnTime = unixToNormal(new Date(asn[j][1]));
					td.appendChild(document.createTextNode(asnTime));
				}
				else{
					td.appendChild(document.createTextNode(asn[j][1]));
				}
				
				tr.appendChild(td);
			}
			
		}
		newTable.appendChild(tr);
	}
	oldTable.parentNode.replaceChild(newTable, oldTable);
	filterTable();
}

function getSearchInputs(){
	elementsValueArray = [];
	elementsValueArray.push(document.getElementById('asnId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('pon').value.toUpperCase());
	elementsValueArray.push(document.getElementById('snn').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destCountry').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destState').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destCity').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destZip').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destStreet').value.toUpperCase());
	elementsValueArray.push(document.getElementById('destNumber').value.toUpperCase());
	elementsValueArray.push(document.getElementById('supId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('supName').value.toUpperCase());
	elementsValueArray.push(''); //placeholder for shipping date
	elementsValueArray.push(''); //placeholder for delivery date
	elementsValueArray.push(document.getElementById('skuIds').value.toUpperCase());
}

function filterTable() {
	  // Declare variables
	  var filter, table, tr, td, i, txtValue;
	  
	  table = document.getElementById('outputTable');
	  tr = table.getElementsByTagName("tr");
	  let filterEmpty = true;
	  for(let i = 0; i < elementsValueArray.length; i++){
		  filter = elementsValueArray[i];
		  if(filter != ""){
			  for (j = 0; j < tr.length; j++) {
				  filterEmpty = false;;
				  td = tr[j].getElementsByTagName("td")[i];
				  if (td) {
					  txtValue = td.textContent || td.innerText;
					  if (txtValue.toUpperCase().indexOf(filter) > -1) {
						  tr[j].style.display = "";
					  } 
					  else {
						  tr[j].style.display = "none";
					  }
				  }
			  }
		  }
		  else{
			  continue;
		  }
		  
	  }
	  if(filterEmpty){
		  for (j = 0; j < tr.length; j++) {
			  filterEmpty = false;;
			  tr[j].style.display = "";
		  }
	  }
	  
	}

function httpGetAsync(theUrl, callback){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
        	if(xmlHttp.response != ""){
        		callback(xmlHttp.response);
        	}
        	else{
        		label.textContent = "ASN with id: "+asnId+" not available";
        	}
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

button.addEventListener("click", function(){
	httpGetAsync(baseUrl, buildTable);
	});

document.addEventListener("keyup", function() {
	getSearchInputs();
	filterTable();
});
function inputListeners(){
	
	searchAsnId = document.getElementById('asnId');
	searchPon = document.getElementById('pon');
	searchSnn = document.getElementById('snn');
	searchDestCountry = document.getElementById('destCountry');
	searchDestState = document.getElementById('destState');
	searchDestCity = document.getElementById('destCity');
	searchDestZip = document.getElementById('destZip');
	searchDestStreet = document.getElementById('destStreet');
	searchDestNumber = document.getElementById('destNumber');
	searchsupId = document.getElementById('supId');
	searchSupName = document.getElementById('supName');
	searchSkuIds = document.getElementById('skuIds');
	
}



document.addEventListener("DOMContentLoaded", function(event) {
	httpGetAsync(baseUrl, buildTable);
});