package commandline;
import java.util.ArrayList;

public class Player{
	
    public ArrayList<CardModel> cardsInHand;//An array of cards hold by the player
    public String name;//player's name
    public boolean continueGame;
    public int score;
    //Constructor
    public Player(String name){
        this.name = name;
        this.cardsInHand = new ArrayList<CardModel>();
        this.continueGame = true;
    }

    
    public void setScore(int score){
    	
    	this.score = score;

    }
    
    public int getScore(){
    	
    	return this.score;
    }
    
    /*Get the player's name*/
    public String getName(){
        return name;
    }
    
    public void setCurrentCards(ArrayList<CardModel> cardsInHand) {
    	this.cardsInHand = cardsInHand;
   
    }
    
    public void addToCurrentCards(CardModel wonCard) {
    	cardsInHand.add(wonCard);
//    	for(int i = 0; i<wonCards.size(); i++) {
//    		cardsInHand.add(wonCards.get(i));
//    	}
    	
    	
    }
    
    public ArrayList<CardModel> getCurrentCards(){
    	
    	return this.cardsInHand;
    }

    /*Get the number of the player's current cards*/
    public int countCurrentCards(){
        return this.cardsInHand.size();
    }

    /*Return if the player will continue the game*/
    public boolean ifContinueGame(){
        return continueGame;
    }

    /*Set whether the player will continue the game*/
    public void setContinueGame(boolean continueGame){
        this.continueGame = continueGame;
    }

    /*Get the player's top card to view*/
    public CardModel viewTopCard(){
        return this.cardsInHand.get(0);
    }

    /*If a player win a round, add card to the end of his current cards*/
    public void getCard(CardModel newCard){        
        cardsInHand.add(newCard);
    }

    
    public CardModel loseCard(){
    	CardModel lostCard = cardsInHand.get(0);
    	cardsInHand.remove(0);
        return lostCard;
    }
}