package blackjack;

public class Gamer {
	Card[] cards = {new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0"), new Card("0", "0")};
	int idx = 0;
	
	public void addCardInHand(Card card) {
		cards[idx] = card;
		idx++;
	}
	
	public Card[] openAllCards() {
		return cards;
	}
}
