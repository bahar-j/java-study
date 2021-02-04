package orderManagement;

import java.util.Iterator;
import java.util.List;

public class Order {
	private List<Food> foods;
	private int amount;

	public Order() {}

	public Order(List<Food> foods, int amount) {
		super();
		this.foods = foods;
		this.amount = amount;
	}

	public void printOrder() {
		Iterator<Food> iterator = foods.iterator();
		while(iterator.hasNext()) {
			Food food = iterator.next();
			food.print();
		}
	}

	public int getAmount() {
		return amount;
	}

}
