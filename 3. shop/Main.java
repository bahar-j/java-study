package shop;

import java.util.Scanner;

public class Main {
	public static final Scanner SCANNER = new Scanner(System.in);
	
	public static void main(String[] args) {
		Cart cart = new Cart();
		ProductManager manager = new ProductManager();
		NoteBook notebook = new NoteBook(1, "LG gram", 100000);
		notebook.setDiscountPercentage(30);
		notebook.applyDiscount();
		Phone phone = new Phone(2, "Galaxy S20", 120000);
		phone.setDiscountPercentage(20);
		phone.applyDiscount();
		manager.addProduct(notebook);
		manager.addProduct(phone);
		
		System.out.println("****************");
		System.out.println("제품 목록: ");
		manager.printProducts();
		System.out.println("****************");
		System.out.println("");
		
		while(true) {
			
			System.out.println("1. 장바구니 추가");
			System.out.println("2. 장바구니 목록");
			System.out.println("3. 프로그램 종료");
			System.out.println("");
			System.out.println("원하시는 메뉴를 선택해주세요: ");
			int userInput = SCANNER.nextInt();
			
			switch(userInput) {
				case 1:
					System.out.println("장바구니에 추가하고 싶은 제품 번호를 입력하세요: ");
					int productId = SCANNER.nextInt();
					try {
						Product product = manager.getProductById(productId);
						cart.addCart(product);
					} catch (Exception e) {
						e.getMessage();
					}
					break;

				case 2:
					cart.printProducts();
					System.out.println("");
					break;
			
				case 3:
					System.out.println("프로그램을 종료합니다");
					return;
			}
		}
	}
}