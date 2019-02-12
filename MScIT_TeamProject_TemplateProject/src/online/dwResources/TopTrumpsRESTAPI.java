package online.dwResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.CardModel;
import commandline.Player;
import commandline.SQL;
import commandline.Test_log;
import online.configuration.TopTrumpsJSONConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	private SQL sql = new SQL();
	private Test_log log = new Test_log();
	private ArrayList<CardModel> shuffledDeck = new ArrayList<CardModel>();
	protected Player activePlayer;

	private int aiPlayerNum;
	private Player humanPlayer;
	private boolean humanIsActivePlayer;
	private String activeCategory;
	private Boolean isDraw = false;
	private Player dummyPlayer;
	private Boolean isHumanPlayerOutGame = false;
	private Boolean gameOver = false;
	private Player roundWinner;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Player> maximumPlayers;
	private ArrayList<CardModel> communalPile = new ArrayList<CardModel>();
	private ArrayList<Player> playersToShuffle = new ArrayList<Player>();
	private ArrayList<Integer> topCardCategoryNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> categoryValuesToBeCompared = new ArrayList<Integer>();

	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	@GET
	@Path("/OnlineSQL")
	public ArrayList<CardModel> onlineSQL() {

		ArrayList<CardModel> cardList;

		SQL sql = new SQL();

		cardList = sql.cardList;

		return cardList;

	}

	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String. We
	 * also illustrate here how we can convert Java objects to JSON strings.
	 * 
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");

		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * 
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello " + Word;
	}

	@GET
	@Path("/setPlayerNum")
	public void setPlayerNum(int num) throws IOException {
		this.aiPlayerNum = num;
	}

	/*
	 * The shuffleDeck method creates a shuffled deck of virtual cards.
	 */
	public void shuffleDeck() {
		Collections.shuffle(sql.cardList);
		shuffledDeck = sql.cardList;
	}

	/*
	 * The createMaximumPlayerArrayList populates the maximumPlayer arraylist with
	 * the maximum amount of players that could possibly play in a single game.
	 */
	public void createMaximumPlayerArrayList() {
		Player humanPlayer = new Player("Human");
		Player aiPlayerOne = new Player("AI 1");
		Player aiPlayerTwo = new Player("AI 2");
		Player aiPlayerThree = new Player("AI 3");
		Player aiPlayerFour = new Player("AI4");

		maximumPlayers.add(humanPlayer);
		maximumPlayers.add(aiPlayerOne);
		maximumPlayers.add(aiPlayerTwo);
		maximumPlayers.add(aiPlayerThree);
		maximumPlayers.add(aiPlayerFour);

	}

	/*
	 * The createPlayers method takes players from the maximumPlayers arraylist and
	 * adds them to the players arraylist, based on the number of AI players
	 * specified by the user plus the human player (who is at index 0).
	 */

	public void createPlayers() {
		int oneHumanPlayer = 1;
		createMaximumPlayerArrayList();
		for (int i = 0; i < aiPlayerNum + oneHumanPlayer; i++) {
			players.add(maximumPlayers.get(i));
		}
		humanPlayer = players.get(0);
		distributeCards();

	}

	/*
	 * The addRemainderCardsToCommunalPile works out the number of cards remaining
	 * when dividing the deck by the number of players and sends those cards to the
	 * communalPile arraylist.
	 */

	public void addRemainderCardsToCommunalPile() {
		int remainderOfDeckModuloPlayerSize = shuffledDeck.size() % players.size();
		for (int i = 0; i < remainderOfDeckModuloPlayerSize; i++) {
			communalPile.add(shuffledDeck.get(i));
		}
	}

	/*
	 * The distributeCards method firstly calls the addRemainderCardsToCommunalPile
	 * to ensure that the shuffledDeck will divide evenly amongst the players. It
	 * then distributes those cards to the players.
	 */

	public void distributeCards() {
		addRemainderCardsToCommunalPile();
		int cardsPerPlayer = shuffledDeck.size() / players.size();
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < cardsPerPlayer; j++) {
				players.get(i).addToCurrentCards(shuffledDeck.get(j));
			}
		}
	}

	/*
	 * The setFirstActivePlayerAndReturnTrueIfHuman method shuffles an array of
	 * players and sets the active player to whichever player is at the 0 position.
	 * It also returns a boolean depending on whether or not the active player is
	 * human. This is useful information as we need to know later whether to allow a
	 * human player to select their category or call a method that automatically
	 * selects a category.
	 */

	public Boolean setFirstActivePlayerAndReturnTrueIfHuman() {
		for (int i = 0; i < players.size(); i++) {
			playersToShuffle.add(players.get(i));
		}
		Collections.shuffle(playersToShuffle);
		if (playersToShuffle.get(0).getName().equals("Human")) {
			this.activePlayer = playersToShuffle.get(0);
			System.out.println("The active player is " + "'" + activePlayer.getName() + "'");
			this.humanIsActivePlayer = true;
		} else {
			this.activePlayer = playersToShuffle.get(0);
			this.humanIsActivePlayer = false;
		}
		return humanIsActivePlayer;
	}

	/*
	 * The humanCategoryChoice sets a String with the human player's chosen
	 * category.
	 */

	public void humanCategoryChoice(String categoryChoice) throws IOException {
		this.activeCategory = categoryChoice;
	}

	/*
	 * The autoSelectCategory method sets a String representing the choice of
	 * category made by the active AI player.
	 */

	public void autoSelectCategory() {
		topCardCategoryNumbers.clear();
		int max = 0;
		this.topCardCategoryNumbers.add(activePlayer.viewTopCard().getSize());
		this.topCardCategoryNumbers.add(activePlayer.viewTopCard().getSpeed());
		this.topCardCategoryNumbers.add(activePlayer.viewTopCard().getRange());
		this.topCardCategoryNumbers.add(activePlayer.viewTopCard().getFirepower());
		this.topCardCategoryNumbers.add(activePlayer.viewTopCard().getCargo());

		max = Collections.max(topCardCategoryNumbers);
		switch (topCardCategoryNumbers.lastIndexOf(max)) {
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

	}

	/*
	 * The getCategoryValuesToBeCompared sets an arraylist of integer values,
	 * representing the active category values from all the players' cards.
	 */

	public void setCategoryValuesToBeCompared() {
		categoryValuesToBeCompared.clear();
		if (activeCategory.equals("Size")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSize());
			}
		} else if (activeCategory.equals("Speed")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getSpeed());
			}
		} else if (activeCategory.equals("Range")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getRange());
			}
		} else if (activeCategory.equals("Firepower")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getFirepower());
			}
		} else if (activeCategory.equals("Cargo")) {
			for (int i = 0; i < players.size(); i++) {
				categoryValuesToBeCompared.add(players.get(i).viewTopCard().getCargo());
			}
		}
	}

	/*
	 * The compareCategoryValues method compares the integers in the
	 * categoryValuesToBeCompared arraylist. If it is a draw, it returns the current
	 * active player; if there is a round winner it returns the round inner. The
	 * returned player should be passed to the setActivePlayerAndReturnTrueIfHuman
	 * method to work out the next active player.
	 */

	public Player compareCategoryValues() {
		int max = Collections.max(categoryValuesToBeCompared);
		int indexOfWinningPlayer = 0;
		int instancesOfMaxNumber = 0;
		for (int i = 0; i < categoryValuesToBeCompared.size(); i++) {
			if (categoryValuesToBeCompared.get(i) == max) {
				instancesOfMaxNumber++;
			}
		}
		if (instancesOfMaxNumber > 1) {
			transferCardsToCommunalPile();
			return activePlayer;
		} else if (instancesOfMaxNumber == 1) {
			isDraw = false;
			indexOfWinningPlayer = categoryValuesToBeCompared.indexOf(max);
			roundWinner = players.get(indexOfWinningPlayer);
			return roundWinner;
		}
		return dummyPlayer;

		/*
		 * Note: dummyPlayer should never get returned as one of the above conditions
		 * will always be true, but has to be there to stop Java showing an error.
		 */

	}

	/*
	 * The setActivePlayerAndReturnTrueIfHuman method takes in a Player instance and
	 * using that information sets the active player. It also returns whether or not
	 * the human player is the active player, so that the manual selection view is
	 * presented to the human player.
	 */

	public Boolean setActivePlayerAndReturnTrueIfHuman(Player roundWinnerOrActivePlayer) {
		if (roundWinnerOrActivePlayer.getName() == activePlayer.getName()
				&& roundWinnerOrActivePlayer.getName().equals("Human")) {
			this.humanIsActivePlayer = true;
		} else if (roundWinnerOrActivePlayer.getName() == activePlayer.getName()) {
			this.humanIsActivePlayer = false;
		} else if (activePlayer.getName() == players.get(players.size() - 1).getName()) {
			if (isHumanPlayerOutGame == true) {
				activePlayer = players.get(0);
			} else {
				humanIsActivePlayer = true;
			}
		} else if (roundWinnerOrActivePlayer.getName() != activePlayer.getName()) {
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

	/*
	 * The transferWinnerCards method transfers the cards in play to the round
	 * winner. Note: this should only be called when it has been verified that the
	 * last round was not a draw!
	 */

	public void transferWinnerCards() {
		ArrayList<CardModel> lostCards = new ArrayList<CardModel>();
		for (int i = 0; i < players.size(); i++) {
			lostCards.add(players.get(i).loseCard());
		}
		for (int i = 0; i < lostCards.size(); i++) {
			roundWinner.addToCurrentCards(lostCards.get(i));
		}
		checkIfPlayersOutTheGame();
		for (int i = 0; i < communalPile.size(); i++) {
			roundWinner.addToCurrentCards(communalPile.get(i));
		}
		communalPile.clear();
		// Not sure above communalPile.clear() is necessary as there should be no cards there? Could delete?
	}

	/*
	 * The transferCardsToCommunalPile method is called when the round is a draw. It
	 * in turn calls the checkIfPlayersOutTheGame method, as a player can also lose
	 * when it is a draw if they have no more cards left.
	 */

	public void transferCardsToCommunalPile() {
		// checkIfPlayersOutTheGame();
		// for some reason I called the above method twice; think it is just a mistake and
		// should be deleted, but if it starts to go buggy maybe it's this?
		for (int i = 0; i < players.size(); i++) {
			communalPile.add(players.get(i).loseCard());
		}
		checkIfPlayersOutTheGame();
		setIsDraw(true);

	}

	/*
	 * The checkIfPlayersOutTheGame method checks the number of cards players hold.
	 * If the number is zero, the players are out the game.
	 */

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
				isHumanPlayerOutGame = true;
			} else if (players.get(i).getCurrentCards().size() == 0) {
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

	/*
	 * If the size of the player arraylist is one, then the game is over.
	 */

	public void checkIfGameHasBeenWon() {
		if (players.size() == 1) {
			gameOver = true;
		}
	}

	// Once the game is over the player at the 0 index will be the winning player.

	public Player getWinningPlayer() {

		return players.get(0);

	}

	public void setIsDraw(Boolean isDrawEquals) {
		isDraw = isDrawEquals;

	}

	public Boolean isDraw() {

		return isDraw;

	}

	public Boolean isGameOver() {
		return gameOver;
	}

	public Boolean isHumanPlayerOutGame() {

		return isHumanPlayerOutGame;
	}

}
