package commandline;

import java.util.ArrayList;

public class Player {

	public ArrayList<CardModel> cardsInHand=new ArrayList<CardModel>();// An array of cards hold by the player
	public String name;// player's name

	// Constructor
	public Player(String name) {
		this.name = name;
	}

	/* Get the player's name */
	public String getName() {
		return name;
	}

	public void addToCurrentCards(CardModel wonCard) {
		cardsInHand.add(wonCard);
	}

	public ArrayList<CardModel> getCurrentCards() {
		return this.cardsInHand;
	}

	/* Get the number of the player's current cards */
	public int countCurrentCards() {
		return this.cardsInHand.size();
	}

	/* Get the player's top card to view */
	public CardModel viewTopCard() {
		return this.cardsInHand.get(0);
	}

	public CardModel loseCard() {
		CardModel lostCard = cardsInHand.get(0);
		cardsInHand.remove(0);
		return lostCard;
	}
	
	public ArrayList<CardModel> removeAllCardsInHand() {
		this.cardsInHand.removeAll(cardsInHand);
		return cardsInHand;
		
}
}