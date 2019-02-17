package commandline;

public class GameController {
	private GameLogic theModel;
	private Player roundWinner;
	SQL sql = new SQL();
	private static int gameID;
	private int roundID = 0;
	private int drawNum = 0;
	private Test_log log = new Test_log();

	/**
	 * The GameController constructor takes an instance of the GameLogic class, as
	 * it will call a series of methods from Game Logic to establish the flow of a
	 * playable game.
	 */

	public GameController(GameLogic theModel) {
		this.theModel = theModel;

	}

	/**
	 * The setUpGame method sets up a new game of Top Trumps, creating a shuffled
	 * deck and players.
	 */

	public void setUpGame() {
		System.out.println("\nGame Start\nRound 1\nRound 1: Players have drawn their cards.");
		System.out.println("Current players: You, AI Player 1, AI Player 2, AI Player 3, and AI Player 4.");
		gameID = sql.getTheCurrentGameID() + 1;
		theModel.shuffleDeck();
		theModel.createPlayerArray();
	}

	/**
	 * The playGame method is called runs as long as the game is played.
	 */

	public void playGame() {
		while (!theModel.isGameOver()) {
			playRoundOne();
		}

	}

	/**
	 * The playRoundOne method is called as we initially have to choose a random
	 * player to start the game and want to know if that player is the user. It is
	 * only called once.
	 */

	public void playRoundOne() {
		while (!theModel.isGameOver()) {
			if (theModel.setFirstActivePlayerAndReturnTrueIfHuman() == true) {
				humanIsActivePlayer();
				playRound();
			} else {
				computerIsActivePlayer();
				playRound();
			}

		}

	}

	/**
	 * The playRound method will be called until there is a winner and the game is
	 * over.
	 */

	public void playRound() {
		while (!theModel.isGameOver()) {
			System.out.println(
					"\nRound " + (roundID + 1) + "\nRound " + (roundID + 1) + ": Players have drawn their cards.");
			theModel.showCurrentPlayers();
			if (theModel.setActivePlayerAndReturnTrueIfHuman(roundWinner) == true) {
				humanIsActivePlayer();
			} else {
				computerIsActivePlayer();
			}
		}

	}

	/**
	 * The humanIsActivePlayer method is potentially called by both the playRoundOne
	 * and playRoundMethods if the user is the active player. We need this method as
	 * if the user is the active player, they should be able to manually input the
	 * category choice.
	 */

	public void humanIsActivePlayer() {
		theModel.displayTopCard();
		roundWinner = theModel
				.compareCards(theModel.createArrayOfCategoryValuesToBeCompared(theModel.manuallySelectCategory()));
		checkIfRoundWasWonOrWasDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(gameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
				theModel.humanIsActivePlayer);
	}

	/**
	 * The computerIsActivePlayer method is called when one of the AI Players is the
	 * active player. The AI Player will automatically select the category on their
	 * card with the highest value.
	 */

	public void computerIsActivePlayer() {
		checkIfHumanPlayerInGame();
		roundWinner = theModel
				.compareCards(theModel.createArrayOfCategoryValuesToBeCompared(theModel.autoSelectCategory()));
		checkIfRoundWasWonOrWasDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(gameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
				theModel.humanIsActivePlayer);

	}

	/**
	 * The checkIfRoundWasWonOrWasDraw method checks if a player has won a round or
	 * if the round was a draw. This is important for updating the database and also
	 * transferring cards to the round winner and displaying the winning card.
	 */

	public void checkIfRoundWasWonOrWasDraw() {
		if (theModel.getIsDraw() == true) {
			drawNum++;
		} else {
			theModel.displayWinningCard(roundWinner);
			theModel.transferCardsToRoundWinner(roundWinner);
		}
	}

	/**
	 * The checkIfHumanPlayerInGame method checks if the user is still in the game.
	 * If they are, we will continue to display their top card, regardless of
	 * whether it is their turn or not.
	 */

	public void checkIfHumanPlayerInGame() {
		if (theModel.humanPlayerOutGame() == false) {
			theModel.displayTopCard();
		}
	}

	/**
	 * The gameIsOver method is called when the game has ended, printing a message
	 * and logging information to the database and potentially to the test log.
	 */

	public void gameIsOver() {
		if (theModel.isGameOver() == true) {
			System.out.println("\nGame End\n");
			System.out.println("Overall winner was: " + theModel.getGameWinner().getName() + "!\n");
			System.out.println(sql.getAllPlayersScores());
			sql.setGameDataToSQL(gameID, roundID, theModel.getGameWinner().getName(), drawNum,
					theModel.humanIsActivePlayer);
		}
	}

}