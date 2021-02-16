package kosta.cafe;

public class Order {

	private Customer customer;
	private Coffee coffee;
	private int count;

	public Order() {

	}

	public Order(Customer customer, Coffee coffee, int count) {
		super();
		this.customer = customer;
		this.coffee = coffee;
		this.count = count;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public int getCount() {
		return count;
	}

}