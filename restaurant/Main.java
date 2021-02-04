package orderManagement;

import java.util.Scanner;

public class Main {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Worker worker = new Worker();
		SaleBook saleBook = new SaleBook();
		worker.createMenu("pizza", 15000);
		worker.createMenu("chicken", 18000);
		worker.createMenu("hamburger", 7000);

		while(true) {
			System.out.println("***************************************");
			System.out.println("1. 주문요청 2. 주문처리 3. 매출액 총액 4. 종료");
			System.out.println("***************************************");
			System.out.println("어떤 작업을 하시겠습니까? ");

			int input = SCANNER.nextInt();

			switch(input) {
				case 1:
					worker.addOrder();
					break;
				case 2:
					try {
						int amount = worker.serveFood();
						saleBook.add(amount);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.printf("매출액 총액은 %d입니다", saleBook.getTotalSales());
					System.out.println();
					break;
				default:
					return;
			}
		}
	}
}
