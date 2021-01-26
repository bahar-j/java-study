package blackjack;

import java.util.Random;

public class Utils {
	private static final Random RANDOM = new Random();
	private static final int NUM_CARDS = 52;
	
	public static int nextInt() {
		return RANDOM.nextInt(NUM_CARDS);
	}
	
	public static int mapValueToInt(String value) {
		if(value.equals("A")) {
			return 1;
		} else if(value.equls("K") || value.equals("Q") || value.equals("J")) {
			return 10;
		} else {
			return Integer.parseInt(value);
		}
	}

}
