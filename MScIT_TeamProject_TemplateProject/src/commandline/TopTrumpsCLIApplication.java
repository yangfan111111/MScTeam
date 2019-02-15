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
	//	if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		SQL sql = new SQL();
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			// ----------------------------------------------------

			// Add your game logic here based on the requirements

			// ----------------------------------------------------
			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you want to see past results or play a game?\r\n" + 
					"   1: Print Game Statistics\r\n" + 
					"   2: Play Game\r\n" + 
					"   3: Quit Game\r\n" + 
					"Enter the number for your selection:");
			int userChoice = scanner.nextInt();
			if(userChoice ==2) {
				newGame();
			} else if (userChoice ==1) {
				System.out.println(sql.getGameStatistics());
			} else if (userChoice ==3){
				userWantsToQuit=true;
			}
				else {
				System.out.println("Please enter either 1, 2, or 3!");
			}
		}

	}
	
	public static void newGame() {
		GameLogic gameLogic = new GameLogic();
		GameController newGame = new GameController(gameLogic);
		newGame.setUpGame();
		newGame.playGame();
		newGame.gameIsOver();
	}

}