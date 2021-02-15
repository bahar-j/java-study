package kosta.cafe;

import java.io.Serializable;

public class Coffee implements Serializable {

	private String coffeeName;
	private int price;
	
	public Coffee() {
		
	}

	public Coffee(String coffeeName, int price) {
		super();
		this.coffeeName = coffeeName;
		this.price = price;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
   public String toString() {
      return coffeeName + ": " + price + "원";
   }
	
}
