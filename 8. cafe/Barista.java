package cafe;

import java.util.Iterator;

public class Barista extends Thread {
	private POS pos;
	
	public Barista(POS pos) {
		this.pos = pos;
	}
	
	@Override
	public void run() {
		while(true){
			Order completedOrder = pos.pop();
			try {
				sleep(5000); // make coffee
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("---------------Pick up----------------");
			System.out.println(completedOrder.getCustomer().getNickName() + "님 주문하신 " + completedOrder.getCoffee().getCoffeeName() + " "
					+ completedOrder.getCount() + "잔 나왔습니다.");
			System.out.println("--------------------------------------");
		}
	}
}
