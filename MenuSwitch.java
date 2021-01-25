package kosta.basic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSwitch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		kiosk();
	}

	static void kiosk() {
		String[] menus = new String[5];
		int idx = 0;
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("번호를 입력하세요: ");
				int userInput = sc.nextInt();
				sc.nextLine();
			
				switch(userInput) {
					case 1:
						System.out.print("생성할 메뉴를 입력하세요: ");
						String menu = sc.nextLine();
						menus[idx++] = menu;
						break;
					case 2:
						for (int i = 0; i < idx; i++) {
							System.out.print(menus[i]+", ");
						}
						System.out.println("");
						break;
					case 3:
						System.out.print("인덱스를 확인할 메뉴를 입력하세요: ");
						String menu2 = sc.nextLine();
						for (int i = 0; i < menus.length; i++) {
							if (menus[i] == null) break;
							if (menus[i].equals(menu2)) {
								System.out.print("인덱스: ");
								System.out.println(i);
								break;
							}
						}
						break;
					case 4:
						System.out.println("프로그램 종료");
						return;
					default:
						System.out.println("잘못된 입력입니다.");
				}
			} catch (InputMismatchException ex) {
				System.out.println("잘못된 입력입니다.");
				String flush = sc.next();
			}
		}
		
	}
}
