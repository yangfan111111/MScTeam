package commandline;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class TopTrumpsCLIApplicationTest {

	protected SQL sql = new SQL();

	protected Player p;

	protected CardModel c;

	protected GameLogic g;

	/**
	 * This test distributes the deck of cards into five CardModel ArrayLists .
	 * Each Array List represents the players cards in hand.
	 * assertEquals tests whether the getCurrentCards() output is the same as the CardModel ArrayList input for each player.
	 * The assertEquals test evaluates to true which shows that when the cards are distributed they are stored in the player object as an ArrayList.
	 */

	@Test

	public void testToEnsurePlayersHaveRecievedTheirCards() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffleDeck();

		g.createPlayerArray();

		

		ArrayList<CardModel> human = new ArrayList<CardModel>();

		ArrayList<CardModel> ai1 = new ArrayList<CardModel>();

		ArrayList<CardModel> ai2 = new ArrayList<CardModel>();

		ArrayList<CardModel> ai3 = new ArrayList<CardModel>();

		ArrayList<CardModel> ai4 = new ArrayList<CardModel>();

		for (int i = 0; i < g.shuffledDeck.size(); i++) {

			

			switch (i % 5) {



			case 0:

				human.add(g.shuffledDeck.get(i));

				break;



			case 1:

				ai1.add(g.shuffledDeck.get(i));

				break;



			case 2:

				ai2.add(g.shuffledDeck.get(i));

				break;



			case 3:

				ai3.add(g.shuffledDeck.get(i));

				break;



			case 4:

				ai4.add(g.shuffledDeck.get(i));

				break;

			}

		}

		Assert.assertEquals(human,g.humanPlayer.getCurrentCards());

		Assert.assertEquals(ai1,g.aiPlayer1.getCurrentCards());

		Assert.assertEquals(ai2,g.aiPlayer2.getCurrentCards());

		Assert.assertEquals(ai3,g.aiPlayer3.getCurrentCards());

		Assert.assertEquals(ai4,g.aiPlayer4.getCurrentCards());



	};

	/**
	 * The following test is to check the countCurrentCards() method in the player class returns the correct number of cards.
	 * In the first round each player should have 8 card objects in their cardsInHand Array List.
	 * assertEquals tests whether each player countCurrentCards() output is the same as 8.
	 * The assertEquals test evaluates to true which suggests the countCurrentCards() method is functioning correctly.
	 */


	@Test

	public void testAmountOfCardsInPlayersHandsAfterDistribution() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		for (Player p : g.players) {

		assertTrue(p.countCurrentCards()==8);
		
		}
		

	}

	/**
	 * This test is to check  if the setFirstActivePlayerAndReturnTrueIfHuman() method returns the correct boolean.
	 * If the human is assigned the first active player it should return a true boolean .
	 * This test is successful which demonstrates that setFirstActivePlayerAndReturnTrueIfHuman() method is functioning correctly.
	 */


	@Test

	public void testfirstHumanActivePlayer() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		g.setFirstActivePlayerAndReturnTrueIfHuman();

		if (g.playersToShuffle.get(0).equals(g.humanPlayer)) {

		assertTrue(g.humanIsActivePlayer==true);

		}else {

			assertTrue(g.humanIsActivePlayer==false);

		}

			

	}

	/**
	 * This test is to check if the setFirstActivePlayerAndReturnTrueIfHuman() method is setting the activePlayer in the Game Logic object correctly.
	 * In the GameLogic object when setting the active player, the player object in the first instance of the playersToShuffle ArrayList should be assigned as the activePlayer.
	 * In the GameLogic object assertEquals checks if the first instance in the playersToShuffle ArrayList is equal to the activePlayer
	 * This test is successful which demonstrates that setFirstActivePlayerAndReturnTrueIfHuman() method is setting the first activePlayer correctly.
	 */

	@Test

	public void testfirstActivePlayer() {

		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayerArray();
		g.setFirstActivePlayerAndReturnTrueIfHuman();
		Assert.assertEquals(g.playersToShuffle.get(0),g.activePlayer);
	}

	

	/**
	 * This test is to check if the autoSelectCategory() method is functioning correctly
	 * When calling this method the AI Player should select a category with the highest value(for their top card ) in the Game Logic object correctly.
	 * In this scenario the player "aiPlayer2" should select "Range".
	 * 
	 * This test is successful as AssertTrue returns a true boolean  which demonstrates that when the autoSelectCategory() method 
	 * is called the player automatically selects the category associated with the highest value.
	 * 
	 */

	@Test

	public void testToConfirmAIChooseCategoryWithHighestValueOnTopCard() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		g.activePlayer=g.aiPlayer2;

		g.compareCards(g.createArrayOfCategoryValuesToBeCompared(g.autoSelectCategory()));

		Assert.assertTrue(g.activeCategory=="Range");
	}

	/**
	 * This test is to check if the createArrayOfCategoryValuesToBeCompared() method is functioning correctly.
	 * When calling this method it should take a specific category and compare the values of that category with the other players top card. 
	 * 
	 * In this scenario the Human Player selects the "Range" category.
	 * assertEquals compares the expected integer Array with the output of the createArrayOfCategoryValuesToBeCompared() method.
	 * 
	 * This test is successful as AssertEquals evaluates to true which demonstrates that when the createArrayOfCategoryValuesToBeCompared() method 
	 * is called the correct values are being stored into the comparison ArrayList.
	 * 
	 */
	

	@Test

	public void testToCheckIfTheRoundComparesTheCorrectValues() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		ArrayList<Integer> input = g.createArrayOfCategoryValuesToBeCompared("Range");

		int userG[]= {input.get(0),input.get(1),input.get(2),input.get(3),input.get(4)};
		
		int range[]= {2,4,10,7,2};
		
		System.out.println(input);
		Assert.assertArrayEquals(userG,range);


		/*int size[]= {9,5,2,4,3};

		int speed[]={9,5,2,4,3};

		int range[]= {2,4,10,7,2};

		int firepower[] = {3,3,4,3,4};

		int cargo[]={0,2,6,4,0};*/

		

	}

	
	/**
	 * This test is to check if the compareCards() method is functioning correctly.
	 * When calling this method it should compare the cardsInPlay and return the Player who has the highest value for that category.
	 * 
	 * In this scenario the Human Player selects the "Speed" category.
	 * The human player should win this round (Values that are being compare {9,5,2,4,3} ).
	 * 
	 * assertEquals compares the expected Player with the output of the compareCards() method.
	 * 
	 * This test is successful as AssertEquals evaluates to true which demonstrates that when the compareCards() method 
	 * is called the Player with the highest value,for a specific category, is returned.
	 * 
	 * 
	 */
	

	@Test

	public void testToConfirmHumanHasWonTheRound() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		Assert.assertEquals(g.humanPlayer,p);

	}
	/**
	 * This test is to check if the compareCards() method is functioning correctly.
	 * When calling this method it should return the Player who has the highest value for that category.
	 * 
	 * In this scenario the aiPlayer2 Player selects the "Range" category and should win the round.
	 * assertEquals compares the expected Player with the output of the compareCards() method.
	 * 
	 * This test is successful as AssertTrue evaluates to true which demonstrates that when the compareCards() method 
	 * is called the Player with the highest value,for a specific category, is returned.
	 * 
	 */
	
	@Test

	public void testToConfirmAIPlayerHasWonRound() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Range"));

		Assert.assertTrue(p.equals(g.aiPlayer2));

	}

	
	/**
	 * This test is to check if the compareCards() method is functioning correctly.
	 * When calling this method it should return the Player who has the highest value for that category.
	 * 
	 * In this scenario the Human Player selects the "Speed" category and wins the round.
	 * The human player is returned from the method as the winning player
	 * The communal pile should be empty when a player wins a round.
	 */
	
	@Test
	public void testToConfirmCommunalPileIsEmptyIfAPlayerHasWonTheRound() {

		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		Assert.assertTrue(g.communalPile.isEmpty());

	}
	

	/**
	 * This test is to check if the compareCards() method functions in a Draw Scenario.
	 * 
	 * When calling this method it should transfer all the players top card into the communal pile.
	 * In this scenario the Human Player selects the "Firepower" category therefore the round will Draw.
	 * In This scenario the Human Player should be assigned as the active player for the next round.
	 * assertTrue evaluates to true which indicates that if the round is a draw the active player is set as intended.
	 */

	@Test
	public void testActivePlayerInDrawScenario() {
		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();
		g.activePlayer=g.humanPlayer;
		// Select category : "FIREPOWER" and the round outcome should be a draw.
		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));
				
		Assert.assertTrue(g.activePlayer==g.humanPlayer);
		
	}
	/**
	 * This test is to check if the compareCards() method functions in a Draw Scenario.
	 * 
	 * When calling this method it should transfer all the players top card into the communal pile.
	 * In this scenario the Human Player selects the "Firepower" category therefore the round will Draw.
	 * In This scenario all the players should have 7 cards in their hand.
	 * 
	 * assertTrue evaluates to true which indicates that if the round is a draw their top card in transferred to the communal pile.
	 */


	@Test
	public void testCountCardsInDrawScenario() {
		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();
		g.activePlayer=g.humanPlayer;
		// Select category : "FIREPOWER" and the round outcome should be a draw.
		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));
				
		
		for (Player x : g.players) {

			Assert.assertTrue(x.countCurrentCards()==7);

		}

	}
	

	/**
	 * This test is to check if the transferCardsToCommunalPile() method is functioning correctly.
	 * 
	 * When calling this method it should transfer all the players top card into the communal pile.
	 * 
	 * In this scenario the Human Player selects the "Firepower" category therefore the round will Draw .
	 * 
	 * The communal pile should not be empty when a player wins a round. 
	 * assertNotNull evaluates to true which indicates the players top cards have been transferred to the communal pile.
	 * assertTrue evaluates to true as the isDraw boolean in the GameLogic class returns true .
	 */

	

	//example where the round is a draw, all the players cards are transferred to the communal pile.

	@Test

	public void testCommunalPileInDrawScenario() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayerArray();
		g.activePlayer=g.humanPlayer;
		// Select category : "FIREPOWER" and the round outcome should be a draw.
		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));
		Assert.assertNotNull(g.communalPile);
		Assert.assertTrue(g.isDraw==true);
	} 

	/**
	 * This test is to check if the transferWinnerCards() method is functioning correctly.
	 * When calling this method it should transfer all of the top cards in play to the winning player
	 * 
	 * In this scenario the Human Player selects the "Speed" category therefore the human will win the round.
	 * The remaining players should lose their top card and be left with 7 cards.
	 *
	 * assertTrue evaluates to true which suggests that when a player wins a round the losers all lose their top card .
	 */

	@Test
	public void testCountLosersCardsInWinScenario(){
		SQL sql = new SQL();

		GameLogic g = new GameLogic();

		g.shuffledDeck=sql.cardList;

		g.createPlayerArray();


		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));
		
		g.transferWinnerCards(p);
		
		for (int i =1 ; i<g.players.size();i++) {
			Assert.assertTrue(g.players.get(i).countCurrentCards()==7);
			
			}
	}
	/**
	 * This test is to check if the transferWinnerCards() method is functioning correctly.
	 * When calling this method it should transfer all of the top cards in play to the winning player
	 * 
	 * In this scenario the Human Player selects the "Speed" category therefore the human will win the round.
	 * The winning Player should have the cards in play added to their card deck.
	 *
	 * assertEquals compares the expected ArrayList to the output of the getCurrentCards for the human player
	 * assertEquals evaluates to true which suggest the cards are being transferred correctly to the winning player.
	 * 
	 * assertTrue evaluates to true which confirms the human player has 12 cards in their deck, as expected.
	 * 
	 */

	@Test

	public void testIfWinnerGetsCardsInPlayAddedTotheirDeck() {

		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayerArray();
		ArrayList<CardModel> expectedArray = new ArrayList<CardModel>();
		
		expectedArray.addAll(g.humanPlayer.getCurrentCards());
		expectedArray.remove(0);
		expectedArray.add(g.humanPlayer.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer1.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer2.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer3.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer4.getCurrentCards().get(0));
		
		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));
		g.transferWinnerCards(p);
		Assert.assertEquals(expectedArray, g.humanPlayer.getCurrentCards());
		Assert.assertTrue(g.humanPlayer.countCurrentCards()==12);

	}

	@Test

	public void testCommunalPileEmptyAfterWinnerHasBeenTransferredCards() {

		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayerArray();
			
		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));
		g.transferWinnerCards(p);
		Assert.assertTrue(g.communalPile.isEmpty());
		

	}

	/*Methods that still need testing

	 * drawRound(Player)

	 * iLoseNextActivePlayer(ArrayList<Player>)

	 * 

	 * ISelectCategoryAgain(ArrayList<Player>)

	 * isGameOver()

	 * setActivePlayer(Player)

	 * checkIfPlayersOutTheGame()

	 * checkIfGameHasBeenWon()

	 * 

	 */

	

	



	}