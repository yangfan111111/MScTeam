<!DOCTYPE html>
<html>

	<head>
		<!-- Web page title -->
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

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
			<table style="width:100%; border:0; cellspacing:0; cellpadding:0">
			<tr>
			<td align="center"><h1 align="center">hello,world!!!GameScreen</h1></td>
			</tr>
			<tr>
			<td align="center"><img id="show" alt="Sabre" align="bottom" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Sabre.jpg" width = "300" height="400"></td>
			</tr>
			<tr>
			<td align="center"><button type="button" onclick="changePicture()" style="width:60;height:40;font-size:12;">Picture</button></td>
			</tr>
			</table>
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			 var index = 0;
			 var pictureArray = new Array(12);
			     pictureArray[0] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Sabre.jpg";
				 pictureArray[1] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Orion.jpg";
				 pictureArray[2] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/350r.jpg";
			 	 pictureArray[3] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg";
			 	 pictureArray[4] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Carrack.jpg";
			 	 pictureArray[5] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Constellation.jpg";
			 	 pictureArray[6] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Hawk.jpg";
			 	 pictureArray[7] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Hornet.jpg";
			 	 pictureArray[8] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg";
			 	 pictureArray[9] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Idris.jpg";
			 	 pictureArray[10] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Merchantman.jpg";
			 	 pictureArray[11] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/Vanguard.jpg";
			 	 pictureArray[12] = "http://dcs.gla.ac.uk/~richardm/TopTrumps/m50.jpg";
			 
			function changePicture(){
				
			 index = (index+1)%13;
			 picture = document.getElementById('show'); 
			 picture.src = pictureArray[index];
				
			}
			
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