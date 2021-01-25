package blackjack;

public class CardDeck {
	Card[] cardsLeft;
	
	public CardDeck(Card[] cards) {
		this.cardsLeft = cards;
	}
	
	public Card giveCard() {
		String value = "0";
		int idx = 0;
		while(value.equals("0")) {
			idx = Utils.nextInt();
			value = cardsLeft[idx].value;
		}
		Card returnValue = new Card(cardsLeft[idx].pattern, cardsLeft[idx].value);
		cardsLeft[idx].value = "0"; //비어있는 카드는 0 
		return returnValue;
	}
}
