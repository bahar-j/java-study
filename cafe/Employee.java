package kosta.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee extends Thread {
	Scanner scanner = new Scanner(System.in);

	List<Coffee> menu = new ArrayList<Coffee>();
	List<Order> orderList = new ArrayList<Order>();

	public Employee() {
		menu.add(new Coffee("아메리카노", 4000));
		menu.add(new Coffee("라떼", 4500));
		menu.add(new Coffee("바닐라라떼", 5000));
		menu.add(new Coffee("icetea", 3500));
		menu.add(new Coffee("핫초코", 3500));
	}

	public Coffee grabCoffee(String coffeeName) {
		Coffee picked = null;
		for(Coffee coffee: menu) {
			if(coffee.getCoffeeName().equals(coffeeName)) {
				picked = coffee;
				break;
			}
		}
		return picked;
	}

	public void showMenu() {
		System.out.println("---------- Menu ----------");
		for (Coffee c : menu) {
			System.out.println(c);
		}
		System.out.println("--------------------------");
	}

	public void getOrder(Customer customer) {

		showMenu();
		System.out.println("주문하실 메뉴를 선택해 주세요.");
		System.out.print("메뉴: ");
		String coffeeName = scanner.nextLine();
		System.out.print("수량: ");
		int count = Integer.parseInt(scanner.nextLine());
		int coupon = customer.getCoupon();
		Coffee c = grabCoffee(coffeeName);
		if(c != null) {
			push(customer, c, count, coupon+1);
		} else System.out.println("찾으시는 커피가 없습니다.");
	}

	public synchronized void push(Customer customer, Coffee c, int count, int coupon) {
		orderList.add(new Order(customer, c, count));
		customer.setCoupon(coupon);
		System.out.println(customer.getNickName() + "님 " + c.getCoffeeName() + " " + count + "잔 주문 완료되었습니다.");
		System.out.println("결제하실 금액은 " + c.getPrice() * count + "원입니다.");
		this.notify();
	}

	@Override
	public synchronized void run() {
		while (true) {
			if(orderList.size() == 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (Order o : orderList) {
				System.out.println(o.getCustomer().getNickName() + "님 주문하신 " + o.getCoffee().getCoffeeName() + " "
						+ o.getCount() + "잔 나왔습니다.");
				orderList.remove(0);
				break;
			}

			try {
				sleep(5000); // ~커피 만드는 시간~ thread 하나이므로 다른 주문 받을 수 없음 !
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void useCoupon(Customer customer) {
		showMenu();
		System.out.println("주문하실 메뉴를 선택해 주세요.");
		System.out.print("메뉴: ");
		String coffeeName = scanner.nextLine();
		System.out.print("수량: ");
		int count = Integer.parseInt(scanner.nextLine());
		int coupon = customer.getCoupon();

		Coffee c = grabCoffee(coffeeName);
		if(c != null) {
			push(customer, c, count, coupon-5);
			System.out.println("쿠폰이 사용되었습니다.");
		} else System.out.println("찾으시는 커피가 없습니다.");
	}

	public void useLiked(Customer customer) {

		System.out.println("즐겨찾는 메뉴 " + customer.getLiked() + "을(를) 주문하시겠습니까?");
		System.out.println("[1] 네\t[2] 아니오");
		int num = Integer.parseInt(scanner.nextLine());
		int coupon = customer.getCoupon();

		if (num == 1) {
			System.out.print("수량: ");
			int count = Integer.parseInt(scanner.nextLine());
			push(customer, customer.getLiked(), count, coupon+1);
		} else if (num == 2) {
			getOrder(customer);
		} else {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
		}

	}

}
