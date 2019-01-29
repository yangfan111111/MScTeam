package commandline;

public class GameController {
	private GameLogic theModel;
	private GameView theView;
	private Player winningPlayer;
	private Player activePlayer;
	private Boolean gameOver = false;
	SQL sql = new SQL();
	private static int GameID = 0;
	private int RoundID = 0;
	private int DrawNum = 0;
	private Test_log log = new Test_log();

	public GameController(GameLogic theModel, GameView theView) {
		this.theModel = theModel;
		this.theView = theView;

	}

	public void setUpGame() {
		//add when the game start gameID++
		GameID = sql.getTheCurrentGameID() + 1;
		System.err.println(GameID);
		theModel.shuffleDeck();
		theModel.createPlayers();
		theModel.displayTopCard();
		// theModel.chooseFirstActivePlayer();
	}

	public void playGame() {
		// maybe have the playRoun return a boolean then this returns a boolean - just
		// thought useful to set things up
		
		
		while (!theModel.isGameOver()) {
			playRoundOne();
		}
		
	}

	//
	/*
	 * currently transferring all draw cards to whoever picked last maybe have the
	 * draw return a boolean, in which case there is no winningCard method called
	 */
	public void playRoundOne() {
		
		while (!theModel.isGameOver()) {
			if (theModel.chooseFirstActivePlayer() == true) {
				// theModel.selectCategory();
				// so at this point we have returned strings?
				winningPlayer = theModel.compareCards(theModel.getSelectedCategory(theModel.selectCategory()));
				checkIfWinOrDraw();

				System.out.println("this should be the human");

				playRound();

			} else {

				winningPlayer = theModel.compareCards(theModel.getSelectedCategory(theModel.autoCategory()));
				checkIfWinOrDraw();

				// theModel.displayTopCard();
				System.out.println("this should be the ai");
				playRound();
				// return true;
			}
		}
		
		if (theModel.isGameOver()) {
			sql.setGameDataToSQL(GameID, RoundID, winningPlayer.getName(), DrawNum, theModel.humanIsActivePlayer);
		}
	}
	// ahh currently all cards going on communal pile as everytime call method is
	// true

	public void checkIfWinOrDraw() {
		if (theModel.getIsDraw() == true) {
			DrawNum++;
			activePlayer = winningPlayer;
			// cards go to communalPile

		} else {
			theModel.winningCard(winningPlayer);
			theModel.transferWinnerCards(winningPlayer);
		}

	}

	public void checkIfHumanPlayerInGame() {

		if (theModel.humanPlayerOutGame() == false) {
			theModel.displayTopCard();
		}
	}
	
	public Boolean gameIsOver() {
		if(theModel.isGameOver() == true) {
			 gameOver = true;
			 System.err.println("gameover");
			 // show the GameStatistics
			System.out.println(sql.getGameStatistics());
			System.out.println(sql.getAllPlayersScores());
			 
		}	
		return gameOver;
		
	}
// maybe just have this be true and then check if boolean 
	public void playRound() {
		while (!theModel.isGameOver()) {
			if (theModel.setActivePlayer(winningPlayer) == true) {
				// theModel.selectCategory();
				// so at this point we have returned strings?
				theModel.displayTopCard();
				winningPlayer = theModel.compareCards(theModel.getSelectedCategory(theModel.selectCategory()));
				
				checkIfWinOrDraw();
				
				RoundID++;
				
				System.err.println("\nThe current Round : " + RoundID);
				
				log.writeFile("\nThe current Round : " + RoundID);
				
				sql.setRoundDataToSQL(GameID, RoundID, winningPlayer.getName(), theModel.getIsDraw(), theModel.humanIsActivePlayer);
			
				//System.out.println("this should be the human having won round");

			} else {
				checkIfHumanPlayerInGame();
				winningPlayer = theModel.compareCards(theModel.getSelectedCategory(theModel.autoCategory()));
				checkIfWinOrDraw();
				
				RoundID++;
				
				System.err.println("\nThe current Round: " + RoundID);
				log.writeFile("\nThe current Round : " + RoundID);
				sql.setRoundDataToSQL(GameID, RoundID, winningPlayer.getName(), theModel.getIsDraw(), theModel.humanIsActivePlayer);

				//System.out.println("this should be the ai " + winningPlayer.getName() + "who has won the last round");
				// return true;
			}
		}

	}

}
