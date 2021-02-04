package kosta.phone;

import java.util.Scanner;

public class Main {
	
	public static String getPhoneNumber() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 전화번호: ");
		String userNumber = sc.nextLine();
		if (userNumber.equals("")) { // if(userNumber == null || userNumber.length == 0)
			throw new Exception("전화번호가 입력되지 않았습니다");
		}
		return userNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager manager = new Manager();
		
		while(true) { 
			System.out.println("1.추가 2.전체 출력 3.검색 4.전화번호 수정 5.삭제 6.정렬 7. 저장 8. 불러오기 9.종료");
			System.out.print("선택: ");
			String menu = DataInput.SCANNER.nextLine();
			switch(menu) {
				case "1":
					System.out.print("추가하고 싶은 타입을 선택하세요(1. 일반 2. 동창 3. 직장): ");
					int type = DataInput.SCANNER.nextInt();
					DataInput.SCANNER.nextLine();
					System.out.print("이름: ");
					String name = DataInput.SCANNER.nextLine();
					System.out.print("전화번호: ");
					String phoneNumber = DataInput.SCANNER.nextLine();
					System.out.print("생년월일: ");
					String birth = DataInput.SCANNER.nextLine();
					if (type == 1) {
						manager.addPhoneInfo(name, phoneNumber, birth);
					} else if (type == 2) {
						System.out.print("부서: ");
						String department = DataInput.SCANNER.nextLine();
						System.out.print("직책: ");
						String position = DataInput.SCANNER.nextLine();
						manager.addPhoneInfo(name, phoneNumber, birth, department, position);
					} else if (type == 3) {
						System.out.print("학번: ");
						int studentId = DataInput.SCANNER.nextInt();
						DataInput.SCANNER.nextLine();
						System.out.print("전공: ");
						String major = DataInput.SCANNER.nextLine();
						manager.addPhoneInfo(name, phoneNumber, birth, studentId, major);
					}
					break;
				case "2":
					manager.listPhoneInfo();
					break;
				case "3":
					System.out.print("찾고 싶은 사람의 이름: ");
					String searchName = DataInput.SCANNER.nextLine();
					manager.searchPhoneInfo(searchName);
					break;
				case "4":
					System.out.print("수정할 사람의 이름: ");
					String userName = DataInput.SCANNER.nextLine();
					String userNumber = "";
					try {
						userNumber = getPhoneNumber();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					} 
					manager.updatePhoneInfo(userName, userNumber);
					break;
				case "5":
					System.out.print("삭제할 사람의 이름: ");
					String deleteName = DataInput.SCANNER.nextLine();
					manager.deletePhoneInfo(deleteName);
					break;
					
				case "6":
					manager.sortList();
					manager.listPhoneInfo();
					break;
					
				case "7":
					manager.saveObject();
					break;
				case "8":
					manager.loadObject();
					break;
				default:
					return;
					
			}
		}
		
	}

}
