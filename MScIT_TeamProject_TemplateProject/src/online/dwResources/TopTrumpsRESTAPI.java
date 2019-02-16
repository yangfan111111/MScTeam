package online.dwResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.CardModel;
import commandline.Player;
import commandline.SQL;
import commandline.Test_log;
import online.configuration.TopTrumpsJSONConfiguration;
import commandline.GameLogic;

import javax.validation.constraints.Null;
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
	int cardsPerPlayer = 0;
	private int aiPlayerNum;
	private Player humanPlayer;
	private boolean humanIsActivePlayer;
	private String activeCategory;
	private Boolean isDraw = false;
	private Player dummyPlayer;
	private Boolean isHumanPlayerOutGame = false;
	private Boolean gameOver = false;
	private Player loser = new Player("loser");
	private Player roundWinner;
//	private ArrayList<Player> players = new ArrayList<Player>();
//	private ArrayList<Player> maximumPlayers = new ArrayList<Player>();
	private ArrayList<Player> players;
	private ArrayList<Player> maximumPlayers;
	private ArrayList<CardModel> communalPile = new ArrayList<CardModel>();
	private ArrayList<Player> playersToShuffle = new ArrayList<Player>();
	private ArrayList<Integer> topCardCategoryNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> categoryValuesToBeCompared = new ArrayList<Integer>();
	int jj = 0;
	int index = 0;
	private int roundNum = 1;
	private int gameID = sql.getTheCurrentGameID()+1;
	private int drawNum = 0;
	

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
		System.err.println("restart!!!!!!!!!!!!!!");
	}

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

	
	@GET
	@Path("/setPlayerNum")
	public int setPlayerNum(@QueryParam("num") int num) throws IOException {
		
		this.aiPlayerNum = num;
		
		System.err.println("aiPlayer: "+aiPlayerNum);
		
		return this.aiPlayerNum;
	}
	// init the loser
	public void setLoserPlayer() {
		
		loser.setCurrentCards(null);

	}

	/*
	 * The shuffleDeck method creates a shuffled deck of virtual cards.
	 */
	@GET
	@Path("/shuffleDeck")
	public ArrayList<CardModel> shuffleDeck() {
		Collections.shuffle(sql.cardList);
		shuffledDeck = sql.cardList;
		return shuffledDeck;
	}
	
	@GET
	@Path("/getCurrentPlayerlist")
	public ArrayList<Player> getCurrentPlayerList () {
		
		for(int i =0; i<players.size();i++) {
			System.err.println("getCurrentPlayerList--------"+players.get(i).cardsInHand.size()+players.get(i).name);
		}
		
		return players;
	}
	/*
	 * The createMaximumPlayerArrayList populates the maximumPlayer arraylist with
	 * the maximum amount of players that could possibly play in a single game.
	 */
	public void createMaximumPlayerArrayList() {
		maximumPlayers = new ArrayList<Player>();
		
		Player humanPlayer = new Player("You");
		Player aiPlayerOne = new Player("AI player 1");
		Player aiPlayerTwo = new Player("AI player 2");
		Player aiPlayerThree = new Player("AI player 3");
		Player aiPlayerFour = new Player("AI player 4");

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
	@GET
	@Path("/createPlayers")
	public ArrayList<Player> createPlayers() throws IOException {
	
		int oneHumanPlayer = 1;

		players = new ArrayList<Player>();
		maximumPlayers = new ArrayList<Player>();
		//shuffledDeck = shuffleDeck();
		cardsPerPlayer = shuffledDeck.size() / (aiPlayerNum+oneHumanPlayer);
		System.err.println("cardsPerPlayer"+cardsPerPlayer);
		createMaximumPlayerArrayList();
		for (int i = 0; i < aiPlayerNum + oneHumanPlayer; i++) {
			players.add(maximumPlayers.get(i));
			
		}
		humanPlayer = players.get(0);
		jj=0;
		roundNum=0;
		communalPile = new ArrayList<CardModel>();
		
		distributeCards();
		System.err.println(players.get(1).getCurrentCards());
		//setLoserPlayer();
		return players;
	}
	
	/*
	 * 
	 * getTheFirstPlayer
	 * 
	 * 
	 * */
	@GET
	@Path("/getTheFirstPlayer")
	public int getTheFirstPlayer() {

		for (int i = 0; i < players.size(); i++) {
			playersToShuffle.add(players.get(i));
		}
		Collections.shuffle(playersToShuffle);
		this.activePlayer = playersToShuffle.get(0);
		System.out.println("The active player is " + "'" + activePlayer.getName() + "'");
		for(Player player: players) {
			if (activePlayer.name.equals(player.name)) {	
			 index = players.indexOf(player);
			}
		}
		return index;
	}
	
	@GET
	@Path("/getTheActivePlayerIndex")
	public int getTheActivePlayerIndex() {
		
		for(Player player: players) {
			
			if (roundWinner.name.equals(player.name) || activePlayer.name.equals(player.name)) {	
			 index = players.indexOf(player);
			}
		}
		return index;
	}
	
	@GET
    @Path("/setFirstActivePlayerAndReturnTrueIfHuman")
	public boolean returnTrueIfHuman() {
		
		if (activePlayer.name.equals("You")) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * The addRemainderCardsToCommunalPile works out the number of cards remaining
	 * when dividing the deck by the number of players and sends those cards to the
	 * communalPile arraylist.
	 */

	public void addRemainderCardsToCommunalPile() {
		int remainderOfDeckModuloPlayerSize = shuffledDeck.size() % players.size();
		//
		if (remainderOfDeckModuloPlayerSize>0) {
			for (int i = 0; i < remainderOfDeckModuloPlayerSize; i++) {
				communalPile.add(shuffledDeck.get(i));
			}
		}
		
	}
	
	@GET
	@Path("/getCommunalPile")
	public int getCommunalPile() {
		
		return communalPile.size();
	}

	/*
	 * The distributeCards method firstly calls the addRemainderCardsToCommunalPile
	 * to ensure that the shuffledDeck will divide evenly amongst the players. It
	 * then distributes those cards to the players.
	 */

	public void distributeCards() {
		addRemainderCardsToCommunalPile();
		
		for (int i = 0; i < players.size(); i++) {
			for ( ; jj < cardsPerPlayer*(i+1); jj++) {
				players.get(i).addToCurrentCards(shuffledDeck.get(jj));
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
		if (playersToShuffle.get(0).getName().equals("You")) {
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
	@GET
	@Path("/getHumanSelected")
	public void humanCategoryChoice(@QueryParam("categoryChoice") String categoryChoice) throws IOException {
		System.err.println("humanCategoryChoice"+categoryChoice);
		this.activeCategory = categoryChoice;
		setCategoryValuesToBeCompared();
	}

	/*
	 * The autoSelectCategory method sets a String representing the choice of
	 * category made by the active AI player.
	 */
	@GET
	@Path("/autoSelectCategory")
	public String autoSelectCategory() {
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
		setCategoryValuesToBeCompared();
		return activeCategory;
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
	@GET
	@Path("/compareCategoryValues")
	public Player compareCategoryValues() {
		
		int max = Collections.max(categoryValuesToBeCompared);
		int indexOfWinningPlayer = 0;
		int instancesOfMaxNumber = 0;
		for (int i = 0; i < categoryValuesToBeCompared.size(); i++) {
			if (categoryValuesToBeCompared.get(i) == max) {
				instancesOfMaxNumber++;
			}
		}
		System.err.println("instancesOfMaxNumber"+instancesOfMaxNumber);
		if (instancesOfMaxNumber > 1) {
			//isDraw = true;
			transferCardsToCommunalPile();
			drawNum++;
			roundNum++;
			getCurrentPlayerList();
			sql.setRoundDataToSQL(gameID, roundNum, activePlayer.name, isDraw, humanIsActivePlayer);
			return activePlayer;
		} else if (instancesOfMaxNumber == 1) {
			isDraw = false;
			indexOfWinningPlayer = categoryValuesToBeCompared.indexOf(max);
			roundWinner = players.get(indexOfWinningPlayer);
			roundNum++;
			transferWinnerCards();
			getCurrentPlayerList();
			activePlayer = roundWinner;
			sql.setRoundDataToSQL(gameID, roundNum, roundWinner.name, isDraw, humanIsActivePlayer);
			return roundWinner;
		}else {
			roundNum++;
			getCurrentPlayerList();
			return dummyPlayer;
		}
		
		

		/*
		 * Note: dummyPlayer should never get returned as one of the above conditions
		 * will always be true, but has to be there to stop Java showing an error.
		 */

	}
	
	@GET
	@Path("/getCurrentRoundNum")
	public int getCurrentRoundNum() {
		return roundNum;
	}
	/*
	 * The setActivePlayerAndReturnTrueIfHuman method takes in a Player instance and
	 * using that information sets the active player. It also returns whether or not
	 * the human player is the active player, so that the manual selection view is
	 * presented to the human player.
	 */

	public Boolean setActivePlayerAndReturnTrueIfHuman(Player roundWinnerOrActivePlayer) {
		if (roundWinnerOrActivePlayer.getName().equals(activePlayer.getName())
				&& roundWinnerOrActivePlayer.getName().equals("You")) {
			this.humanIsActivePlayer = true;
		} else if (roundWinnerOrActivePlayer.getName().equals(activePlayer.getName())) {
			this.humanIsActivePlayer = false;
		} else if (activePlayer.getName().equals(players.get(players.size() - 1).getName())) {
			if (isHumanPlayerOutGame == true) {
				activePlayer = players.get(0);
			} else {
				humanIsActivePlayer = true;
			}
		} else if (!roundWinnerOrActivePlayer.getName().equals(activePlayer.getName())) {
			for (int i = 0; i < players.size(); i++) {
				if (activePlayer.getName().equals(players.get(i).getName()) && i < players.size() - 1) {
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
		System.err.println("transferCardsToCommunalPile"+isDraw);
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
			int num = 0;
			System.err.println("*******************************************"+num++);
			if (players.get(0).name.equals("You")) {
				sql.setGameDataToSQL(gameID, roundNum, players.get(0).name, drawNum, true);
			}else {
				sql.setGameDataToSQL(gameID, roundNum, players.get(0).name, drawNum, false);
			}
			
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
	//-----------------------------------
	@GET
	@Path("/isDraw")
	public Boolean isDraw() {

		return isDraw;

	}
	@GET
	@Path("/isGameOver")
	public Boolean isGameOver() {
		
		return gameOver;
	}
	
	@GET
	@Path("/isHumanPlayerOutGame")
	public Boolean isHumanPlayerOutGame() {

		return isHumanPlayerOutGame;
	}
	
	@GET
	@Path("/getGameStatis")
	public String getGameStatis() {
		System.err.println(sql.getGameStatistics());
		return sql.getGameStatistics();
	}
	
	@GET
	@Path("/getAllPlayersScores")
	public String getAllPlayersScores() {
		
		return "The Winner is: "+players.get(0).name +"\n"+ sql.getAllPlayersScores();
	}

}