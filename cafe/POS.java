package kosta.cafe;

import java.util.ArrayList;
import java.util.List;

public class POS {
	private List<Order> orderList = new ArrayList<Order>();
	
	public POS() {}
	
	public synchronized void push(Order order) {
		orderList.add(order);
		this.notify();
	}
	
	public synchronized Order pop() {
		while(orderList.isEmpty()) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orderList.remove(0);
	}
}
