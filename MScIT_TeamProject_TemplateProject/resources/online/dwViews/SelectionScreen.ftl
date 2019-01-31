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
    <#--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">-->
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
		}
        .panel-body{
			height: 500px;
            font-size:25px;
			display: flex;
            align-items:center;
			justify-content:center;
            cursor: pointer;
        }

    </style>
</head>

<body onload="initalize()">
<!-- Call the initalize method when the page loads -->

<div class="container">

    <!-- Add your HTML Here -->
    <div>
		<h1 class="head">Top Trumps Game</h1>
    </div>
    <div class="row" style="margin-top:20px;">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">New Game</div>
                <div class="panel-body new-game">
                    Start a New Top Trumps Game
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Game Statistics</div>
                <div class="panel-body statistics">
                    Get Statistics From Past Games
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    // Method that is called on page load
    function initalize() {

        $(".new-game").click(function(){
            window.location.href="http://localhost:7777/toptrumps/game"
        });
        $(".statistics").click(function(){
            window.location.href="http://localhost:7777/toptrumps/stats"
        });
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
        xhr.onload = function (e) {
            var responseText = xhr.response; // the text of the response
            alert(responseText); // lets produce an alert
        };

        // We have done everything we need to prepare the CORS request, so send it
        xhr.send();
    }

    // This calls the helloJSONList REST method from TopTrumpsRESTAPI
    function helloWord(word) {

        // First create a CORS request, this is the message we are going to send (a get request in this case)
        var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word=" + word); // Request type and URL+parameters

        // Message is not sent yet, but we can check that the browser supports CORS
        if (!xhr) {
            alert("CORS not supported");
        }

        // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
        // to do when the response arrives
        xhr.onload = function (e) {
            var responseText = xhr.response; // the text of the response
            alert(responseText); // lets produce an alert
        };

        // We have done everything we need to prepare the CORS request, so send it
        xhr.send();
    }
</script>

</body>

</html>