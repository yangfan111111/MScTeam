package commandline;


public class CardModel {
	
	private int CardID = 0;
	private String Description;
	private int Size = 0;
	private int Speed = 0;
	private int Range = 0;
	private int Firepower = 0;
	private int Cargo = 0;
	private String CardPicture;
	
	// cardID 
	public void setCardID(int cardID) {
		this.CardID = cardID;
	}
	public int getCardID() {
		return this.CardID;
	}
	// Description
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getDescription() {
		return this.Description;
	}
	// size
	public void setSize(int Size) {
		this.Size = Size;
	}
	public int getSize() {
		return this.Size;
	}
	//Speed
	public void setSpeed(int Speed) {
		this.Speed = Speed;
	}
	public int getSpeed() {
		return this.Speed;
	}
	// range
	public void setRange(int Range) {
		this.Range = Range;
	}
	public int getRange() {
		return this.Range;
	}
	// firepower
	public void setFirepower(int Firepower) {
		this.Firepower = Firepower;
	}
	public int getFirepower() {
		return this.Firepower;
	}
	// cargo
	public void setCargo(int cargo) {
		this.Cargo = cargo;
	}
	public int getCargo(){
		return this.Cargo;
	}
	//cardPicture
	public void setCardPicture(String cardPicture) {
		this.CardPicture = cardPicture;
	}
	public String getCardPicture() {
		return this.CardPicture;
	}
	
	public CardModel(int cardID, String description, int size, int speed, int range, int firepower, int cargo, String cardPicture) {
		this.CardID = cardID;
		this.Description = description;
		this.Size = size;
		this.Speed = speed;
		this.Range = range;
		this.Firepower = firepower;
		this.Cargo = cargo;
		this.CardPicture = cardPicture;
	}
	
	//show all data
	public String showData() {
		
		String infoString = "cardID: "+this.CardID+", " +"card name: "+this.Description+", "+
				"Size: "+this.Size+", "+"Speed: "+this.Speed+", "+"Range: "+this.Range+", "
				+"Firepower: "+this.Firepower+", "+"Cargo: "+this.Cargo+"\n";
		
		return infoString;
	}
	
	public String toString() {
		String infoString = "cardID: "+this.CardID+", " +"card name: "+this.Description+", "+
				"Size: "+this.Size+", "+"Speed: "+this.Speed+", "+"Range: "+this.Range+", "
				+"Firepower: "+this.Firepower+", "+"Cargo: "+this.Cargo+"\n";
		
		return infoString;
	}
	
}
