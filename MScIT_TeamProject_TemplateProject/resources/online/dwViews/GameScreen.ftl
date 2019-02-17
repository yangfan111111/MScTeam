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
			<h5 id="gameProgress" class="head2 bg-info text-white">Game Progress</h5>
		</div>
		

		<div class="leftPanel">
			<div class="cardBox">
				<div class="card">
					<h3 id="showActivePlayer" class="card-header">Welcome</h3>
					<div id="infoPanel" class="card-body" style="font-size: 16px">show the
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
							style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px); min-width: 100%; font-size: 14px">

							<a id="selectSpeed" class="dropdown-item" href="#" onclick="getHumanSelected(this.id)">Speed</a> 
							<a id="selectCargo" class="dropdown-item" href="#" onclick="getHumanSelected(this.id)">Cargo</a>
							<a id="selectRange" class="dropdown-item" href="#" onclick="getHumanSelected(this.id)">Range</a> 
							<a id="selectFirepower" class="dropdown-item" href="#" onclick="getHumanSelected(this.id)">Firepower</a>
							<a id="selectSize" class="dropdown-item" href="#" onclick="getHumanSelected(this.id)">Size</a> 
							
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
							style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px); min-width: 100%;font-size: 14px">

							<a id="2player" class="dropdown-item"
								onclick="getPlayerNum(this.id)">1</a> <a id="3player"
								class="dropdown-item" onclick="getPlayerNum(this.id)">2</a> <a
								id="4player" class="dropdown-item"
								onclick="getPlayerNum(this.id)">3</a> <a id="5player"
								class="dropdown-item" onclick="getPlayerNum(this.id)">4</a>

						</div>

					</div>

				
					<div>
					   <button id="over" class="btn btn-info btn-block" onclick="javascript:window.location.href='http://localhost:7777/toptrumps/'"
							type="button" style="display: none; font-size: 14px">RETURN TO THE SELECT SCREEN</button>
						<button id="start" class="btn btn-info btn-block" onclick="getFirstPlayerIndex()"
							type="button" style="display: none; font-size: 14px">START GAME</button>
						<button id="showWinner" class="btn btn-info btn-block" onclick="compareCategory()"
							type="button" style="display: none; font-size: 14px">SHOW WINNER</button>
						<button id="confirmButton" type="button" class="btn btn-info btn-block"
							onclick="createPlayers()"
							style="width: 60; height: 40; font-size: 14px">CREATE PLAYERS</button>
						<button id="AISelect" class="btn btn-info btn-block" onclick="autoSelectCategory()"
							type="button" style="display: none; font-size: 14px"><strong>NEXT AI: </strong>CATEGORY SELECTION</button>
						<button id="nextRound" type="button" class="btn btn-info btn-block"
							onclick="changePicture()"
							style="width: 60; height: 40; display: none; font-size: 14px">NEXT ROUND</button>
					</div>

				</div>

			</div>

		</div>


		<div id="cardZone" class="rightPanel">
			<div id="card1" class="cardBox">
				<div class="card">
					<h3 id="head1" class="card-header" style="background-color: green">
						<input disabled="disabled" id="number1" value=""
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
				
				// 
			
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			//--------------------------------------------
			// parameter (Global Variables)
			  var num = 0; // num of AI player 
			  var playerlist = new Array(4); // generated playerlist from back end 
			  var PlayerCard = new Array(4); // rebuild the list 
			  var index1 = 0; // the index1 show order of player handcard 
			  var finalNum = 0; // the all player num 
			  var activePlayerIndex = 0; // activePlayerIndex 
			  var PlayerIndexList = new Array(); 
			  var lastTimeId = 1;
			  var ishuman = false;
			  var AISelected;
			  var activePlayer;
			  var isDraw = false;
			  var roundNum = 1;
			  var communalpileNum;
			  var HumanPlayerOutGame = false;
			  var gameOver = false;
			 

			
			// get the number of player from selection 
			  function getPlayerNum(id) {
			    var text = document.getElementById(id).innerText;
			    num = text.valueOf();	
			    message = document.getElementById('selectPlayerBt');
				message.innerHTML = num;
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
					playerlist = new Array(4); 
 					var responseText = xhr.response; // the text of the response 					
 					var json = JSON.parse(responseText); 	
 					playerlist = json;			
 					
 					if(playerlist.length>0){
 						for(var i=0; i<playerlist.length;i++){						
 	 						console.log(playerlist[i]);						
 	 						
 	 					}
 	 					//getRoundNum();
 	 					//showProgress();

 	 	                hideComponent("selectPlayerBt");
 	 	                hideComponent("confirmButton");
 	 	                showComponent("start");
 	 					return playerlist;	
 					}else{
 						alert("Please select how many players you would like to play with ! ! ")
 					}
 					
 						
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
				
			} 
			
			//-----------------------------------------------getRoundNum
			function getRoundNum() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCurrentRoundNum");				
				if (!xhr) {
  					alert("CORS not supported");
				}
				
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response 					
 					var json = JSON.parse(responseText); 					
 					roundNum = json;
 					return roundNum;
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
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
				message.innerHTML = "The active player is "+playerlist[id].name;
			}
			
			function showActiveSelected() {
				message = document.getElementById('infoPanel');
				message.innerHTML = "They selected "+ AISelected;
			}
			
			function showRoundWinner(){
				message = document.getElementById('gameProgress');
				message.innerHTML = "[Game Progress] Round "+(roundNum-1)+":  "+activePlayer.name + " won this round!";
			}
			
			function showIsDraw(){
				//getNumOfCommunalpile();
				message = document.getElementById('gameProgress');
				console.log("showIsDraw"+communalpileNum)
				message.innerHTML = "[Game Progress] Round "+roundNum+": This round was a Draw. Communal Pile now has " + communalpileNum + " cards";
			}
			
			function showProgress(){
				//getRoundNum();
				message = document.getElementById('gameProgress');
				message.innerHTML = "[Game Progress] Round "+roundNum+": Players have drawn their cards";
				hideComponent("showWinner");
			}
			
			function waitingMessage(){
				message = document.getElementById('infoPanel');
				message.innerHTML = "Waiting for selecting";
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
 						whenHumanSelect();
 						// get the human selection 
 						
 						
 					}else{
 						whenAISelect();	
 					}
				};
				//getRoundNum();
				xhr.send();
			}
			//----------------------------------------------------------------------------------
			function whenHumanSelect(){
				showComponent("selectButton");
				hideComponent('AISelect');
			}
			
			function whenAISelect(){
				showComponent('AISelect');
				hideComponent("selectButton");
			}
			//------------------------------------------------------------------------------get human selected 
			 function getHumanSelected(id) {
				
				 var text = document.getElementById(id).innerText;
				 
				 var categoryChoice = text.valueOf();
				 
				 console.log(categoryChoice);
				 //getRoundNum();
				 sendHumanSelected(categoryChoice);
				 for (var i=2; i<(playerlist.length+1); i++){
				 		showComponent("card"+i);
				 	}

			} 
			
			function sendHumanSelected(categoryChoice) {
				
				 var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanSelected?categoryChoice="+categoryChoice);
				 console.log("11111111:"+categoryChoice);
					if (!xhr) {
	  					alert("CORS not supported");
					}
					xhr.onload = function(e) {
	 					var responseText = xhr.response; // the text of the response
					};
					showWinnerWhenHumanSelect();
					xhr.send();

			}
			
			function showWinnerWhenHumanSelect(){
				hideComponent("selectButton");
				showComponent("showWinner");
			}
			
		     //-----------------test of next round 	 
			 function changePicture(){		 
	
			 	index1 = 0;
			 	
			 	console.log("index1:"+index1);
				 // get the element 
				getCurrentPlayerList();
			 	for(var j=1;j<(playerlist.length+1);j++){
			 		
			 		getElementAndSetValue(index1, j);
			 		console.log("j: "+j);
			 	}
			 	showComponent("cardZone");
			 	isHumanPlayerOutGame();
			 	getCurrentPlayerIndex();
				console.log("current active: "+activePlayerIndex);
 				backColor(lastTimeId);
 				changeColor(activePlayerIndex);
 				showActivePlayer(activePlayerIndex);
 				identifyThePlayer();
 			    showProgress();
 			    hideComponent("nextRound");
 			    

			}
		     
		    function getAllPlayersScores() {
		    	
		    	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAllPlayersScores");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					message = document.getElementById('infoPanel');
 					message.innerText = responseText;
 					
				};
				//getRoundNum();
				xhr.send();
		    	
		    }
			
			function getFirstPlayerIndex() {
				for(var j=1;j<(finalNum+1);j++){
					getElementAndSetValue(0, j);
			 		console.log("j: "+j);
			 	}
				
			 	showComponent("cardZone");
			 	for (var i=2; i<6; i++){
			 		hideComponent("card"+i);
			 	}
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTheFirstPlayer");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);  
 					activePlayerIndex = json;
 					console.log("first active: "+activePlayerIndex);
 					changeColor(activePlayerIndex);
 					showActivePlayer(activePlayerIndex);
 					identifyThePlayer();
 					showProgress();
				}
				hideComponent("start")
				xhr.send();
			}
			
			
			function getCurrentPlayerIndex(){
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTheActivePlayerIndex");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);  
 					activePlayerIndex = json;
 					console.log("after compare:current active: "+activePlayerIndex);
 					return activePlayerIndex;
				};
				//getRoundNum();
				xhr.send();
			}
			
			//-------------------------------------compare the Category value 
			function compareCategory() {
				console.log("compareCategory");
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/compareCategoryValues");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);
 					activePlayer = json;
 					console.log("-----active player:" + activePlayer.name);
 					//checkisDraw();
 					getCurrentPlayerIndex();
 					getNumOfCommunalpile();
 					//getRoundNum();
 					showProgress();
 					getRoundNum();
 					getCurrentPlayerList();
 					waitingMessage();
 					console.log("-----------after compare:current active: "+activePlayerIndex);
 					//console.log("isDraw111111:"+isDraw);
 					//hideComponent("showWinner");
 					isGameOver();
 					console.log("isgameover11111"+gameOver);
 					
 			
				};
				// We have done everything we need to prepare the CORS request, so send it
				
				xhr.send();
			
			}
			//---------------------------------------------------------------if draw get how many card in communalpile
			function getNumOfCommunalpile() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCommunalPile");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);
 					checkisDraw();
 					communalpileNum = json;
 					console.log("inner----communalpileNum"+communalpileNum);
 					return communalpileNum;
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			
			}
			
			//---------------------------------------------------------- identify which player out the game 
			function hideCardIfplayerOutGame() {
				//var hidePlayerNum =  (finalNum-playerlist.length);
				for(var i = finalNum;i>playerlist.length;i--){
					hideCard(i);
				}
			}
			
			//----------------------------------------------------------isHumanPlayerOutGame
			function isHumanPlayerOutGame() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isHumanPlayerOutGame");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);
 					HumanPlayerOutGame = json;
 					
 					if(HumanPlayerOutGame){
 						for (var i=1; i<(finalNum+1); i++){
 							hideCard(i);
 						}
 				 	}else{
 				 		for (var i=2; i<(finalNum+1); i++){
 							hideCard(i);
 						}
 				 	}
 				 	
 					return HumanPlayerOutGame;
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();

			}
			
			//-----------------------------------------------if draw 
			function checkisDraw() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isDraw");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					var json = JSON.parse(responseText);
 					isDraw = json;
 					console.log("checkisDraw():"+isDraw);
 					if (isDraw == true){
 						// show draw 
 						//getNumOfCommunalpile();
 						showIsDraw();
 						console.log("communalpileNum"+communalpileNum);
 						console.log("draw");
 							
 					}else{
 						// show winner 
 						showRoundWinner();
 						console.log("winner");
 					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			//----------------------------------------------------------------get the current player list
			function getCurrentPlayerList() {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCurrentPlayerlist");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response 					
 					var json = JSON.parse(responseText); 					
 					playerlist = json;					
 					for(var i=0; i<playerlist.length;i++){						
 						console.log(playerlist[i]);						
 						
 					}
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	

			}
			
			//---------------------------------------------- isGameOver 
			function isGameOver() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/isGameOver");	
				if (!xhr) {
  					alert("CORS not supported");
				}	
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response 					
 					var json = JSON.parse(responseText); 					
 					gameOver = json;
 					if(gameOver){
 						showComponent("over");
 						message = document.getElementById('showActivePlayer');
 						message.innerHTML = "THE GAME IS OVER";
 						getAllPlayersScores();
 					}else{
 						
 						showComponent("nextRound");
 					}
 					return gameOver;
 					
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			
			//------------------------------------autoSelectCategory
			 function autoSelectCategory(){
					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/autoSelectCategory");	
					if (!xhr) {
	  					alert("CORS not supported");
					}	
					xhr.onload = function(e) {
	 					var responseText = xhr.response; // the text of the response
	 					  
	 					AISelected = responseText;
	 					//console.log(AISelected);
	 					showActiveSelected();
	 					//getRoundNum();
	 					
					};
					// We have done everything we need to prepare the CORS request, so send it
					showWinnerWhenAISelect();
					for (var i=1; i<(finalNum+1); i++){
				 		showCard(i);
				 	}
					hideCardIfplayerOutGame();
					xhr.send();
			 }  
			
			function showWinnerWhenAISelect(){
				hideComponent("AISelect");
				showComponent("showWinner");
			}
		    
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
				    picture.src = playerlist[N].currentCards[index].cardPicture;
				    cardID.value = "CardID: "+playerlist[N].currentCards[index].cardID;
				 	speed.value = "Speed: "+playerlist[N].currentCards[index].speed;
				    cargo.value = "Cargo: "+playerlist[N].currentCards[index].cargo;
				    range.value = "Range: "+playerlist[N].currentCards[index].range;
				    description.value = ""+playerlist[N].currentCards[index].description;
				    firepower.value = "Firepower: "+playerlist[N].currentCards[index].firepower;
				    size.value = "Size: "+playerlist[N].currentCards[index].size;
				    number.value = playerlist[N].name +" [ "+ playerlist[N].currentCards.length +" ]";  
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