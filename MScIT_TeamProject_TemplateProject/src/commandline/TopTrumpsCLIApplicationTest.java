package commandline;



import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;





class TopTrumpsCLIApplicationTest {



	protected SQL sql = new SQL();



	protected Player p;



	protected CardModel c;



	protected GameLogic g;

	
	
	
	@BeforeEach
	public void setUp() {
		sql = new SQL();
		g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayerArray();
			
	}

	/**

	 * This test distributes the deck of cards into five CardModel ArrayLists .

	 * Each Array List represents the players cards in hand.

	 * It then calls the getCurrentCards() and tests whether the output is the same as the CardModel ArrayList input for each player.

	 * The assertEquals test evaluates to true which shows that when the cards are distributed they are stored in the player object as an ArrayList.

	 */



	
	@Test
	public void testToEnsurePlayersHaveRecievedTheirCards() {

		g.players.clear();
		g.humanPlayer.getCurrentCards().clear();
		g.aiPlayer1.getCurrentCards().clear();
		g.aiPlayer2.getCurrentCards().clear();
		g.aiPlayer3.getCurrentCards().clear();
		g.aiPlayer4.getCurrentCards().clear();
		
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

		assertEquals(human,g.humanPlayer.getCurrentCards());
		assertEquals(ai1,g.aiPlayer1.getCurrentCards());
		assertEquals(ai2,g.aiPlayer2.getCurrentCards());
		assertEquals(ai3,g.aiPlayer3.getCurrentCards());
		assertEquals(ai4,g.aiPlayer4.getCurrentCards());
	};



	/**

	 * The following test is to check the countCurrentCards() method in the player class returns the correct number of cards.

	 * In the first round each player should have 8 card objects in their cardsInHand ArrayList.

	 * It then calls the countCurrentCards() method and tests whether the output is equal to 8.

	 * The assertEquals test evaluates to true which suggests the countCurrentCards() method is functioning correctly.

	 */





	@Test



	public void testAmountOfCardsInPlayersHandsAfterDistribution() {

		

		for (Player p : g.players) {

		assertTrue(p.countCurrentCards()==8);

		

		}

		



	}



	/**

	 * This test is to check  if the setFirstActivePlayerAndReturnTrueIfHuman() method returns the correct boolean.

	 * If the Player Object "humanPlayer" is assigned as the active player it should return humanIsActivePlayer as true, if not it should return false.

	 * This test is successful which demonstrates that setFirstActivePlayerAndReturnTrueIfHuman() method is functioning correctly.

	 */





	@Test



	public void testfirstHumanActivePlayer() {

		g.setFirstActivePlayerAndReturnTrueIfHuman();



		if (g.playersToShuffle.get(0).equals(g.humanPlayer)) {



		assertTrue(g.humanIsActivePlayer==true);



		}else {



			assertTrue(g.humanIsActivePlayer==false);



		}



			

		/**

		 * This test is to check  if the manuallySelectCategory() method returns a String ("activeCategory") correctly.

		 * This test calls the manuallySelectCategory() method which allows the user to select a category.

		 * This output is stored as a String,"testString".

		 * 

		 * It tests whether the output is the same as the the activeCategory  string in the GameLogic class.

		 * The assertEquals test evaluates to true which demonstrates the method is working Correctly.

		 */

	}

	

	@Test

	public void testHumanInputForSelectingACategory() {

	
		String testString = g.manuallySelectCategory();

		assertEquals(testString,g.activeCategory);
				

	}



	/**

	 * This test is to check if the setFirstActivePlayerAndReturnTrueIfHuman() method is setting the activePlayer in the Game Logic object correctly.

	 * In the GameLogic object when setting the active player, the player object in the first instance of the playersToShuffle ArrayList should be assigned as the activePlayer.

	 * In the GameLogic object assertEquals checks if the first instance in the playersToShuffle ArrayList is equal to the activePlayer

	 * It then gets the first instance in the playersToShuffle ArrayList and tests whether the output is equal to the activePlayer.

	 * This test is successful which demonstrates that setFirstActivePlayerAndReturnTrueIfHuman() method is setting the first activePlayer correctly.

	 */



	@Test



	public void testfirstActivePlayer() {


		g.setFirstActivePlayerAndReturnTrueIfHuman();

		Assert.assertEquals(g.playersToShuffle.get(0),g.activePlayer);

	}



	



	/**

	 * This test is to check if the autoSelectCategory() method is functioning correctly

	 * When calling this method the Player Object "aiPlayer2" should select a category with the highest value(for their top card ) in the Game Logic object correctly.

	 * In this scenario the player "aiPlayer2" should select "Range".

	 * 

	 * This test is successful as AssertTrue returns a true boolean  which demonstrates that when the autoSelectCategory() method 

	 * is called the player automatically selects the category associated with the highest value.

	 * 

	 */



	@Test



	public void testToConfirmAIChoosesCategoryWithHighestValueOnTopCard() {


		g.activePlayer=g.aiPlayer2;

		g.compareCards(g.createArrayOfCategoryValuesToBeCompared(g.autoSelectCategory()));

		Assert.assertTrue(g.activeCategory=="Range");

	}



	/**

	 * This test is to check if the createArrayOfCategoryValuesToBeCompared() method is functioning correctly.

	 * When calling this method it should take a specific category and compare the values of that category with the other players top card. 

	 * 

	 * In this scenario the Player Object "humanPlayer" selects the "Range" category.

	 * It then calls the createArrayOfCategoryValuesToBeCompared()  and tests whether the output is equal to the expected integer Array "user".

	 * 

	 * 

	 * This test is successful as AssertEquals evaluates to true which demonstrates that when the createArrayOfCategoryValuesToBeCompared() method 

	 * is called the correct values are being stored into the comparison ArrayList.

	 * 

	 */

	



	@Test



	public void testToCheckIfTheRoundComparesTheCorrectValues() {


		ArrayList<Integer> input = g.createArrayOfCategoryValuesToBeCompared("Range");



		int user[]= {input.get(0),input.get(1),input.get(2),input.get(3),input.get(4)};

		

		int range[]= {2,4,10,7,2};

		

		System.out.println(input);

		Assert.assertArrayEquals(user,range);

	



	}



	

	/**

	 * This test is to check if the compareCards() method is functioning correctly.

	 * When calling this method it should compare the cardsInPlay and return the Player who has the highest value for that category.

	 * 

	 * In this scenario the Player object "humanPlayer" selects the "Speed" category.

	 * The human player should win this round (Values that are being compare {9,5,2,4,3}).

	 * Player "p" is that output of the compareCards() method.

	 * 

	 * It then tests whether the output("p") is equal to the expected Player Object ".

	 * 

	 * This test is successful as AssertEquals evaluates to true which demonstrates that when the compareCards() method. 

	 * is called the Player with the highest value,for a specific category, is returned.

	 * 

	 * 

	 */

	



	@Test



	public void testToConfirmHumanHasWonTheRound() {

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));



		Assert.assertEquals(g.humanPlayer,p);



	}

	/**

	 * This test is to check if the compareCards() method is functioning correctly.

	 * When calling this method it should return the Player who has the highest value for that category.

	 * 

	 * In this scenario the aiPlayer2 Player selects the "Range" category and should win the round.

	 * Player "p" is that output of the compareCards() method.

	 * It then tests whether the output("p") is equal to the expected Player Object ".

	 * 

	 * This test is successful as AssertTrue evaluates to true which demonstrates that when the compareCards() method 

	 * is called the Player with the highest value,for a specific category, is returned.

	 * 

	 */

	

	@Test



	public void testToConfirmAIPlayerHasWonRound() {

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Range"));



		Assert.assertTrue(p.equals(g.aiPlayer2));



	}



	

	/**

	 * This test is to check if the compareCards() method is functioning correctly.

	 * When calling this method it should return the Player who has the highest value for that category.

	 * 

	 * In this scenario the Player Object  "humanPlayer" selects the "Speed" category and wins the round.

	 * The human player is returned from the method as the winning player.

	 * 

	 * The communal pile should be empty when a player wins a round.

	 * assertTrue returns a true boolean which shows the communal pile is empty when a player wins a round.

	 */

	

	@Test

	public void testToConfirmCommunalPileIsEmptyIfAPlayerHasWonTheRound() {

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));



		Assert.assertTrue(g.communalPile.isEmpty());



	}

	



	/**

	 * This test is to check if the compareCards() method functions in a Draw Scenario.

	 * 

	 * When calling this method it should transfer all the players top card into the communal pile.

	 * In this scenario the Player Object "humanPlayer" selects the "Firepower" category therefore the round will Draw.

	 * In This scenario the Human Player should be assigned as the active player for the next round.

	 * assertTrue evaluates to true which indicates that if the round is a draw the next round active player is set as intended.

	 */



	@Test

	public void testActivePlayerInDrawScenario() {


		g.activePlayer=g.humanPlayer;

		// Select category : "FIREPOWER" and the round outcome should be a draw.

		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));

				

		Assert.assertTrue(g.activePlayer==g.humanPlayer);

		

	}

	/**

	 * This test is to check if the compareCards() method functions in a Draw Scenario.

	 * 

	 * When calling this method it should transfer all the players top card into the communal pile.

	 * In this scenario the Player Object "humanPlayer" selects the "Firepower" category therefore the round will Draw.

	 * In This scenario all the players should have 7 cards in their hand.

	 * 

	 * assertTrue evaluates to true which indicates that if the round is a draw their top card in transferred to the communal pile.

	 */





	@Test

	public void testCountCardsInDrawScenario() {


		g.activePlayer=g.humanPlayer;

		// Select category : "FIREPOWER" and the round outcome should be a draw.

		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));

				

		

		for (Player x : g.players) {



			Assert.assertTrue(x.countCurrentCards()==7);



		}



	}

	

	/**

	 * This test is to check if the compareCards() method functions in a Draw Scenario.

	 * 

	 * When calling this method it should transfer all the players top card into the communal pile.

	 * In this scenario the Player Object "humanPlayer" selects the "Firepower" category therefore the round will Draw.

	 * In This scenario all the players should have 7 cards in their hand.

	 * 

	 * assertTrue evaluates to true which indicates that if the round is a draw their top card in transferred to the communal pile.

	 */





	@Test

	public void testCommunalPileCardsInDrawScenario() {

		ArrayList<CardModel> expectedArray = new ArrayList<CardModel>();

		g.activePlayer=g.humanPlayer;

		expectedArray.add(g.humanPlayer.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer1.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer2.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer3.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer4.getCurrentCards().get(0));

		g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Firepower"));

		

		assertEquals(expectedArray,g.communalPile);



	}

	



	/**

	 * This test is to check if the transferCardsToCommunalPile() method is functioning correctly.

	 * 

	 * When calling this method it should transfer all the players top card into the communal pile.

	 * 

	 * In this scenario the Player Object "humanPlayer" selects the "Firepower" category therefore the round will Draw .

	 * 

	 * The communal pile should not be empty when a player wins a round. 

	 * assertNotNull evaluates to true which indicates the players top cards have been transferred to the communal pile.

	 * assertTrue evaluates to true as the isDraw boolean in the GameLogic class returns true .

	 */



	



	//example where the round is a draw, all the players cards are transferred to the communal pile.



	@Test



	public void testCommunalPileInDrawScenario() {

		
		
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

	 * In this scenario the Player Object "humanPlayer" selects the "Speed" category therefore the human will win the round.

	 * The remaining players should lose their top card and be left with 7 cards.

	 *

	 * assertTrue evaluates to true which suggests that when a player wins a round the losers all lose their top card .

	 */



	@Test

	public void testCountLosersCardsInWinScenario(){

	
		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		

		g.transferCardsToRoundWinner(p);

		

		for (int i =1 ; i<g.players.size();i++) {

			Assert.assertTrue(g.players.get(i).countCurrentCards()==7);

			

			}

	}

	/**

	 * This test is to check if the transferWinnerCards() method is functioning correctly.

	 * When calling this method it should transfer all of the top cards in play to the winning player

	 * 

	 * In this scenario the Player Object "humanPlayer" selects the "Speed" category therefore the human will win the round.

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



		
		

		ArrayList<CardModel> expectedArray = new ArrayList<CardModel>();

		

		expectedArray.addAll(g.humanPlayer.getCurrentCards());

		expectedArray.remove(0);

		expectedArray.add(g.humanPlayer.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer1.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer2.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer3.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer4.getCurrentCards().get(0));

		

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		g.transferCardsToRoundWinner(p);

		Assert.assertEquals(expectedArray, g.humanPlayer.getCurrentCards());

		Assert.assertTrue(g.humanPlayer.countCurrentCards()==12);



	}

	/**

	 * This test is to check if the transferWinnerCards() method is functioning correctly.

	 * When calling this method it should transfer all of the top cards in play to the winning player

	 * 

	 * In this scenario the Player Object humanPlayer selects the "Speed" category therefore the human will win the round.

	 * If there are cards in the communal pile they should be transferred to the winning Player

	 * 

	 * In this test assertTrue checks if the communal pile is empty.

	 * This test is successful as assertTrue evaluates to true which demonstrates when transferWinnerCards() method is called cards in the communal pile are 

	 * 

	 */



	@Test



	public void testCommunalPileEmptyAfterWinnerHasBeenTransferredCards() {

				

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		g.transferCardsToRoundWinner(p);

		Assert.assertTrue(g.communalPile.isEmpty());

		



	}

	

	/**

	 * This test is to check if the transferWinnerCards() method is functioning correctly.

	 * When calling this method it should return the Player who has the highest value for that category.

	 * 

	 * In this scenario the previous round was a draw therefore the communal pile contains 5 cards.

	 * Player Object  "humanPlayer" selects the "Speed" category and wins the round.

	 * The human player is returned from the method as the winning player.

	 * The human player should receive all the cards in play for the round AND the cards in the communal pile.

	 * 

	 * In this test assertEquals checks to see if the human players getCurrentCards() output is equal to the expected ArrayList

	 * This test is successful as assertEquals evaluates to true which demonstrates that the cards

	 */

	

	@Test

	public void testPreviousRoundDrawHumanWinsTransferBothCardsInPlayAndCardsInCommunalPile() {

		ArrayList<CardModel> expectedArray = new ArrayList<CardModel>();
		expectedArray.addAll(g.humanPlayer.getCurrentCards());


		g.communalPile.add(g.humanPlayer.cardsInHand.get(1));

		g.communalPile.add(g.aiPlayer1.cardsInHand.get(1));

		g.communalPile.add(g.aiPlayer2.cardsInHand.get(1));

		g.communalPile.add(g.aiPlayer3.cardsInHand.get(1));

						

		g.humanPlayer.cardsInHand.remove(1);

		g.aiPlayer1.cardsInHand.remove(1);;

		g.aiPlayer2.cardsInHand.remove(1);

		g.aiPlayer3.cardsInHand.remove(1);

		g.aiPlayer4.cardsInHand.remove(1);

		

		expectedArray.add(g.humanPlayer.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer1.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer2.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer3.getCurrentCards().get(0));

		expectedArray.add(g.aiPlayer4.getCurrentCards().get(0));

		expectedArray.addAll(g.communalPile);



		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		g.transferCardsToRoundWinner(p);

		expectedArray.remove(0);

		expectedArray.remove(0);

		assertEquals(expectedArray,g.humanPlayer.getCurrentCards());

	}

	

	/**

	 * This test is to check if the checkIfPlayersOutTheGame() method is functioning correctly.

	 * When calling this method it should check if the Player object "humanPlayer" has any cards stored in their deck .

	 * 

	 * In this scenario the Human Player has no cards left in their deck so the checkIfPlayersOutTheGame() should remove the player from the players ArrayList and set humanPlayerOutGame as true. 

	 * In this test assertFalse checks if the Players ArrayList in the GameLogic class contains the Player object "humanPlayer".

	 * Additionally assertTrue checks if the output of the method returns a true boolean.

	 * This test is successful which demonstrates that the method is working as intended.

	 * 

	 */

	

	@Test 

	public void testIfHumanPlayerIsOutOfGame() {


		g.setFirstActivePlayerAndReturnTrueIfHuman();

		

		

		g.humanPlayer.removeAllCardsInHand();

		g.checkIfPlayersOutTheGame();

		assertFalse(g.players.contains(g.humanPlayer));

		assertTrue(g.humanPlayerOutGame==true);

		

		

	}

	

	/**

	 * This test is to check if the checkIfPlayersOutTheGame() method is functioning correctly.

	 * In this scenario only the Player Object "humanPlayer" is left in the game.

	 * When calling this method it should return the gameOver boolean, in the GameLogic class, as true.

	 * The test shows this is the case as assertTrue evaluates to true which confirms the method is functioning correctly.

	 * 

	 */

	

	@Test

	public void testIsGameOverWhenOnePlayerIsInTheGame() {

		

		g.setFirstActivePlayerAndReturnTrueIfHuman();

		

		

		g.aiPlayer1.removeAllCardsInHand();

		g.aiPlayer2.removeAllCardsInHand();

		g.aiPlayer3.removeAllCardsInHand();

		g.aiPlayer4.removeAllCardsInHand();

		g.checkIfPlayersOutTheGame();

		

		assertTrue(g.gameOver==true);

		

		

	}

	

		

	/**

	 * This test is to check if the checkIfGameHasBeenWon() method is functioning correctly.

	 * In this scenario three Player Objects are still in the game.

	 * When calling this method it should return the gameOver boolean, in the GameLogic class, as false.

	 * The test shows this is the case as assertTrue evaluates to true which confirms the method is functioning correctly.

	 */

	@Test

	public void testIsGameOverWhenMoreThenOnePlayerIsInTheGame() {

		
		g.setFirstActivePlayerAndReturnTrueIfHuman();

				

		g.aiPlayer1.removeAllCardsInHand();

		g.aiPlayer2.removeAllCardsInHand();

				

		g.checkIfPlayersOutTheGame();

		assertTrue(g.gameOver==false);

		

		

	}

	

	

	

	/**

	 * This test is to check if the setActivePlayerAndReturnTrueIfHuman() method is functioning correctly.

	 * In this scenario the Player Object "humanPlayer" selects the "Speed" category and loses the round.

	 * Player object "aiPlayer2" is the winner of this round and is stored as Player object "p"

	 * 

	 * p is passed to the setActivePlayerAndReturnTrueIfHuman() method and should set the next player in the players ArrayList as the active player.

	 * The test shows this is the case as assertTrue evaluates to true which confirms the method is functioning correctly.

	 */

	

	@Test

	public void testNextActivePlayerWhenActivePlayerLosesARound() {

		

		g.activePlayer=g.humanPlayer;

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Size"));

		g.setActivePlayerAndReturnTrueIfHuman(p);

		assertEquals(g.aiPlayer1,g.activePlayer);		

	}

	

	/**

	 * This test is to check if the setActivePlayerAndReturnTrueIfHuman() method is functioning correctly.

	 * In this scenario the Player Object "humanPlayer" wins the round which is stored as Player object "p".

	 * p is passed to the setActivePlayerAndReturnTrueIfHuman() method and should set the humanIsActivePlayer to true.

	 * 

	 * The test shows this is the case as assertTrue evaluates to true which confirms the method is functioning correctly.

	 */

	

	@Test

	public void testIsHumanActivePlayerWhenWinningARound() {


		g.activePlayer=g.humanPlayer;

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Speed"));

		g.setActivePlayerAndReturnTrueIfHuman(p);

		assertTrue(g.humanIsActivePlayer==true);

		

	}

	

	/**

	 * This test is to check if the setActivePlayerAndReturnTrueIfHuman() method is functioning correctly.

	 * In this scenario the Player Object "humanPlayer" selects the "Speed" category and loses the round.

	 * Player object "aiPlayer2" is the winner of this round and is stored as Player object "p"

	 * 

	 * p is passed to the setActivePlayerAndReturnTrueIfHuman() method and should set the next player in the players ArrayList as the active player therefore

	 * the humanIsActivePlayer should be set to false.

	 * The test shows this is the case as assertTrue evaluates to true which confirms the method is functioning correctly.

	 */

	

	

	@Test

	public void testIsHumanActivePlayerWhenHumanLosesARound() {


		g.activePlayer=g.humanPlayer;

		Player p = g.compareCards(g.createArrayOfCategoryValuesToBeCompared("Size"));

		g.setActivePlayerAndReturnTrueIfHuman(p);

		assertTrue(g.humanIsActivePlayer==false);		

	}

	



	}
