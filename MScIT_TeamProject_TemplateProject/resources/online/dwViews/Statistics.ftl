<html>

<head>
    <!-- Web page title -->
    <title>Top Trumps</title>

    <!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

    <!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
    <!-- <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css"> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    <script>
        vex.defaultOptions.className = 'vex-theme-os';
    </script>
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/4/sketchy/bootstrap.min.css">
    
    <style>
        html body {
            background: #e5e5e5;
        }
        .head{
            margin-top: 5px;
            height: 100px;
            background-color: #999;
            color: #fff;
            font-size:50px;
            border: 1px solid #ebeee7;
            box-shadow: 0 0 #333;
            padding: 15px;
        }
		.panel-heading{
			height: 50px;
			font-size: 30px;
			text-align: center;
			cursor: pointer;
		}
        .panel-body{
			height: 500px;
            font-size:25px;
			display: flex;
            align-items:center;
			justify-content:center;
        }
    </style>
    
	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
	
		<div>
			<h1 class="head">Top Trumps Game</h1>
    	</div>
		

		<div class="panel panel-default">
        	<div class="panel-heading" onclick="javascript:window.location.href='http://localhost:7777/toptrumps/'">RETURN TO HOMEPAGE</div>
                <div id="statis" class="panel-body new-game">
                    
                </div>
       </div>
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				getGameStatis();
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		

			
			function getGameStatis() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameStatis");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response 					
 					console.log(responseText);
 					message = document.getElementById('statis');
 					message.innerText = responseText;
 					//return responseText;
 					
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function showGameStatis(){
				getGameStatis();
				console.log(gameStatis);
				message = document.getElementById('statis');
				message.innerHTML = gameStatis;
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
		


		</script>
		
		</body>
</html>