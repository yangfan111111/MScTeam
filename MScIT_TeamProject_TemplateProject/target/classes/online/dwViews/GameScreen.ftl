<!DOCTYPE html>

<html>

	<head>
		<!-- Web page title -->
		<style type="text/css">
			#content{
				border-style:inset;
				border-color:yellow;
				border-width:3px;
			}
			
			</style>
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()" style="background-color:rgb(205,213,213,0.6)"> <!-- Call the initalize method when the page loads -->
    
    	
    	
    	<div class="container">

			<!-- Add your HTML Here -->
		<span id ="content" style = "position:absolute;top:10%;right:20%;display:block;background-color:white">
			<table style="width:200px">
			<tr align="center" style="background-color:green">
			<td><input type="text" id="number" Readonly="readonly" disabled="disabled" value="" style="background-color:green; border:0 ;text-align:left ;color:white"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="description" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><img id="show" alt="Sabre" align="bottom" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Sabre.jpg" width = "200" height="100"></td>
			</tr>
			<tr>
			<td align="center"><button type="button" onclick="changePicture()" style="width:60;height:40;font-size:12">Change</button></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="cardID" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="speed" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="cargo" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="range" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="firepower" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			<tr align="center">
			<td><input type="text" id="size" Readonly="readonly" disabled="disabled" value="" style="background-color:white; border:0 ;text-align:left"></td>
			</tr>
			</table>
		</span>
			
		</div>
		

		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				SQL();
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			 var index = 0;
			 var cardlist = new Array(39);
			 
			function SQL(){
				
				var list = new Array(39);
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/OnlineSQL");
				
				if (!xhr) {
  					alert("CORS not supported");
				}
				
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					//list = responseText;
 					//important!!!!!
 					var json = JSON.parse(responseText);
 				    //console.log(json);
 				    // default 
 				    number = document.getElementById('number');
 				    cardID = document.getElementById('cardID'); 
 				    speed = document.getElementById('speed');
 				    cargo = document.getElementById('cargo');
 				    range = document.getElementById('range');
 				    description = document.getElementById('description');
 				    firepower = document.getElementById('firepower');
 				    size = document.getElementById('size');
 				    // set value 
 				    number.value = "playName "+"cardNum";
 				    cardID.value = "CardID: "+json[0].cardID;
 				    speed.value = "Speed: "+json[0].speed;
 				    cargo.value = "Cargo: "+json[0].cargo;
 				    range.value = "Rango: "+json[0].range;
 				    description.value = "Description: "+json[0].description;
 				    firepower.value = "Firepower: "+json[0].firepower;
 				    size.value = "Size: "+json[0].size;
 				    
 				    cardlist = json;
 				    return cardlist;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			 
			function changePicture(){
				
			 	index = (index+1)%40;
				 // get the element 
				picture = document.getElementById('show'); 
			 	cardID = document.getElementById('cardID'); 
			 	speed = document.getElementById('speed');
				cargo = document.getElementById('cargo');
			 	range = document.getElementById('range');
			 	description = document.getElementById('description');
			 	firepower = document.getElementById('firepower');
			 	size = document.getElementById('size');
			 	number = document.getElementById('number');
			 	// set the value for all 
			 	picture.src = cardlist[index].cardPicture;
			 	cardID.value = "CardID: "+cardlist[index].cardID;
			 	speed.value = "Speed: "+cardlist[index].speed;
			    cargo.value = "Cargo: "+cardlist[index].cargo;
			    range.value = "Rango: "+cardlist[index].range;
			    description.value = "Description: "+cardlist[index].description;
			    firepower.value = "Firepower: "+cardlist[index].firepower;
			    size.value = "Size: "+cardlist[index].size;
			    number.value = "playName "+"cardNum";
			 
			}
			
			// -----------------------------------------------------------------------
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<script type="text/javascript">
		
		</script>
		
		
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>