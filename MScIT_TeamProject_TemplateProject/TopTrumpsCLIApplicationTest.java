package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopTrumpsCLIApplicationTest {
	protected SQL sql = new SQL();
	protected Player p;
	protected CardModel c;
	protected GameLogic g;
	
	//Test to ensure the cards have been stored in the player objects.
	@Test
	public void testToEnsurePlayersHaveRecievedTheirCards() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffleDeck();
		g.createPlayers();
		
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
		
			
		 /*
			int [] cardiDz = {g.humanPlayer.getCurrentCards().get(0).getCardID(),
			g.humanPlayer.getCurrentCards().get(1).getCardID(),
			g.humanPlayer.getCurrentCards().get(2).getCardID(),
			g.humanPlayer.getCurrentCards().get(3).getCardID(),
			g.humanPlayer.getCurrentCards().get(4).getCardID(),
			g.humanPlayer.getCurrentCards().get(5).getCardID(),
			g.humanPlayer.getCurrentCards().get(6).getCardID(),
			g.humanPlayer.getCurrentCards().get(7).getCardID(), 	
		  	int[] hExpected = {1,6,11,16,21,26,31,36};
			int[] ai1Expected = {1,6,11,16,21,26,31,36};
			int[] ai2xpected = {1,6,11,16,21,26,31,36};
			int[] ai3Expected = {1,6,11,16,21,26,31,36};
			int[] ai4Expected = {1,6,11,16,21,26,31,36};	
			Assert.assertArrayEquals(hexpected, cardiDz);*/
		
	};
	
	

	
	
	//Test to count human cards after they have been distributed.
	@Test
	public void testAmountOfHumanCards() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		
		assertTrue(g.humanPlayer.countCurrentCards()==8);
		
	}
	
	// Tests if human is the first active player
	@Test
	public void testfirstHumanActivePlayer() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		g.chooseFirstActivePlayer();
		if (g.playersToShuffle.get(0).getName().equals("Human")) {
		assertTrue(g.humanIsActivePlayer==true);
		}else {
			assertTrue(g.humanIsActivePlayer==false);
		}
			
	}
	
	// Check if correct player is being stored as active player .
	@Test
	public void testfirstActivePlayer() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		g.chooseFirstActivePlayer();
		
		Assert.assertEquals(g.playersToShuffle.get(0).getName(),g.activePlayer.getName());
		
			
	}
	
	
	// Test checks to ensure when the user selects a category it is returned as is should be.
	@Test
	public void testHumanInputForSelectingACategory() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		g.selectCategory();
		
		if (g.activeCategory=="Speed") {
			assertTrue(g.activeCategory=="Speed");

		} else if (g.activeCategory=="Size") {
			assertTrue(g.activeCategory=="Size");

		} else if (g.activeCategory=="Range") {
			assertTrue(g.activeCategory=="Range");

		} else if (g.activeCategory=="Firepower") {
			assertTrue(g.activeCategory=="Firepower");

		} else if (g.activeCategory=="Cargo") {
			assertTrue(g.activeCategory=="Cargo");
		} 
				
	}
	
	// In This Test AI Player 2 is the active player and has to automatically select a category.
	// AI Player 2 should choose the category "Range" as it is contains the highest value on 
	// the players top card.
	@Test
	public void testToConfirmAIChooseCategoryWithHighestValueOnTopCard() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		g.activePlayer=g.aiPlayer2;
		g.compareCards(g.getSelectedCategory(g.autoCategory()));
		Assert.assertTrue(g.activeCategory=="Range");
				
	}
	
	
	
	//	//This is an example round where the Human selects either the "Range" or "Size"  category 
	 	// and checks if the the comparison array is correct.
	@Test
	public void testToCheckIfTheRoundComparesTheCorrectValues() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		ArrayList<Integer> input = g.getSelectedCategory("Size");
		
		int userG[]= {input.get(0),input.get(1),input.get(2),input.get(3),input.get(4)};
		
		//values that should be compared in the first round.
		
		int size[]= {9,5,2,4,3};
		int speed[]={9,5,2,4,3};
		int range[]= {2,4,10,7,2};
		int firepower[] = {3,3,4,3,4};
		int cargo[]={0,2,6,4,0};
				
		//
		if (g.activeCategory=="Range") {
			Assert.assertArrayEquals(userG, range);

		} else if (g.activeCategory=="Size") {
			Assert.assertArrayEquals(userG, size); }
		
	}
	
	
	//This is an example round where the Human selects the "Size" category where Human is expected to win as 
	// it holds the card with the highest value.
	@Test
	public void testToConfirmHumanHasWonTheRound() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		Player p = g.compareCards(g.getSelectedCategory("Speed"));
		Assert.assertTrue(p.getName().equals("Human"));
		Assert.assertEquals(g.humanPlayer,p);
		Assert.assertTrue(g.communalPile.isEmpty());
	}
	
	//This is an example where the Human selects the "Range" category . 
	// AI2 is expected to win as it holds the card with the highest value.
	
	@Test
	public void testToConfirmAIPlayerHasWonRound() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		Player p = g.compareCards(g.getSelectedCategory("Range"));
		Assert.assertTrue(p.getName().equals("AI 2"));
		Assert.assertTrue(g.communalPile.isEmpty());
	
	}
	
	
	//example where the round is a draw, all the players cards are transferred to the communal pile.
	@Test
	public void testDrawScenario() {
		
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		// Select category : "FIREPOWER" and the round outcome should be a draw.
		g.compareCards(g.getSelectedCategory(g.selectCategory()));
		
		Assert.assertNotNull(g.communalPile);
		Assert.assertTrue(g.activePlayer==g.humanPlayer);
		Assert.assertTrue(g.isDraw==true);
		// All the players should have one less card in their decks as their top card
		// is transferred to the communal pile.
		for (Player x : g.players) {
			Assert.assertTrue(x.countCurrentCards()==7);
		}
			
	} 
	
	
	// When a player wins, all the cards in the comparison array should be added to the 
	// winning players current cards. Additionally the top card of the players who lost 
	// should be removed from their currentCards.
	@Test
	public void testWinScenario() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		
		ArrayList<CardModel> expectedArray = new ArrayList<CardModel>();
		expectedArray.addAll(g.humanPlayer.getCurrentCards());
		expectedArray.remove(0);
		expectedArray.add(g.humanPlayer.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer1.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer2.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer3.getCurrentCards().get(0));
		expectedArray.add(g.aiPlayer4.getCurrentCards().get(0));
		Player p = g.compareCards(g.getSelectedCategory("Speed"));
		g.transferWinnerCards(p);
		
		Assert.assertEquals(expectedArray, g.humanPlayer.getCurrentCards());
		Assert.assertTrue(g.humanPlayer.countCurrentCards()==12);

		// losers should have 7 cards remaining.
		for (int i =1 ; i<g.players.size();i++) {
			Assert.assertTrue(g.players.get(i).countCurrentCards()==7);
		}
		
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
  
	
		
		
		
	
	


