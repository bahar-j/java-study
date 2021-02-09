package kosta.phone;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Manager {
//	PhoneInfo[] arr = new PhoneInfo[10];
	List<PhoneInfo> list;
//	PhoneInfo[] arr;
//	int idx;
	
	public Manager() {
//		this.arr = new PhoneInfo[10];
//		this.idx = 0;
		this.list = new LinkedList<PhoneInfo>();
	}
	
	
	public void addPhoneInfo(String name, String phoneNumber, String birth) {
		//이름, 전화번호, 생년월일 입력
		// PhoneInfo 객체 생성 => 배열에 추가
//		arr[idx++] = new PhoneInfo(name, phoneNumber, birth);
		list.add(new PhoneInfo(name, phoneNumber, birth));
	}
	
	public void addPhoneInfo(String name, String phoneNumber, String birth, String department, String position) {
		//이름, 전화번호, 생년월일 입력
		// PhoneInfo 객체 생성 => 배열에 추가
//		arr[idx++] = new Company(name, phoneNumber, birth, department, position);
		list.add(new Company(name, phoneNumber, birth, department, position));
	}
	
	public void addPhoneInfo(String name, String phoneNumber, String birth, int studentId, String major) {
		//이름, 전화번호, 생년월일 입력
		// PhoneInfo 객체 생성 => 배열에 추가
//		arr[idx++] = new University(name, phoneNumber, birth, studentId, major);
		list.add(new University(name, phoneNumber, birth, studentId, major));
	}
	
	public void listPhoneInfo() {
		// 배열에 있는 PhoneInfo 객체 모두 출력
		// 1. 전체 2. 동창 3. 직장 
//		for(PhoneInfo info: arr) {
//			if (info != null) {
//				info.show();
//			} else break;
//		}
		
		Iterator<PhoneInfo> iterator = list.iterator();
		while(iterator.hasNext()) {
			iterator.next().show();
		}
	}
	
	public void searchPhoneInfo(String name) {
		//키보드로부터 이름 입력, 해당 정보 출력 
		boolean isFound = false;
//		for(PhoneInfo info: arr) {
//			if (info != null) {
//				if (info.getName().equals(name)) {
//					isFound = true;
//					info.show();
//				}
//			} else break;
//		}
		
		Iterator<PhoneInfo> iterator = list.iterator();
		while(iterator.hasNext()) {
			PhoneInfo person = iterator.next();
			if (person.getName().equals(name)) {
				isFound = true;
				person.show();
				break;
			}
		}
		
		if(!isFound) {
			System.out.println("찾을 수 없습니다");
		}
		
	}
	
	public void updatePhoneInfo(String name, String number) {
		boolean isFound = false;
//		for(PhoneInfo info: arr) {
//			if (info != null) {
//				if (info.getName().equals(name)) {
//					isFound = true;
//					info.updateNumber(number);
//				}
//			} else break;
//		}
		
		Iterator<PhoneInfo> iterator = list.iterator();
		while(iterator.hasNext()) {
			PhoneInfo person = iterator.next();
			if (person.getName().equals(name)) {
				isFound = true;
				person.updateNumber(number);
				break;
			}
		}
		
		if(!isFound) {
			System.out.println("찾을 수 없습니다");
		}
	}
	
	public void deletePhoneInfo(String name) {
		boolean isFound = false;
//		for(int i = 0; i < idx; i++) {
//			if (arr[i].getName().equals(name)) {
//				isFound = true;
//				arr[i] = null;
//				for(int j = i+1; j < idx; j++) {
//					arr[j-1] = arr[j];
//				}
//				arr[idx-1] = null;
//				break;
//			}
//		}
//		idx--;
		
		Iterator<PhoneInfo> iterator = list.iterator();
		while(iterator.hasNext()) {
			PhoneInfo person = iterator.next();
			if (person.getName().equals(name)) {
				isFound = true;
				list.remove(person);
				break;
			}
		}
		
		if(!isFound) {
			System.out.println("찾을 수 없습니다");
		}
	}
	
	public void sortList() {
//		Collections.sort(list);
		Collections.sort(list, new Comparator<PhoneInfo>() {

			@Override
			public int compare(PhoneInfo person1, PhoneInfo person2) {
				// TODO Auto-generated method stub
				if (person1.getName().compareTo(person2.getName()) < 0) {
					return -1;
				} else if (person1.getName().compareTo(person2.getBirth()) > 0) {
					return 1;
				}
				return 0;
			}
			
		});
	}
	
	public void saveObject() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("phoneInfo.ser", true));
			oos.writeObject(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void loadObject() {
		ObjectInputStream ols = null;
		try {
			ols = new ObjectInputStream(new FileInputStream("phoneInfo.ser"));
			list = (LinkedList<PhoneInfo>) ols.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				ols.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveDataToServer() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9000);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			writer.println("8");
			writer.flush();
			
			String data = Util.objectToJSONString(list);
			writer.println(data);
			writer.flush();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	public void loadDataFromServer() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9000);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			writer.println("9");
			writer.flush();
			
			String loadedData = reader.readLine();
			List<PhoneInfo> convertedData = Util.JSONtoObject(loadedData);
			System.out.println(convertedData);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void terminateServer() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9000);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("quit");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
