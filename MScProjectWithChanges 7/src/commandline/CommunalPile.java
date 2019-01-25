package commandline;
import java.util.ArrayList;

public class CommunalPile{
    private ArrayList<CardModel> pileCard;

    //Constructor
    public CommunalPile(){
        this.pileCard = new ArrayList<CardModel>();
    }
    
    
    public void setCommunalPileCards(ArrayList<CardModel> cardsInPile) {
    	this.pileCard = cardsInPile;
    	
    	
    }

    /*Put cards into the Communal Pile*/
    public void putInCard(CardModel newCard){
        pileCard.add(newCard);
        }

    /*Take the top card out from the Communal Pile*/
    public CardModel takeOutCard(){
    	CardModel outCard = pileCard.get(0);
    	pileCard.remove(0);
        return outCard;
    }

    /*Get the Card needed*/
    public CardModel getCard(int i){
        return this.pileCard.get(i);
    }

    /*Get the number of cards that in the communal pile*/
    public int getComPileSize(){
        return this.pileCard.size();
    }
}