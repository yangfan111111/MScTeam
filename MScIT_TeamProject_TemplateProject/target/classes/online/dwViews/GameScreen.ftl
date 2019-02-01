<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<#--<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<#--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">-->
        <link rel="stylesheet" href="https://bootswatch.com/4/sketchy/bootstrap.min.css">

        <style>
            html body {
                background: #e5e5e5;
            }
            .head{
                height: 100px;
                background-color: #999;
                color: #fff;
                font-size:50px;
                border: 1px solid #ebeee7;
                box-shadow: 0 0 #333;
                padding: 15px;
				margin: 0px;
            }
			.head2{
				height: 40px;
				font-size: 20px;
                padding: 8px;
				margin: 0px;
			}
			.cardBox{
				margin:15px;
				float: left;
			}
			.leftPanel{
				margin:15px;
				float: left;
                width:300px;
			}
			.rightPanel{
                overflow:hidden;
			}

			.imageStyle{
                height: 120px;
				width: 220px;
				display: block;
			}
		</style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    <div class="container">

        <!-- Add your HTML Here -->
        <div>
            <h1 class="head">Top Trumps Game</h1>
            <h5 class="head2 bg-info text-white">game progress</h5>
        </div>

        <div class="leftPanel">
            <div class="cardBox">
                <div class="card">
                    <h3 class="card-header">Who is the active player</h3>
                    <div class="card-body">
                        show the attribute which is the active player's choice.
                    </div>

                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <button id="selectButton" type="button" class="btn btn-info dropdown-toggle"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <strong>NEXT: </strong>CATEGORY SELECTION
                        </button>
                        <div id="selectMenu" class="dropdown-menu" aria-labelledby="selectButton"
                             x-placement="bottom-start"
                             style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px); min-width:100%;">
                            <a id="size" class="dropdown-item" href="#">SIZE</a>
                            <a id="speed" class="dropdown-item" href="#">SPEED</a>
                            <a id="range" class="dropdown-item" href="#">RANGE</a>
                            <a id="firepower" class="dropdown-item" href="#">FIREPOWER</a>
                            <a id="cargo" class="dropdown-item" href="#">CARGO</a>
                        </div>
                    </div>

                    <div>
                        <button id="showWinner" class="btn btn-info btn-block" type="button">SHOW WINNER</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="rightPanel">
            <div id="card1" class="cardBox">
                <div class="card">
                    <h3 class="card-header">Card1 Header</h3>
                    <div class="card-body" style="height: 40px">
                        <h5 class="card-title">Card1 Title</h5>
                    </div>
                    <img class="imageStyle"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image">
                    <div class="card-body">
                        show attributes1</br>
                        show attributes2</br>
                        show attributes3</br>
                        show attributes4</br>
                        show attributes5
                    </div>
                </div>
            </div>

            <div id="card2" class="cardBox">
                <div class="card">
                    <h3 class="card-header">Card2 Header</h3>
                    <div class="card-body" style="height: 40px">
                        <h5 class="card-title">Card2 Title</h5>
                    </div>
                    <img class="imageStyle"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image">
                    <div class="card-body">
                        show attributes1</br>
                        show attributes2</br>
                        show attributes3</br>
                        show attributes4</br>
                        show attributes5
                    </div>
                </div>
            </div>

            <div id="card3" class="cardBox">
                <div class="card">
                    <h3 class="card-header">Card3 Header</h3>
                    <div class="card-body" style="height: 40px">
                        <h5 class="card-title">Card3 Title</h5>
                    </div>
                    <img class="imageStyle"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image">
                    <div class="card-body">
                        show attributes1</br>
                        show attributes2</br>
                        show attributes3</br>
                        show attributes4</br>
                        show attributes5
                    </div>
                </div>
            </div>

            <div id="card4" class="cardBox">
                <div class="card">
                    <h3 class="card-header">Card4 Header</h3>
                    <div class="card-body" style="height: 40px">
                        <h5 class="card-title">Card4 Title</h5>
                    </div>
                    <img class="imageStyle"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image">
                    <div class="card-body">
                        show attributes1</br>
                        show attributes2</br>
                        show attributes3</br>
                        show attributes4</br>
                        show attributes5
                    </div>
                </div>
            </div>

            <div id="card5" class="cardBox">
                <div class="card">
                    <h3 class="card-header">Card5 Header</h3>
                    <div class="card-body" style="height: 40px">
                        <h5 class="card-title">Card5 Title</h5>
                    </div>
                    <img class="imageStyle"
                         src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22318%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20318%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_158bd1d28ef%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_158bd1d28ef%22%3E%3Crect%20width%3D%22318%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22129.359375%22%20y%3D%2297.35%22%3EImage%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                         alt="Card image">
                    <div class="card-body">
                        show attributes1</br>
                        show attributes2</br>
                        show attributes3</br>
                        show attributes4</br>
                        show attributes5
                    </div>
                </div>
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
				//helloJSONList();
				//helloWord("Student");

                hideWinnerButton();
                //hideSelectButton();

//                for(var i=1;i++;i<5){
//                    hideCard(i);
//				}
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

			function hideCard(cardID) {
                var card = document.getElementById("card"+cardID);
                card.style.display = "none";
            }

            function showCard(cardID) {
				var card = document.getElementById("card"+cardID);
				card.style.display= "block" ;
            }

            function hideWinnerButton(){
                var button = document.getElementById("showWinner");
                button.style.display = "none";
			}

			function showWinnerButton() {
                var button = document.getElementById("showWinner");
                button.style.display = "block";
            }

            function hideSelectButton(){
                var button = document.getElementById("selectButton");
                button.style.display = "none";
			}

            function showSelectButton(){
                var button = document.getElementById("selectButton");
                button.style.display = "block";
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