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
	
	//Test to ensure players have recieved their cards.
/*	@Test
	public void testHumanCardsAfterDistribution() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		
		
		int [] cardiDz = {g.humanPlayer.getCurrentCards().get(0).getCardID(),
			g.humanPlayer.getCurrentCards().get(1).getCardID(),
			g.humanPlayer.getCurrentCards().get(2).getCardID(),
			g.humanPlayer.getCurrentCards().get(3).getCardID(),
			g.humanPlayer.getCurrentCards().get(4).getCardID(),
			g.humanPlayer.getCurrentCards().get(5).getCardID(),
			g.humanPlayer.getCurrentCards().get(6).getCardID(),
			g.humanPlayer.getCurrentCards().get(7).getCardID(),
		};
		int[] expected = {1,6,11,16,21,26,31,36};	
		Assert.assertArrayEquals(expected, cardiDz);
	}
	
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
	public void testfirstActivePlayer() {
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
	
	//	//This is an example round where the Human selects either the "Range" or "Size"  category 
	 	// and checks if the the comparison array is correct.
	@Test
	public void testToCheckIfTheRoundComparesTheCorrectValues() {
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		ArrayList<Integer> input = g.getSelectedCategory(g.selectCategory());
		
		int userG[]= {input.get(0),input.get(1),input.get(2),input.get(3),input.get(4)};
		
		//values that should be compared in the first round.
		int range[]= {2,4,10,7,2};
		int size[]= {9,5,2,4,3};
				
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
	}*/
	
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
	
	}
	
	//example where the round is a draw.
	@Test
	public void testToCheckIfCardsHaveBeenTransferredToCommunalPileWhenRoundIsDraw() {
		
		SQL sql = new SQL();
		GameLogic g = new GameLogic();
		g.shuffledDeck=sql.cardList;
		g.createPlayers();
		Player p = g.compareCards(g.getSelectedCategory(g.selectCategory()));
		Assert.assertNotNull(g.communalPile);
		
	} 
	
	
	
	
	
	
  
  

		
	}
  
	
		
		
		
	
	


