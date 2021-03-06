<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!-- Web page title -->
<style type="text/css">
#content {
	border-style: inset;
	border-color: yellow;
	border-width: 3px;
}

html body {
	background: #e5e5e5;
}

.head {
	height: 100px;
	background-color: #999;
	color: #fff;
	font-size: 50px;
	border: 1px solid #ebeee7;
	box-shadow: 0 0 #333;
	padding: 15px;
	margin: 0px;
}

.head2 {
	height: 40px;
	font-size: 20px;
	padding: 8px;
	margin: 0px;
}

.cardBox {
	margin: 15px;
	float: left;
}

.leftPanel {
	margin: 15px;
	float: left;
	width: 300px;
}

.rightPanel {
	overflow: hidden;
	display:none;
}

.imageStyle {
	height: 120px;
	width: 220px;
	display: block;
}

.cardTable input {
	type: text;
	Readonly: readonly;
	disabled: disabled;
	background-color: white;
	border: 0;
	text-align: left;
	font-size: 14px;
}
</style>
<title>Top Trumps</title>

<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
<script src="https://code.jquery.com/jquery-2.1.1.js"></script>

<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->

<!-- <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css"> -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>

<script>vex.defaultOptions.className = 'vex-theme-os';</script>

<link rel="stylesheet"
	href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />

<link rel="stylesheet"
	href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">-->

<link rel="stylesheet"
	href="https://bootswatch.com/4/sketchy/bootstrap.min.css">


</head>

<body onload="initalize()"
	style="background-color: rgb(205, 213, 213, 0.6)">
	<!-- Call the initalize method when the page loads -->

	<div class="container">

		<!-- Add your HTML Here -->
		<div>
			<h1 class="head">Top Trumps Game</h1>
			<h5 class="head2 bg-info text-white">game progress</h5>
		</div>

		<div class="leftPanel">
			<div class="cardBox">
				<div class="card">
					<h3 id="showActivePlayer" class="card-header">Welcome</h3>
					<div class="card-body" style="font-size: 14px">show the
						attribute which is the active player's choice.</div>

					<div class="btn-group" role="group"
						aria-label="Button group with nested dropdown">
						<button id="selectButton" type="button"
							class="btn btn-info dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							style="display: none; font-size: 14px">
							<strong>NEXT: </strong>CATEGORY SELECTION
						</button>
						<div id="selectMenu" class="dropdown-menu" onclick="getThePlayerIndex()"
							aria-labelledby="selectButton"
							style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px); min-width: 100%;">

							<a id="selectSize" class="dropdown-item" href="#">SIZE</a> <a
								id="selectSpeed" class="dropdown-item" href="#">SPEED</a> <a
								id="selectRange" class="dropdown-item" href="#">RANGE</a> <a
								id="selectFirepower" class="dropdown-item" href="#">FIREPOWER</a>
							<a id="selectCargo" class="dropdown-item" href="#">CARGO</a>
						</div>

					</div>



					<div class="btn-group" role="group"
						aria-label="Button group with nested dropdown">

						<button id="selectPlayerBt" type="button"
							class="btn btn-info dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							style="font-size: 14px">How many AI players do you want?

						</button>

						<div id="selectMenu" class="dropdown-menu text-muted"
							aria-labelledby="selectPlayerBt"
							style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px); min-width: 100%;">

							<a id="2player" class="dropdown-item"
								onclick="getPlayerNum(this.id)">1</a> <a id="3player"
								class="dropdown-item" onclick="getPlayerNum(this.id)">2</a> <a
								id="4player" class="dropdown-item"
								onclick="getPlayerNum(this.id)">3</a> <a id="5player"
								class="dropdown-item" onclick="getPlayerNum(this.id)">4</a>

						</div>

					</div>

					<div>

						<button id="showWinner" class="btn btn-info btn-block"
							type="button" style="display: none">SHOW WINNER</button>

					</div>

					<div>

						<button id="showWinner" class="btn btn-info btn-block"
							type="button" style="display: none; font-size: 14">SHOW WINNER</button>
						<button type="button" class="btn btn-info btn-block"
							onclick="changePicture()"
							style="width: 60; height: 40; font-size: 12">Change</button>
						<button id="confirmButton" type="button" class="btn btn-info btn-block"
							onclick="createPlayers()"
							style="width: 60; height: 40; font-size: 12">CreatePlayers</button>
						<button id="AISelect" class="btn btn-info btn-block"
							type="button" style="display: none; font-size: 14"><strong>NEXT AI: </strong>CATEGORY SELECTION</button>
					</div>

				</div>

			</div>

		</div>


		<div id="cardZone" class="rightPanel">
			<div id="card1" class="cardBox">
				<div class="card">
					<h3 id="head1" class="card-header" style="background-color: green">
						<input id="number1" value=""
							style="background-color: green; border: 0; text-align: left; color: white">
					</h3>
					<div class="card-body" style="height: 40px">
						<h5 class="card-title">
							<input type="text" id="description1" Readonly="readonly"
								disabled="disabled" value=""
								style="background-color: white; border: 0; text-align: left">
						</h5>
					</div>
					<img class="imageStyle" id="show1" alt="Sabre" align="bottom"
						>
					<div class="card-body">
						<table class="cardTable">
							<tr>
								<td><input disabled="disabled" id="cardID1" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="speed1" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="cargo1" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="range1" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="firepower1" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="size1" value=""></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div id="card2" class="cardBox">
				<div class="card">
					<h3 id="head2" class="card-header" style="background-color: green">
						<input type="text" id="number2" Readonly="readonly"
							disabled="disabled" value=""
							style="background-color: green; border: 0; text-align: left; color: white">
					</h3>
					<div class="card-body" style="height: 40px">
						<h5 class="card-title">
							<input type="text" id="description2" Readonly="readonly"
								disabled="disabled" value=""
								style="background-color: white; border: 0; text-align: left">
						</h5>
					</div>
					<img class="imageStyle" id="show2" alt="Sabre" align="bottom"
						src="">
					<div class="card-body">
						<table class="cardTable">
							<tr>
								<td><input disabled="disabled" id="cardID2" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="speed2" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="cargo2" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="range2" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="firepower2" value=""></td>
							</tr>
							<tr>
								<td><input disabled="disabled" id="size2" value=""></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div id="card3" class="cardBox">
				<div class="card">
					<h3 id="head3" class="card-header" style="background-color: green">
						<input type="text" id="number3" Readonly="readonly"
							disabled="disabled" value=""
							style="background-color: green; border: 0; text-align: left; color: white">
					</h3>
					<div class="card-body" style="height: 40px">
						<h5 class="card-title">
							<input type="text" id="description3" Readonly="readonly"
								disabled="disabled" value=""
								style="background-color: white; border: 0; text-align: left">
						</h5>
					</div>
					<img class="imageStyle" id="show3" alt="Sabre" align="bottom"
						src="">

					<div class="card-body">
						<table class="cardTable">
							<tr>
								<td><input id="cardID3" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="speed3" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="cargo3" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="range3" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="firepower3" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="size3" disabled="disabled" value=""></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div id="card4" class="cardBox">
				<div class="card">
					<h3 id="head4" class="card-header" style="background-color: green">
						<input type="text" id="number4" Readonly="readonly"
							disabled="disabled" value=""
							style="background-color: green; border: 0; text-align: left; color: white">
					</h3>
					<div class="card-body" style="height: 40px">
						<h5 class="card-title">
							<input type="text" id="description4" Readonly="readonly"
								disabled="disabled" value=""
								style="background-color: white; border: 0; text-align: left">
						</h5>
					</div>
					<img class="imageStyle" id="show4" alt="Sabre" align="bottom"
						src="">
					<div class="card-body">
						<table class="cardTable">
							<tr>
								<td><input id="cardID4" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="speed4" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="cargo4" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="range4" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="firepower4" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="size4" disabled="disabled" value=""></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div id="card5" class="cardBox">
				<div class="card">
					<h3 id="head5" class="card-header" style="background-color: green">
						<input type="text" id="number5" Readonly="readonly"
							disabled="disabled" value=""
							style="background-color: green; border: 0; text-align: left; color: white">
					</h3>
					<div class="card-body" style="height: 40px">
						<h5 class="card-title">
							<input type="text" id="description5" Readonly="readonly"
								disabled="disabled" value=""
								style="background-color: white; border: 0; text-align: left">
						</h5>
					</div>
					<img class="imageStyle" id="show5" alt="Sabre" align="bottom"
						src="">
					<div class="card-body">
						<table class="cardTable">
							<tr>
								<td><input id="cardID5" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="speed5" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="cargo5" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="range5" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="firepower5" disabled="disabled" value=""></td>
							</tr>
							<tr>
								<td><input id="size5" disabled="disabled" value=""></td>
							</tr>
						</table>
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
				SQL();
			
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			//--------------------------------------------
			// parameter (Global Variables)
			  var num = 0; // num of AI player 
			  var playerlist = new Array(4); // generated playerlist from back end 
			  var PlayerCard = new Array(4); // rebuild the list 
			  var index1 = -1; // the index1 show order of player handcard 
			  var finalNum = 0; // the all player num 
			  var activePlayerIndex = 6; // activePlayerIndex 
			  var PlayerIndexList = new Array(); 
			  var lastTimeId = 1;
			  var ishuman = false;
			 
			//-------------------------------------
			
			
			// get the number of player from selection 
			  function getPlayerNum(id) {
			    var text = document.getElementById(id).innerText;
			    num = text.valueOf();			    
			    setAINum(num);			    
			    finalNum = (++num);
				console.log("finalNum"+finalNum);             
                for(var i=(finalNum+1);i<=5;i++){
                    hideCard(i);
                }               
                console.log("num is "+num--);
            }
			  
			
			// send the AI player num to back end 
			function setAINum(num){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setPlayerNum?num="+num);
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
				};
				xhr.send();
			}
			
		
			// createPlayers
			function createPlayers(){				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/createPlayers");				
				if (!xhr) {
  					alert("CORS not supported");
				}
				
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response 					
 					var json = JSON.parse(responseText); 					
 					playerlist = json;					
 					for(var i=0; i<finalNum;i++){						
 						console.log(playerlist[i]);						
 						
 						PlayerCard[i]=playerlist[i];
 					}
 	                hideComponent("selectPlayerBt");
 	                
 	                hideComponent("confirmButton");
 					return playerlist;		
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
				
			} 
			
			//---------------------------------------------
			function getThePlayerIndex() {
				console.log("activePlayerIndex: "+activePlayerIndex);
			 	console.log("activePlayerIndex: "+activePlayerIndex);
			 	console.log("activePlayerIndex: "+activePlayerIndex);
				
            }
			
			//--------------------------------------------
			function changeColor(id) {
				id = (id+1);
				head = document.getElementById('head'+id); 
				number = document.getElementById('number'+id); 
				head.style.backgroundColor = "firebrick";
				number.style.backgroundColor = "firebrick";
				lastTimeId = id;
			}
			
			function backColor(id) {
	
				head = document.getElementById('head'+id); 
				number = document.getElementById('number'+id); 
				head.style.backgroundColor = "green";
				number.style.backgroundColor = "green";
			}
			
			function showActivePlayer(id){
				message = document.getElementById('showActivePlayer');
				message.innerHTML = "The active player is "+PlayerCard[id].name;
			}
			
			//------------------------------------------------------------------------------
			// identify the first player is human?
			function identifyThePlayer() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setFirstActivePlayerAndReturnTrueIfHuman");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					//list = responseText;
 					//important!!!!!
 					var json = JSON.parse(responseText);
 					ishuman = json;	
 					if(ishuman){
 						showComponent("selectButton");
 						hideComponent('AISelect');
 					}else{
 						
 						showComponent('AISelect');
 						hideComponent("selectButton");
 					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			//----------------------------------------------------------------------------------

		     //-----------------test of next round 	 
			 function changePicture(){
				 
				console.log("xxxxxxxxxxxxxx"+parseInt(40/finalNum));
					
			 	index1 = (++index1) % (parseInt(40/finalNum));
			 	
			 	console.log("index1:"+index1);
			 	
				 // get the element 
				
			 	for(var j=1;j<(finalNum+1);j++){
			 		
			 		getElementAndSetValue(index1, j);
			 		console.log("j: "+j);
			 	}
			 	showComponent("cardZone");
			 	
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTheFirstPlayer");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);  
 					activePlayerIndex = json;
 					console.log("activePlayerIndex1111111: "+activePlayerIndex);
 					console.log("activePlayerIndex2222222: "+responseText);
 					backColor(lastTimeId);
 					changeColor(activePlayerIndex);
 					showActivePlayer(activePlayerIndex);
 					identifyThePlayer();
 					return activePlayerIndex;
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			 	
			}
			
			 function  
		    
		     //------------------------------------------------
		     // set the card data 
			 function getElementAndSetValue(index, number){
				    console.log("number; "+number);
				    console.log("number-1: "+(number-1));
				    console.log("index: "+index);
				    var N = (number-1);
				    picture = document.getElementById('show'+number); 
				 	cardID = document.getElementById('cardID'+number); 
				 	speed = document.getElementById('speed'+number);
					cargo = document.getElementById('cargo'+number);
				 	range = document.getElementById('range'+number);
				 	description = document.getElementById('description'+number);
				 	firepower = document.getElementById('firepower'+number);
				 	size = document.getElementById('size'+number);
				 	number = document.getElementById('number'+number);
				 	// set value 
				    picture.src = PlayerCard[N].currentCards[index].cardPicture;
				    cardID.value = "CardID: "+PlayerCard[N].currentCards[index].cardID;
				 	speed.value = "Speed: "+PlayerCard[N].currentCards[index].speed;
				    cargo.value = "Cargo: "+PlayerCard[N].currentCards[index].cargo;
				    range.value = "Rango: "+PlayerCard[N].currentCards[index].range;
				    description.value = ""+PlayerCard[N].currentCards[index].description;
				    firepower.value = "Firepower: "+PlayerCard[N].currentCards[index].firepower;
				    size.value = "Size: "+PlayerCard[N].currentCards[index].size;
				    number.value = PlayerCard[N].name +" [ "+ PlayerCard[N].currentCards.length +" ]";  
			 }
			 
			 
			// sql 
			function SQL(){			
				var list = new Array(39);
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/shuffleDeck");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					//list = responseText;
 					//important!!!!!
 					var json = JSON.parse(responseText);
 					cardlist = json;				  
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			
		//----------------------------------------------------- hide show 
			function hideCard(cardID) {
                var card = document.getElementById("card"+cardID);
                card.style.display = "none";
            }
		
            function showCard(cardID) {
				var card = document.getElementById("card"+cardID);
				card.style.display= "block" ;
            }

			function hideComponent(id) {
                var c = document.getElementById(id);
                c.style.display = "none";
            }

            function showComponent(id) {
                var c = document.getElementById(id);
                c.style.display = "block";
            }
         //--------------------------------------------------------   
		
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
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