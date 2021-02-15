package kosta.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager extends Thread {
	Scanner scanner = new Scanner(System.in);

	List<Coffee> menu = new ArrayList<Coffee>();
	List<Order> orderList = new ArrayList<Order>();

	public OrderManager() {
		menu.add(new Coffee("아메리카노", 4000));
		menu.add(new Coffee("라떼", 4500));
		menu.add(new Coffee("바닐라라떼", 5000));
		menu.add(new Coffee("아이스티", 3500));
		menu.add(new Coffee("핫초코", 3500));
	}

	public void showMenu() {
		System.out.println("---------- Menu ----------");
		for (Coffee c : menu) {
			System.out.println(c);
		}
		System.out.println("--------------------------");
	}

	public void push(Customer customer) {

		showMenu();
		System.out.println("주문하실 메뉴를 선택해 주세요.");
		System.out.print("메뉴: ");
		String coffeeName = scanner.nextLine();
		System.out.print("수량: ");
		int count = Integer.parseInt(scanner.nextLine());
		
		int coupon = customer.getCoupon();
		for (Coffee c : menu) {
			if (coffeeName.equals(c.getCoffeeName())) {
				orderList.add(new Order(customer, c, count));
				customer.setCoupon(coupon++);
				System.out.println(customer.getNickName() + "님 " + c.getCoffeeName() + " " + count + "잔 주문 완료되었습니다.");
				System.out.println("결제하실 금액은 " + c.getPrice() * count + "원입니다.");
				break;
			}
		}
	}

	@Override
	public void run() {
		// 스레드로 리스트에 있는 메뉴 5초마다 프린트하고 리스트에서 삭제
		while(orderList.size() > 0) {
			
			for(Order o : orderList) {
				System.out.println(o.getCustomer().getNickName() + "님 주문하신 " + o.getCoffee().getCoffeeName() + " " + o.getCount() + "잔 나왔습니다.");
				orderList.remove(0);
				break;
			}
			
			try {
				sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void useCoupon() {
		// 쿠폰이 5장이면 메뉴 하나를 공짜로 선택 가능
		// 쿠폰을 0개로 setCoupon
	}

	
}