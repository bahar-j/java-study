package orderManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Worker {
	private Queue<Order> orders;
	private List<Food> foodsInRestaurant;
	private static final Scanner SCANNER = new Scanner(System.in);

	public Worker() {
		orders = new LinkedList<Order>();
		foodsInRestaurant = new LinkedList<Food>();
	}

	public void createMenu(String name, int price) {
		Food food = new Food(name, price);
		foodsInRestaurant.add(food);
	}

	public void bringMenu() {
		Iterator<Food> iterator = foodsInRestaurant.iterator();
		while(iterator.hasNext()) {
			Food food = iterator.next();
			food.print();
		}
	}

	public Food checkWhichFood(String name) throws Exception {
		Iterator<Food> iterator = foodsInRestaurant.iterator();
		while(iterator.hasNext()) {
			Food food = iterator.next();
			if(food.getName().equals(name)) {
				return food;
			}
		}

		throw new Exception("찾으시는 음식이 없어요.");
	}

	public int checkSum(List<Food> foods) {
		int sum = 0;
		Iterator<Food> iterator = foods.iterator();
		while(iterator.hasNext()) {
			Food food = iterator.next();
			sum += food.getPrice();
		}
		return sum;
	}

	public void addOrder() {
		List<Food> orderList = new ArrayList<Food>();

		System.out.println("메뉴판입니다: ");
		bringMenu();
		System.out.println("어떤 음식을 주문하시겠어요? ");
		while(true) {
			String name = SCANNER.nextLine();
			if (name.equals("end")) break;
			try {
				Food food = checkWhichFood(name);
				orderList.add(food);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("다시 한 번 말씀해주세요.");
				continue;
			}
		}

		Order order = new Order(orderList, checkSum(orderList));
		orders.offer(order);
		System.out.println("주문을 다시 한 번 확인합니다.");
		order.printOrder();
	}

	public int serveFood() throws Exception {
		if(!orders.isEmpty()) {
			Order order = orders.poll();
			System.out.println("주문하신 메뉴 나왔습니다.");
			order.printOrder();
			return order.getAmount();
		}

		throw new Exception("처리되지 않은 주문이 없습니다.");
	}
}
