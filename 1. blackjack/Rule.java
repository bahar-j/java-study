package blackjack;

public class Rule {

	public CardDeck startGame() {
		return setCards();
	}

	public CardDeck setCards() {
		String[] patterns = {"diamond", "heart", "spade", "club"};
		String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J"};
		Card[] tmp = new Card[52];
		int idx = 0;

		for(String pattern: patterns) {
			for(String value: values) {
				tmp[idx] = new Card(pattern, value);
				idx++;
			}
		}
		CardDeck deck = new CardDeck(tmp);
		return deck;
	}

	public boolean needAnotherCard(int sum) {
		if (sum <= 16) return true;
		else return false;
	}

	public int calculateSum(Card[] cards) {
		int sum = 0;
		for(Card card: cards) {
			sum += Utils.mapValueToInt(card.value);
		}
		return sum;
	}

	public void announceWinner(Dealer dealer, Gamer gamer) {
		int dealerScore = calculateSum(dealer.openAllCards());
		int gamerScore = calculateSum(gamer.openAllCards());

		if (diffWith21(dealerScore) < 0 && diffWith21(gamerScore) < 0) {
			System.out.println("승자가 없습니다🙀");
		} else if (diffWith21(dealerScore) < 0) {
			System.out.println("승리하셨습니다🙊");
		} else if (diffWith21(gamerScore) < 0) {
			System.out.println("패배하셨습니다🙀");
		} else if (diffWith21(dealerScore) < diffWith21(gamerScore)) {
			System.out.println("패배하셨습니다🙀");
		} else if (diffWith21(dealerScore) == diffWith21(gamerScore)) {
			System.out.println("무승부입니다.");
		} else {
			System.out.println("승리하셨습니다🙊");
		}
	}

	public int diffWith21 (int score) {
		return 21 - score;
	}


}
