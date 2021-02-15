package kosta.cafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

	List<Customer> customerList = new ArrayList<Customer>();
	Customer loggedInCustomer;

	public CustomerManager() { loadFromServer(); }

	public void saveToServer() {
		String json = Util.toJSON(customerList);
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9000);

			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			writer.println("save");
			writer.flush();

			writer.println(json);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadFromServer() {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9000);

			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			writer.println("load");
			writer.flush();

			String loadedData = reader.readLine();
			if (loadedData != null) customerList = Util.toList(loadedData);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void join() {
		String id = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력해주세요: ");
		String name = sc.nextLine();

		System.out.print("전화번호를 입력해주세요: ");
		String number = sc.nextLine();

		while(true) {
			System.out.print("아이디를 입력해주세요: ");
			id = sc.nextLine();
			if(!isDuplicated(id)) break;
		}

		System.out.print("비밀번호를 입력해주세요: ");
		String pw = sc.nextLine();

		System.out.print("닉네임을 입력해주세요: ");
		String nickName = sc.nextLine();

		customerList.add(new Customer(name, number, id, pw, nickName));
		saveToServer();
		System.out.println("회원가입되셨습니다🎉 다시 한 번 로그인해주세요!");
	}

	public void login() {
		loadFromServer();
		Scanner sc = new Scanner(System.in);
//		System.out.println(customerList.get(0).getLiked());
		while(true) {
			System.out.print("아이디를 입력해주세요: ");
			String id = sc.nextLine();

			System.out.print("비밀번호를 입력해주세요: ");
			String pw = sc.nextLine();

			if(canLogin(id, pw)) break;
		}
	}

	public boolean isDuplicated(String id) {
		boolean isDuplicated = false;
		for(Customer c: customerList) {
			if(c.getId().equals(id)) {
				isDuplicated = true;
				break;
			}
		}
		if(isDuplicated) {
			System.out.println("중복된 아이디입니다. 다른 아이디를 입력해주세요.");
		}
		return isDuplicated;
	}

	public boolean canLogin(String id, String pw) {
		boolean isRightId = false;
		boolean isRightPw = false;

		for(Customer c: customerList) {
			if(c.getId().equals(id)) {
				isRightId = true;
				if(c.getPw().equals(pw)) {
					System.out.println(c.getNickName()+"님 로그인 되셨습니다.");
					isRightPw = true;
					loggedInCustomer = c;
					break;
				}
			}
		}
		if(!isRightId || !isRightPw) {
			System.out.println("잘못된 아이디 혹은 비밀번호입니다⛔ 다시 한 번 입력해주세요!️");
			return false;
		} else return true;
	}

	public void addLiked() {
		loadFromServer();

		// 즐겨찾는 메뉴 추가 (customerList 수정)

		saveToServer();
	}
} 
