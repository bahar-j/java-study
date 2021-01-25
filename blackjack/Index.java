package blackjack;

import java.util.Scanner;

public class Index {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		
		System.out.println("게임을 시작합니다.");
		Dealer dealer = new Dealer();
		Gamer gamer = new Gamer();
		Rule rule = new Rule();
		CardDeck deck = rule.startGame();
//		System.out.println(deck.cardsLeft[0].value);
//		System.out.println(deck.cardsLeft[0].pattern);
		
		System.out.println("딜러와 게이머가 각각 카드를 2장씩 받습니다.");
		for(int i = 0; i < 2; i++) {
			dealer.addCardInHand(deck.giveCard());
		}
		for(int i = 0; i < 2; i++) {
			gamer.addCardInHand(deck.giveCard());
		}
		
		System.out.println("딜러가 추가 카드를 받을지 판단합니다.");
//		System.out.println(dealer.cards[0].value);
		boolean isNeeded = true;
		while (isNeeded) {
			int dealerSum = rule.calculateSum(dealer.openAllCards());
			isNeeded = rule.needAnotherCard(dealerSum);
			dealer.addCardInHand(deck.giveCard());
		} 
		
		System.out.println("게이머가 추가 카드를 받을지 판단합니다.");
		while(true) {
			System.out.print("새로운 카드를 뽑으시겠습니까?(y/n)");
			String userAnswer = scanner.nextLine();
			if (userAnswer.equals("y")) {
				gamer.addCardInHand(deck.giveCard());
			} else break;
		}
		
		rule.announceWinner(dealer, gamer);
		
	}

}
