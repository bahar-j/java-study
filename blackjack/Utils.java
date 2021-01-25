package blackjack;

import java.util.Random;

public class Utils {
	private static final Random RANDOM = new Random();
	private static final int NUM_CARDS = 52;
	
	public static int nextInt() {
		return RANDOM.nextInt(NUM_CARDS);
	}
	
	public static int mapValueToInt(String value) {
		if(value == "A") {
			return 1;
		} else if(value == "K") {
			return 11;
		} else if(value == "Q") {
			return 12;
		} else if(value == "J") {
			return 13;
		} else {
			return Integer.parseInt(value);
		}
	}

}
