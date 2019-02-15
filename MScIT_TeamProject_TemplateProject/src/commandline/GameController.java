package commandline;

public class GameController {
	private GameLogic theModel;
	private Player roundWinner;
	SQL sql = new SQL();
	private static int GameID;
	private int roundID = 0;
	private int drawNum = 0;
	private Test_log log = new Test_log();

	public GameController(GameLogic theModel) {
		this.theModel = theModel;

	}

	public void setUpGame() {
		System.out.println("\nGame Start\nRound 1\nRound 1: Players have drawn their cards.");
		GameID = sql.getTheCurrentGameID() + 1;
		//System.err.println("gameID: " + GameID);
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
			System.out.println("\nRound " + (roundID+1) + "\nRound " + (roundID+1) + ": Players have drawn their cards.");
			if (theModel.setActivePlayerAndReturnTrueIfHuman(roundWinner) == true) {
				humanIsActivePlayer();
				//break;
			} else {
				computerIsActivePlayer();
			}
		}

	}

	public void humanIsActivePlayer() {
		theModel.displayTopCard();
		roundWinner = theModel.compareCards(theModel.getSelectedCategory(theModel.selectCategory()));
		checkIfWinOrDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(GameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
				theModel.humanIsActivePlayer);
	}

	public void computerIsActivePlayer() {
		checkIfHumanPlayerInGame();
		roundWinner = theModel.compareCards(theModel.getSelectedCategory(theModel.autoCategory()));
		checkIfWinOrDraw();
		roundID++;
		log.writeFile("\nThe current Round : " + roundID);
		sql.setRoundDataToSQL(GameID, roundID, roundWinner.getName(), theModel.getIsDraw(),
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
			System.out.println("Overall winner was " + theModel.returnWinningPlayer().getName());
			//System.out.println(sql.getGameStatistics());
			System.out.println(sql.getAllPlayersScores());
			log.writeFileToTestLog();
			sql.setGameDataToSQL(GameID, roundID, theModel.returnWinningPlayer().getName(), drawNum,
					theModel.humanIsActivePlayer);
		}
	}

}