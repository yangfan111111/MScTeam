package commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {

	private SQL sql = new SQL();
	// private ArrayList<CardModel> deck;
	private Player dummyPlayer;
	protected boolean humanIsActivePlayer;
	protected Player activePlayer;
	protected String activeCategory;
	private Player humanPlayer = new Player("Human");
	private Player aiPlayer1 = new Player("AI 1");
	private Player aiPlayer2 = new Player("AI 2");
	private Player aiPlayer3 = new Player("AI 3");
	private Player aiPlayer4 = new Player("AI 4");
	private Boolean isDraw = false;
	private Boolean humanPlayerOutGame = false;
	private Boolean gameOver = false;
	private int value;
	private Test_log log = new Test_log();
	// Cards in play for each Round
	ArrayList<Integer> topCards = new ArrayList<Integer>();
	// variable to store an array of player objects
	public ArrayList<Player> playersToShuffle = new ArrayList<Player>();
	public ArrayList<Player> players = new ArrayList<Player>();

	private ArrayList<CardModel> shuffledDeck = new ArrayList<CardModel>();
	// variable to store top card for each player per round
	protected ArrayList<Integer> cardsInPlay = new ArrayList<Integer>();
	// variable to store all cards in temp arraylist if there is a draw .
	private ArrayList<CardModel> communalPile = new ArrayList<CardModel>();

	// 2) Randomise the cards .

	public void shuffleDeck() {
		Collections.shuffle(sql.cardList);
		shuffledDeck = sql.cardList;
		log.writeFile("The contents of the complete deck after it has been shuffled \n");
		for (CardModel card : shuffledDeck) {
			log.writeFile(card.showData());
		}
	}

	public void createPlayerArray() {
		players.add(humanPlayer);
		players.add(aiPlayer1);
		players.add(aiPlayer2);
		players.add(aiPlayer3);
		players.add(aiPlayer4);
		distributeCards();
	}

	// 3 ) Distribute the shuffled deck of cards evenly to all the players.
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

		log.writeFile("humanPlayer drawn cards" + humanPlayer.getCurrentCards() + "\n");
		log.writeFile("aiPlayer1 drawn cards" + aiPlayer1.getCurrentCards() + "\n");
		log.writeFile("aiPlayer2 drawn cards" + aiPlayer2.getCurrentCards() + "\n");
		log.writeFile("aiPlayer3 drawn cards" + aiPlayer3.getCurrentCards() + "\n");
		log.writeFile("aiPlayer4 drawn cards" + aiPlayer4.getCurrentCards() + "\n");

	}

	// 3) The user is shown their top card (PRINT TO CONSOLE)
	public void displayTopCard() {
		System.out.println("You drew " + humanPlayer.viewTopCard().getDescription() + ":");
		System.out.println("> " + "Size : " + humanPlayer.viewTopCard().getSize());
		System.out.println("> " + "Speed : " + humanPlayer.viewTopCard().getSpeed());
		System.out.println("> " + "Range : " + humanPlayer.viewTopCard().getRange());
		System.out.println("> " + "Firepower : " + humanPlayer.viewTopCard().getFirepower());
		System.out.println("> " + "Cargo : " + humanPlayer.viewTopCard().getCargo());
		int cardsInDeck = humanPlayer.countCurrentCards();
		// is this def minus 1?
		System.out.println("There are " + cardsInDeck + " cards in your deck");
		log.writeFile("The topcard is" + humanPlayer.viewTopCard().getCardID() + "\n");
		log.writeFile("There are " + cardsInDeck + " cards in your deck" + "\n");
	}

	/*
	 * Print top card System.out.println(myCardArray.get(0);
	 */

	// 4) First players to choose category(active player) is random. ( RANDOMISE ,
	// ArrayList containing players)

	// will I stop it working that I have players and playersToShuffle - it's just
	// presumably that we want it to round orderly based on players

	public Boolean setFirstActivePlayerAndReturnTrueIfHuman() {
		for (int i = 0; i < players.size(); i++) {
			playersToShuffle.add(players.get(i));
		}
		Collections.shuffle(playersToShuffle);
		if (playersToShuffle.get(0).getName().equals("Human")) {
			this.activePlayer = playersToShuffle.get(0);
			// System.out.println("The active player is " + "'" + activePlayer.getName() +
			// "'");
			this.humanIsActivePlayer = true;
			log.writeFile("The active player is " + "'" + activePlayer.getName() + "'" + "\n");
		} else {
			this.activePlayer = playersToShuffle.get(0);
			// System.out.println("The active player is " + "'" + activePlayer.getName() +
			// "'");
			this.humanIsActivePlayer = false;
			log.writeFile("The active player is " + "'" + activePlayer.getName() + "'" + "\n");
		}
		return humanIsActivePlayer;

	}

	// method to 1) find out if active player is human or AI. 2) select category
	// based on player.

	// currently not using this method but it could be useful during refactoring
	// haha
	public String humanOrAI(Player active) {
		if (humanIsActivePlayer == true) {
			this.activeCategory = selectCategory();
			// System.out.println(" C! " + activeCategory);
		} else {
			this.activeCategory = autoCategory();
			// System.out.println(" C 2 " +activeCategory);
		}
//	System.out.println(activeCategory);
		return activeCategory;
	}

	// AI AUTOMATICALLY SELECTS HIGHEST ATTRIBUTE;

	public String autoCategory() {

		for (int i = 0; i < players.size(); i++) {
			System.out.println("Current players " + players.get(i).getName());
			log.writeFile("Current players " + players.get(i).getName() + "\n");
		}

		topCards.clear();

		int max = 0;
		this.topCards.add(activePlayer.viewTopCard().getSize());
		this.topCards.add(activePlayer.viewTopCard().getSpeed());
		this.topCards.add(activePlayer.viewTopCard().getRange());
		this.topCards.add(activePlayer.viewTopCard().getFirepower());
		this.topCards.add(activePlayer.viewTopCard().getCargo());

		for (int i = 0; i < topCards.size(); i++) {
			System.out.println("Card value " + i + " is: " + topCards.get(i));
		}

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
		// System.out.println(max + " -->ac");
		System.out.println("Top category is: " + activeCategory);

		return activeCategory;

	}
	// 5) Player is allowed to select a category.
	public String selectCategory() {
		activePlayer = humanPlayer;
		humanPlayerChoice();
			Scanner s = new Scanner(System.in);
			this.value = s.nextInt();
			if (value == 1) {
				System.out.println("You have selected Size.");
				log.writeFile("You have selected Size." + "\n");
				activeCategory = "Size";
			} else if (value == 2) {
				System.out.println("You have selected Speed.");
				log.writeFile("You have selected Speed." + "\n");
				activeCategory =  "Speed";

			} else if (value == 3) {
				System.out.println("You have selected Range.");
				log.writeFile("You have selected Range." + "\n");
				activeCategory = "Range";

			} else if (value == 4) {
				System.out.println("You have selected Firepower.");
				log.writeFile("You have selected Firepower." + "\n");
				activeCategory = "Firepower";

			} else if (value == 5) {
				System.out.println("You have selected Cargo.");
				log.writeFile("You have selected Cargo." + "\n");
				activeCategory = "Cargo";
			} 
			else {
				System.out.println("Error! Please choose a number from 1-5.");
				log.writeFile("You have selected an incorrect value." + "\n");
				selectCategory();
			}
		return activeCategory;
	}
	
	public void humanPlayerChoice() {
		log.writeFile(" THE current player is:" + humanPlayer.getName() + "\n");
		System.out.println("It is your turn to select a category. The categories are: ");
		System.out.println("1: Size");
		System.out.println("2: Speed");
		System.out.println("3: Range" );
		System.out.println("4: Firepower");
		System.out.println("5: Cargo" );
		System.out.println("Enter the number for your attribute: ");
	}

	// 6) get the category which has been selected from each of the players top card
	// and return an array.

	public ArrayList<Integer> getSelectedCategory(String category) {
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Current players " + players.get(i).getName());
			System.out.println("this player should have this many cards " + players.get(i).countCurrentCards());
			System.out.println("top card " + players.get(i).viewTopCard());
			// log.writeFile("Current players " + players.get(i).getName()+"\n");
			log.writeFile("this player should have this many cards " + players.get(i).countCurrentCards() + "\n");
			log.writeFile("top card " + players.get(i).viewTopCard() + "\n");
		}

		ArrayList<Integer> categoryValuesToBeCompared = new ArrayList<Integer>();
		this.cardsInPlay = categoryValuesToBeCompared;
		if (category.equals("Size")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSize());
				// System.out.println(" X5x5 ");
			}
		} else if (category.equals("Speed")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSpeed());
				// System.out.println(" X1 " );
			}
		} else if (category.equals("Range")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getRange());
				// System.out.println(" X2 " );
			}
		} else if (category.equals("Firepower")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getFirepower());
				// System.out.println(" X3 " );
			}
		} else if (category.equals("Cargo")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getCargo());
				// System.out.println(" X4 " );
			}
		}
		System.out.println(activeCategory + " values being compared  --- >  " + cardsInPlay);
		return categoryValuesToBeCompared;
	}

	// 7) player with highest value =wins the round . RETURNS THE winner Player
	// Object.
	public Player compareCards(ArrayList<Integer> cardsInPlay) {
		int max = Collections.max(cardsInPlay);
		System.out.println("highest number is " + max);
		int highestNum = 0;
		int instancesOfMaxNumber = 0;
		for (int i = 0; i < cardsInPlay.size(); i++) {
			if (cardsInPlay.get(i) == max) {
				instancesOfMaxNumber++;
			}
		}
		if (instancesOfMaxNumber > 1) {
			transferCardsToCommunalPile();
			System.out.println("There round was a draw and " + activePlayer.getName()
					+ " stays on. This sometimes appears after game ended, which is expected.");
			log.writeFile("There round was a draw and " + activePlayer.getName()
					+ " stays on. This sometimes appears after game ended, which is expected." + "\n");
			return activePlayer;
		} else if (instancesOfMaxNumber == 1) {
			isDraw = false;
			highestNum = cardsInPlay.indexOf(max);
			System.out
					.println("Winning card belongs to: " + players.get(highestNum).getName() + " - Won this round ! ");
			log.writeFile(
					"Winning card belongs to: " + players.get(highestNum).getName() + " - Won this round ! " + "\n");
			players.get(highestNum).viewTopCard().toString();
			return players.get(highestNum);
		}
		return dummyPlayer;

	}

	// Displays winning card information
	public void winningCard(Player winningPlayer) {
		System.out.println("The winning card was" + "'" + winningPlayer.viewTopCard().getDescription() + "'");
		log.writeFile("The winning card was" + "'" + winningPlayer.viewTopCard().getDescription() + "'" + "\n");
		if (activeCategory.equals("Size")) {
			System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize() + " <---- ");
			System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
			System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
			System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
			System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
		} else if (activeCategory.equals("Speed")) {
			System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
			System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed() + " <---- ");
			System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
			System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
			System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
		} else if (activeCategory.equals("Range")) {
			System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
			System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
			System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange() + " <---- ");
			System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
			System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
		} else if (activeCategory.equals("Firepower")) {
			System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
			System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
			System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
			System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower() + " <---- ");
			System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo());
		} else if (activeCategory.equals("Cargo")) {
			System.out.println("> " + "Size : " + winningPlayer.viewTopCard().getSize());
			System.out.println("> " + "Speed : " + winningPlayer.viewTopCard().getSpeed());
			System.out.println("> " + "Range : " + winningPlayer.viewTopCard().getRange());
			System.out.println("> " + "Firepower : " + winningPlayer.viewTopCard().getFirepower());
			System.out.println("> " + "Cargo : " + winningPlayer.viewTopCard().getCargo() + " <---- ");
		}
		log.writeFile("the activeCategory is" + activeCategory + "\n");

	}

//	8)all card played in the round go to winner. (TEMP ARRAY LIST?)
	public void transferWinnerCards(Player winningPlayer) {
		ArrayList<CardModel> lostCards = new ArrayList<CardModel>();
		for (int i = 0; i < players.size(); i++) {
			lostCards.add(players.get(i).loseCard());
			System.out.println(lostCards.size());
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

	public void checkIfGameHasBeenWon() {
		if (players.size() == 1) {
			System.out.println("The winner is " + players.get(0).getName() + "!");
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

	public void checkIfPlayersOutTheGame() {
		for (int i = 0; i < players.size(); i++) {
			int x = i;
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
						"Human, you are out the game! You are a failure at top trumps and you are a failure at life!!");

			} else if (players.get(i).getCurrentCards().size() == 0) {
				System.out.println("This should test each player. " + players.get(i).getName() + " has this many cards "
						+ players.get(i).countCurrentCards());
				System.out.println(players.get(i).getName() + " is out the game!");
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
//		9) round winner=active player. keep track of round winner.

	public Boolean setActivePlayerAndReturnTrueIfHuman(Player winningPlayer) {
//		System.out.println("This should be player who last selected category " + activePlayer.getName());
//		System.out.println("this should be the winner of the last round " + winningPlayer.getName());
//		System.out.println(players.size());
		if (winningPlayer.getName() == activePlayer.getName() && winningPlayer.getName().equals("Human")) {
			this.humanIsActivePlayer = true;
			// System.out.println("This should be the winner of the last round after test "
			// + activePlayer.getName());
		} else if (winningPlayer.getName() == activePlayer.getName()) {
			this.humanIsActivePlayer = false;
			// System.out.println("This should be the ai winner of the last round after test
			// " + activePlayer.getName());

		} else if (activePlayer.getName() == players.get(players.size() - 1).getName()) {

			if (humanPlayerOutGame == true) {
				activePlayer = players.get(0);
				// System.out.println("This should be an AI player at start of array as human
				// player has lost "
				// + activePlayer.getName());
			} else {
				humanIsActivePlayer = true;
				// System.out.println(
				// "This should be the human player if AI 4/player at last index selected
				// category last round "
				// + activePlayer.getName());
			}

		} else if (winningPlayer.getName() != activePlayer.getName()) {
			for (int i = 0; i < players.size(); i++) {
				if (activePlayer.getName() == players.get(i).getName() && i < players.size() - 1) {
					activePlayer = players.get(i + 1);
					// System.out.println(
					// "This should be the next player after whoever selected category last round
					// unless last winner was AI 4/player at last index "
					// + activePlayer.getName());
					humanIsActivePlayer = false;
					break;
				}
			}

		}
		return humanIsActivePlayer;
		// return activePlayer;

	}

	// 10) if the round is a draw , all cards placed in the communal pile. keep
	// track of how many draws .

	// not sure why check if players out the game called twice check again???

	public void transferCardsToCommunalPile() {
		// numberOfDraws ++;
		checkIfPlayersOutTheGame();
		// CommunalPile communalPile = new CommunalPile();
		for (int i = 0; i < players.size(); i++) {
			communalPile.add(players.get(i).loseCard());
			// call method here to check if players have lost all their cards and to remove
			// them

		}
		System.out.println("The Communal Pile has this many cards " + communalPile.size());
		checkIfPlayersOutTheGame();
		setIsDraw(true);

	}

	public void setIsDraw(Boolean isDrawEquals) {
		isDraw = isDrawEquals;

	}

	public Boolean getIsDraw() {

		return isDraw;

	}
}