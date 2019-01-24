package commandline;



import java.util.Collections;



/**

 * Top Trumps command line application

 */

public class TopTrumpsCLIApplication {



	/**

	 * This main method is called by TopTrumps.java when the user specifies that they want to run in

	 * command line mode. The contents of args[0] is whether we should write game logs to a file.

 	 * @param args

	 */

	public static void main(String[] args) {



		boolean writeGameLogsToFile = false; // Should we write game logs to file?

		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		SQL sql = new SQL();

		Collections.shuffle(sql.cardList);


		System.out.print(sql.cardList.size());

		// State

		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		GameLogic gameLogic = new GameLogic();

		GameView gameView = new GameView();

		GameController newGame = new GameController(gameLogic, gameView);

		// Loop until the user wants to exit the gam

		

		

	//this will change when we can present data - the boolean returned from the controller will do something different 

		while (!userWantsToQuit) {



			// ----------------------------------------------------

			// Add your game logic here based on the requirements

			// ----------------------------------------------------

			

			newGame.setUpGame();

			newGame.playGame();

			

			if (newGame.gameIsOver() == true) {

				userWantsToQuit = true;

			}

			//userWantsToQuit=newGame.playGame(); // use this when the user wants to exit the game

			//hmm is that confusing? maybe have a second method like to check if game is over 

		
		}

	}



}