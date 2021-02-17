package blackjack;

public class Gamer {
	Card[] cards;
	int idx = 0;

	public Gamer(){
		this.cards = new Card[5];

		for(int i = 0; i < 5; i++){
			this.cards[i] = new Card("0", "0");
		}
		this.idx = 0;
	}
	
	public void addCardInHand(Card card) {
		cards[idx] = card;
		idx++;
	}
	
	public Card[] openAllCards() {
		return cards;
	}
}
