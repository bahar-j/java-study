package cafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Waitress {
	private List<Coffee> menu = new ArrayList<Coffee>();
	private POS pos;

	public Waitress(POS pos) {
		prepareMenu();
		this.pos = pos;
	}

	public void prepareMenu() {
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

	public void getOrder(Customer customer) {
		showMenu();
		System.out.println("주문하실 메뉴를 선택해 주세요.");
		System.out.print("메뉴: ");
		String coffeeName = Util.SCANNER.nextLine();
		System.out.print("수량: ");
		int count = Integer.parseInt(Util.SCANNER.nextLine());
		int coupon = customer.getCoupon();
		Coffee c = grabCoffee(coffeeName);
		if(c != null) {
			makeOrder(customer, c, count, coupon+count, false);
		} else System.out.println("찾으시는 커피가 없습니다.");
	}

	public void makeOrder(Customer customer, Coffee c, int count, int coupon, boolean useCoupon) {
		pos.push(new Order(customer, c, count));
		customer.setCoupon(coupon);
		System.out.println(customer.getNickName() + "님 " + c.getCoffeeName() + " " + count + "잔 주문 완료되었습니다.");
		if (!useCoupon) System.out.println("결제하실 금액은 " + c.getPrice() * count + "원입니다.");
	}

	public boolean useCoupon(Customer customer) {
		System.out.println("쿠폰 사용이 가능합니다. 쿠폰을 사용하시겠습니까?");
		System.out.println("[1] 네\t[2] 아니오");
		int num = Integer.parseInt(Util.SCANNER.nextLine());
		int coupon = customer.getCoupon();
		if (num == 1) {
			showMenu();
			System.out.println("주문하실 메뉴를 선택해 주세요.");
			System.out.print("메뉴: ");
			String coffeeName = Util.SCANNER.nextLine();

			Coffee c = grabCoffee(coffeeName);
			if (c != null) {
				makeOrder(customer, c, 1, coupon - 5, true);
				System.out.println("쿠폰이 사용되었습니다.");
				return true;
			} else System.out.println("찾으시는 커피가 없습니다.");
		}
		return false;
	}

	public void orderByPreference(Customer customer) {

		System.out.println("즐겨찾는 메뉴 " + customer.getPreference() + "을(를) 주문하시겠습니까?");
		System.out.println("[1] 네\t[2] 아니오");
		int num = Integer.parseInt(Util.SCANNER.nextLine());
		int coupon = customer.getCoupon();

		if (num == 1) {
			System.out.print("수량: ");
			int count = Integer.parseInt(Util.SCANNER.nextLine());
			makeOrder(customer, customer.getPreference(), count, coupon+count, false);
		} else if (num == 2) {
			getOrder(customer);
		} else {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
		}

	}
}
