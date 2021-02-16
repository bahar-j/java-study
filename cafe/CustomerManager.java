package kosta.cafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
	private List<Customer> customerList = new ArrayList<Customer>();
	private Customer loggedInCustomer;
	private int idx;

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
		System.out.print("이름을 입력해주세요: ");
		String name = Util.SCANNER.nextLine();

		System.out.print("전화번호를 입력해주세요: ");
		String number = Util.SCANNER.nextLine();

		while(true) {
			System.out.print("아이디를 입력해주세요: ");
			id = Util.SCANNER.nextLine();
			if(!isDuplicated(id)) break;
		}

		System.out.print("비밀번호를 입력해주세요: ");
		String pw = Util.SCANNER.nextLine();

		System.out.print("닉네임을 입력해주세요: ");
		String nickName = Util.SCANNER.nextLine();

		customerList.add(new Customer(name, number, id, pw, nickName));
		saveToServer();
		System.out.println("회원가입되셨습니다🎉 다시 한 번 로그인해주세요!");
	}

	public void login() {
		loadFromServer();
		while(true) {
			System.out.print("아이디를 입력해주세요: ");
			String id = Util.SCANNER.nextLine();

			System.out.print("비밀번호를 입력해주세요: ");
			String pw = Util.SCANNER.nextLine();

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
					idx = customerList.indexOf(c);
					break;
				}
			}
		}
		if(!isRightId || !isRightPw) {
			System.out.println("잘못된 아이디 혹은 비밀번호입니다⛔ 다시 한 번 입력해주세요!️");
			return false;
		} else return true;
	}

	public void addLiked(Coffee favoriteCoffee) {
		loadFromServer();
		Customer customerInfo = null;
		for(Customer c: customerList) {
			if(c.getId().equals(loggedInCustomer.getId())) {
				customerInfo = c;
				break;
			}
		}
		customerInfo.setLiked(favoriteCoffee);
		loggedInCustomer = customerInfo;
		saveToServer();
	}

	public void updateCurrentUserData() {
		customerList.remove(idx);
		customerList.add(loggedInCustomer);
		saveToServer();
	}

	public Customer getLoggedInCustomer() {
		return loggedInCustomer;
	}


} 
