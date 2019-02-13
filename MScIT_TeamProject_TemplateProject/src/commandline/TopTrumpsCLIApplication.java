package commandline;



import java.util.Collections;
import java.util.Scanner;



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

		GameController newGame = new GameController(gameLogic);

		// Loop until the user wants to exit the gam

		

		

	//this will change when we can present data - the boolean returned from the controller will do something different 

		while (!userWantsToQuit) {



			// ----------------------------------------------------

			// Add your game logic here based on the requirements

			// ----------------------------------------------------

			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you want to see past results or play a game?\r\n" + 
					"   1: Print Game Statistics\r\n" + 
					"   2: Play game\r\n" + 
					"Enter the number for your selection:");
			int userChoice = scanner.nextInt();
			
			if(userChoice ==2) {

			newGame.setUpGame();

			newGame.playGame();

			} else if (userChoice ==1) {
				System.out.println(sql.getGameStatistics());
			} else {
				System.out.println("Please enter either 1 or 2!");
			}

//			if (newGame.gameIsOver() == true) {
//
//				System.err.print("it com");
//				
//				userWantsToQuit = true;
//				
//			}
			
			

			//userWantsToQuit=newGame.playGame(); // use this when the user wants to exit the game

			//hmm is that confusing? maybe have a second method like to check if game is over 

		
		}

	}



}