package kosta.cafe;

public class Main {

	public static void main(String[] args) {
		// TODO: 상담원 채팅, 직원 여러명
		CustomerManager customerManager = new CustomerManager();
		Employee employee = new Employee();
		employee.start();

		while(true) {

			System.out.println("--------------- Kosta Coffee ---------------");
			System.out.println("어서오세요:)");
			System.out.println("1.회원가입  2.로그인 3.종료");
			System.out.print("원하시는 메뉴 선택해주세요: ");
			String menu = Util.SCANNER.nextLine();

			if (menu.equals("1")) {
				customerManager.join();
			}else if (menu.equals("2")) {
				customerManager.login();
				while(true){
					System.out.println("무엇을 도와드릴까요?");
					System.out.println("1.주문하기 2.선호메뉴 등록 3.종료");
					String num = Util.SCANNER.nextLine();
					if(num.equals("1")) {
						if(customerManager.getLoggedInCustomer().getCoupon()>=5) {
							employee.useCoupon(customerManager.getLoggedInCustomer());
							continue;
						}
						if (customerManager.getLoggedInCustomer().getLiked()!=null) {
							employee.useLiked(customerManager.getLoggedInCustomer());
						} else {
							employee.getOrder(customerManager.getLoggedInCustomer());
						}
					} else if (num.equals("2")) {
						employee.showMenu();
						System.out.print("메뉴: ");
						String coffeeName = Util.SCANNER.nextLine();
						Coffee coffee = employee.grabCoffee(coffeeName);
						if(coffee != null) {
							customerManager.addLiked(coffee);
						} else {
							System.out.println("찾으시는 커피가 없습니다.");
						}
					} else if (num.equals("3")) {
						System.out.println("프로그램 종료");
						customerManager.updateCurrentUserData();
						return;
					} else {
						System.out.println("없는 메뉴입니다. 다시 시도하세요.");
					}
				}
			} else if (menu.equals("3")) {
				System.out.println("프로그램 종료");
				customerManager.updateCurrentUserData();
				return;
			}else {
				System.out.println("없는 메뉴입니다. 다시 시도하세요.");
			}
		}
	}

}