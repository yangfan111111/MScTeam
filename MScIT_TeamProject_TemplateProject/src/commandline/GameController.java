package commandline;

public class GameController {
	private GameLogic theModel;
	private Player roundWinner;
	SQL sql = new SQL();
	private static int gameID;
	private int roundID = 0;
	private int drawNum = 0;
	private Test_log log = new Test_log();

	public GameController(GameLogic theModel) {
		this.theModel = theModel;

	}

	public void setUpGame() {
		System.out.println("\nGame Start\nRound 1\nRound 1: Players have drawn their cards.");
		System.out.println("Current players: You, AI Player 1, AI Player 2, AI Player 3, and AI Player 4.");
		gameID = sql.getTheCurrentGameID() + 1;
		theModel.shuffleDeck();
		theModel.createPlayerArray();
	}

	public void playGame() {
		while (!theModel.isGameOver()) {
			playRoundOne();
		}

	}

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

	public void humanIsActivePlayer() {
		theModel.displayTopCard();
		roundWinner = theModel
				.compareCards(theModel.createArrayOfCategoryValuesToBeCompared(theModel.manuallySelectCategory()));
		checkIfWinOrDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(gameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
				theModel.humanIsActivePlayer);
	}

	public void computerIsActivePlayer() {
		checkIfHumanPlayerInGame();
		roundWinner = theModel
				.compareCards(theModel.createArrayOfCategoryValuesToBeCompared(theModel.autoSelectCategory()));
		checkIfWinOrDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(gameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
				theModel.humanIsActivePlayer);

	}

	public void checkIfWinOrDraw() {
		if (theModel.getIsDraw() == true) {
			drawNum++;
		} else {
			theModel.winningCard(roundWinner);
			theModel.transferWinnerCards(roundWinner);
		}
	}

	public void checkIfHumanPlayerInGame() {
		if (theModel.humanPlayerOutGame() == false) {
			theModel.displayTopCard();
		}
	}

	public void gameIsOver() {
		if (theModel.isGameOver() == true) {
			System.out.println("\nGame End\n");
			System.out.println("Overall winner was: " + theModel.returnWinningPlayer().getName() + "!\n");
			System.out.println(sql.getAllPlayersScores());
			log.writeFileToTestLog();
			sql.setGameDataToSQL(gameID, roundID, theModel.returnWinningPlayer().getName(), drawNum,
					theModel.humanIsActivePlayer);
		}
	}

}