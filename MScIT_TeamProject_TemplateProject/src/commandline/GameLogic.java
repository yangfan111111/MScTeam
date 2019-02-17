package commandline;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameLogic {
	protected SQL sql = new SQL();
	protected Player dummyPlayer;
	protected boolean humanIsActivePlayer;
	protected Player activePlayer;
	protected String activeCategory;
	protected Player humanPlayer = new Player("You");
	protected Player aiPlayer1 = new Player("AI player 1");
	protected Player aiPlayer2 = new Player("AI player 2");
	protected Player aiPlayer3 = new Player("AI player 3");
	protected Player aiPlayer4 = new Player("AI player 4");
	protected Boolean isDraw = false;
	protected Boolean humanPlayerOutGame = false;
	protected Boolean gameOver = false;
	protected String value;
	protected Test_log log = new Test_log();
	// Cards in play for each Round
	protected ArrayList<Integer> topCards = new ArrayList<Integer>();
	// variable to store an array of player objects
	protected ArrayList<Player> playersToShuffle = new ArrayList<Player>();
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected ArrayList<CardModel> shuffledDeck = new ArrayList<CardModel>();
	// variable to store top card for each player per round
	protected ArrayList<Integer> cardsInPlay = new ArrayList<Integer>();
	// variable to store all cards in temp arraylist if there is a draw .
	protected ArrayList<CardModel> communalPile = new ArrayList<CardModel>();

	/**
	 * The shuffleDeck method creates a shuffled deck of virtual cards.
	 */
	public void shuffleDeck() {
		Collections.shuffle(sql.cardList);
		shuffledDeck = sql.cardList;
		log.writeFile("The contents of the complete deck after it has been shuffled \n");
		for (CardModel card : shuffledDeck) {
			log.writeFile(card.showData());
		}
	}

	/**
	 * The createPlayerArray method creates an array of player objects.
	 */

	public void createPlayerArray() {
		players.add(humanPlayer);
		players.add(aiPlayer1);
		players.add(aiPlayer2);
		players.add(aiPlayer3);
		players.add(aiPlayer4);
		distributeCards();
	}

	/**
	 * The distributeCards method takes the shuffled deck and distributes it evenly
	 * amongst the players.
	 */

	public void distributeCards() {
		for (int i = 0; i < shuffledDeck.size(); i++) {
			switch (i % 5) {
			case 0:
				humanPlayer.addToCurrentCards(shuffledDeck.get(i));
				break;
			case 1:
				aiPlayer1.addToCurrentCards(shuffledDeck.get(i));
				break;
			case 2:
				aiPlayer2.addToCurrentCards(shuffledDeck.get(i));
				break;
			case 3:
				aiPlayer3.addToCurrentCards(shuffledDeck.get(i));
				break;
			case 4:
				aiPlayer4.addToCurrentCards(shuffledDeck.get(i));
				break;
			}
		}
		logInitialCardInformation();
	}

	/**
	 * The logInitialCardInformation method logs the initial cards each player has
	 * to the test log file.
	 */

	public void logInitialCardInformation() {
		log.writeFile("humanPlayer drawn cards" + humanPlayer.getCurrentCards() + "\n");
		log.writeFile("aiPlayer1 drawn cards" + aiPlayer1.getCurrentCards() + "\n");
		log.writeFile("aiPlayer2 drawn cards" + aiPlayer2.getCurrentCards() + "\n");
		log.writeFile("aiPlayer3 drawn cards" + aiPlayer3.getCurrentCards() + "\n");
		log.writeFile("aiPlayer4 drawn cards" + aiPlayer4.getCurrentCards() + "\n");
	}

	/**
	 * The displayTopCard method displays the top card in the human player's virtual
	 * deck, as well as displaying the number of cards in the virtual deck. It also
	 * logs this information to the test log file.
	 */

	public void displayTopCard() {
		System.out.println("You drew " + humanPlayer.viewTopCard().getDescription() + ":");
		System.out.println("> " + "Size : " + humanPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + humanPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + humanPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + humanPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + humanPlayer.viewTopCard().getCargo());
		int cardsInDeck = humanPlayer.countCurrentCards();
		System.out.println("Number of cards in your deck: " + cardsInDeck);
		log.writeFile("The topcard is" + humanPlayer.viewTopCard().getCardID() + "\n");
		log.writeFile("There are " + cardsInDeck + " cards in your deck");
	}

	/**
	 * The setFirstPlayerAndReturnTrueIfHuman method randomly selects the first
	 * player. It returns a boolean value true if the first player is the human
	 * player. This information is needed to know whether the game should allow the
	 * human player to manually select a category.
	 */

	public Boolean setFirstActivePlayerAndReturnTrueIfHuman() {
		for (int i = 0; i < players.size(); i++) {
			playersToShuffle.add(players.get(i));
		}
		Collections.shuffle(playersToShuffle);
		if (playersToShuffle.get(0).getName().equals("You")) {
			this.activePlayer = playersToShuffle.get(0);
			this.humanIsActivePlayer = true;
			log.writeFile("The active player is " + "'" + activePlayer.getName() + "'" + "\n");
		} else {
			this.activePlayer = playersToShuffle.get(0);
			this.humanIsActivePlayer = false;
			log.writeFile("The active player is " + "'" + activePlayer.getName() + "'" + "\n");
		}
		return humanIsActivePlayer;
	}

	/**
	 * The autoSelectCategory method is called when it is an AI player's turn to
	 * choose a category. It analyses the AI player's top card and automatically
	 * selects category with the highest value.
	 */

	public String autoSelectCategory() {
		System.out.println("It is " + activePlayer.getName() + "'s turn to choose the category.");
		for (int i = 0; i < players.size(); i++) {
			log.writeFile("Current players " + players.get(i).getName() + "\n");
		}
		autoSelectCategoryArray();
		int max = 0;
		max = Collections.max(topCards);
		switch (topCards.lastIndexOf(max)) {
		case 0:
			activeCategory = "Size";
			break;
		case 1:
			activeCategory = "Speed";
			break;
		case 2:
			activeCategory = "Range";
			break;
		case 3:
			activeCategory = "Firepower";
			break;
		case 4:
			activeCategory = "Cargo";
			break;
		}
		return activeCategory;
	}

	public void autoSelectCategoryArray() {
		topCards.clear();
		this.topCards.add(activePlayer.viewTopCard().getSize());
		this.topCards.add(activePlayer.viewTopCard().getSpeed());
		this.topCards.add(activePlayer.viewTopCard().getRange());
		this.topCards.add(activePlayer.viewTopCard().getFirepower());
		this.topCards.add(activePlayer.viewTopCard().getCargo());
	}

	/**
	 * The manuallySelectCategory method is called when it is the human player's
	 * turn to select a category. It accepts an input from the human player and
	 * returns the selected category.
	 */

	public String manuallySelectCategory() {
		activePlayer = humanPlayer;
		humanPlayerChoice();
				Scanner s = new Scanner(System.in);
				this.value = s.nextLine();
				if (value.equals("1")) {
					System.out.println("You have selected Size.");
					log.writeFile("You have selected Size." + "\n");
					activeCategory = "Size";
				} else if (value.equals("2")) {
					System.out.println("You have selected Speed.");
					log.writeFile("You have selected Speed." + "\n");
					activeCategory = "Speed";

				} else if (value.equals("3")) {
					System.out.println("You have selected Range.");
					log.writeFile("You have selected Range." + "\n");
					activeCategory = "Range";

				} else if (value.equals("4")) {
					System.out.println("You have selected Firepower.");
					log.writeFile("You have selected Firepower." + "\n");
					activeCategory = "Firepower";

				} else if (value.equals("5")) {
					System.out.println("You have selected Cargo.");
					log.writeFile("You have selected Cargo." + "\n");
					activeCategory = "Cargo";
				} else {
					System.out.println("Error! Please choose a number from 1-5.");
					log.writeFile("You have selected an incorrect value." + "\n");
					manuallySelectCategory();
				}
				s.close();
				return activeCategory;
	}

	/**
	 * The humanPlayerChoice method is simply the view the human player will see
	 * when it is their turn to choose.
	 */

	public void humanPlayerChoice() {
		log.writeFile(" THE current player is:" + humanPlayer.getName() + "\n");
		System.out.println("It is your turn to select a category. The categories are: ");
		System.out.println("1: Size");
		System.out.println("2: Speed");
		System.out.println("3: Range");
		System.out.println("4: Firepower");
		System.out.println("5: Cargo");
		System.out.println("Enter the number for your attribute: ");
	}

	/**
	 * The createArrayOfCategoryValuesToBeCompared method passes in the category
	 * selected by the active player and creates an array of all the chosen category
	 * values from all the other players' cards.
	 */

	public ArrayList<Integer> createArrayOfCategoryValuesToBeCompared(String category) {
		ArrayList<Integer> categoryValuesToBeCompared = new ArrayList<Integer>();
		this.cardsInPlay = categoryValuesToBeCompared;
		if (category.equals("Size")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSize());
			}
		} else if (category.equals("Speed")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSpeed());
			}
		} else if (category.equals("Range")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getRange());
			}
		} else if (category.equals("Firepower")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getFirepower());
			}
		} else if (category.equals("Cargo")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getCargo());
			}
		}
		return categoryValuesToBeCompared;
	}

	/**
	 * The compareCards method compares all the category values in the passed in
	 * array. If the round is a draw, all the cards in play will be passed to the
	 * communal pile and the method will return the current active player. If the
	 * round has a winner, the method will return the winning player.
	 */

	public Player compareCards(ArrayList<Integer> cardsInPlay) {
		int max = Collections.max(cardsInPlay);
		int highestNum = 0;
		int instancesOfMaxNumber = 0;
		for (int i = 0; i < cardsInPlay.size(); i++) {
			if (cardsInPlay.get(i) == max) {
				instancesOfMaxNumber++;
			}
		}
		if (instancesOfMaxNumber > 1) {
			System.out.println("The round was a draw.");
			transferCardsToCommunalPile();
			log.writeFile("There round was a draw and " + activePlayer.getName()
					+ " stays on. This sometimes appears after game ended, which is expected." + "\n");
			return activePlayer;
		} else if (instancesOfMaxNumber == 1) {
			isDraw = false;
			highestNum = cardsInPlay.indexOf(max);
			System.out.println("Winning card belongs to: " + players.get(highestNum).getName() + "! ");
			log.writeFile(
					"Winning card belongs to: " + players.get(highestNum).getName() + " - Won this round ! " + "\n");
			// players.get(highestNum).viewTopCard().toString();
			return players.get(highestNum);
		}
		return dummyPlayer;
	}

	public void winningCard(Player winningPlayer) {
		System.out.println("The winning card was" + "'" + winningPlayer.viewTopCard().getDescription() + "'");
		log.writeFile("The winning card was" + "'" + winningPlayer.viewTopCard().getDescription() + "'" + "\n");
		if (activeCategory.equals("Size")) {
			equalsSizeView(winningPlayer);
		} else if (activeCategory.equals("Speed")) {
			equalsSpeedView(winningPlayer);
		} else if (activeCategory.equals("Range")) {
			equalsRangeView(winningPlayer);
		} else if (activeCategory.equals("Firepower")) {
			equalsFirepowerView(winningPlayer);
		} else if (activeCategory.equals("Cargo")) {
			equalsCargoView(winningPlayer);
		}
		log.writeFile("the activeCategory is" + activeCategory + "\n");
	}

	public void equalsSizeView(Player winningPlayer) {
		System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize() + " <---- ");
		System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
	}

	public void equalsSpeedView(Player winningPlayer) {
		System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed() + " <---- ");
		System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
	}

	public void equalsRangeView(Player winningPlayer) {
		System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange() + " <---- ");
		System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
	}

	public void equalsFirepowerView(Player winningPlayer) {
		System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower() + " <---- ");
		System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
	}

	public void equalsCargoView(Player winningPlayer) {
		System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo() + " <---- ");
	}

	public void transferWinnerCards(Player winningPlayer) {
		ArrayList<CardModel> lostCards = new ArrayList<CardModel>();
		for (int i = 0; i < players.size(); i++) {
			lostCards.add(players.get(i).loseCard());
		}
		for (int i = 0; i < lostCards.size(); i++) {
			winningPlayer.addToCurrentCards(lostCards.get(i));
		}
		checkIfPlayersOutTheGame();
		for (int i = 0; i < communalPile.size(); i++) {
			winningPlayer.addToCurrentCards(communalPile.get(i));
		}
		communalPile.clear();
	}

	public void showCurrentPlayers() {
		System.out.print("Current players: ");
		for (int i = 0; i < players.size(); i++) {
			log.writeFile("Current players " + players.get(i).getName() + "\n");
			log.writeFile("this player should have this many cards " + players.get(i).countCurrentCards() + "\n");
			log.writeFile("top card " + players.get(i).viewTopCard() + "\n");
			if (players.get(i) == players.get(players.size() - 1)) {
				System.out.print("and " + players.get(i).getName() + ".\n");
			} else {
				System.out.print(players.get(i).getName() + ", ");
			}
		}
	}

	public void checkIfPlayersOutTheGame() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getCurrentCards().size() == 0 && players.get(i) == humanPlayer) {
				if (players.get(i) == activePlayer && i < players.size() - 1) {
					activePlayer = players.get(i + 1);
				} else if (players.get(i) == activePlayer && i == players.size() - 1) {
					activePlayer = players.get(i + 1);
				}
				players.remove(players.get(i));
				i--;
				humanPlayerOutGame = true;
				System.out.println(
						"\nYou are out the game! Better luck next time!");
			} else if (players.get(i).getCurrentCards().size() == 0) {
				System.out.println("\n" + players.get(i).getName() + " is out the game!");
				if (players.get(i) == activePlayer && i < players.size() - 1) {
					activePlayer = players.get(i + 1);
				} else if (players.get(i) == activePlayer && i == players.size() - 1) {
					activePlayer = players.get(0);
				}
				players.remove(players.get(i));
				i--;
			}
		}
		checkIfGameHasBeenWon();
	}

	public Boolean setActivePlayerAndReturnTrueIfHuman(Player winningPlayer) {
		if (winningPlayer.getName() == activePlayer.getName() && winningPlayer.getName().equals("You")) {
			this.humanIsActivePlayer = true;
		} else if (winningPlayer.getName() == activePlayer.getName()) {
			this.humanIsActivePlayer = false;
		} else if (activePlayer.getName() == players.get(players.size() - 1).getName()) {
			if (humanPlayerOutGame == true) {
				activePlayer = players.get(0);
			} else {
				humanIsActivePlayer = true;
			}
		} else if (winningPlayer.getName() != activePlayer.getName()) {
			for (int i = 0; i < players.size(); i++) {
				if (activePlayer.getName() == players.get(i).getName() && i < players.size() - 1) {
					activePlayer = players.get(i + 1);
					humanIsActivePlayer = false;
					break;
				}
			}
		}
		return humanIsActivePlayer;
	}

	public void transferCardsToCommunalPile() {
		checkIfPlayersOutTheGame();
		for (int i = 0; i < players.size(); i++) {
			communalPile.add(players.get(i).loseCard());
		}
		System.out.println("The communal pile has this many cards: " + communalPile.size() + ".");
		checkIfPlayersOutTheGame();
		setIsDraw(true);
	}

	public void setIsDraw(Boolean isDrawEquals) {
		isDraw = isDrawEquals;

	}

	public Boolean getIsDraw() {

		return isDraw;

	}

	public void checkIfGameHasBeenWon() {
		if (players.size() == 1) {
			log.writeFile("The winner is " + players.get(0).getName() + "!");
			gameOver = true;
		}
	}

	public Player returnWinningPlayer() {

		return players.get(0);

	}

	public Boolean isGameOver() {
		return gameOver;
	}

	public Boolean humanPlayerOutGame() {

		return humanPlayerOutGame;
	}
}