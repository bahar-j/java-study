package blackjack;

public class CardDeck {
	Card[] cards;
	
	public CardDeck(Card[] cards) {
		this.cards = cards;
	}
	
	public Card giveCard() {
		String value = "0";
		int idx = 0;
		while(value.equals("0")) {
			idx = Utils.nextInt();
			value = cards[idx].value;
		}
		Card pickedCard = new Card(cards[idx].pattern, cards[idx].value);
		cards[idx].value = "0"; //비어있는 카드는 value 0으로 바꿔줌
		return pickedCard;
	}
}
