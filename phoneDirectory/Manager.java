package kosta.phone;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Manager {
	List<PhoneInfo> list;

	public Manager() {
		this.list = new LinkedList<PhoneInfo>();
	}
	
	
	public void addPhoneInfo(String name, String phoneNumber, String birth) {
		list.add(new PhoneInfo(name, phoneNumber, birth));
	}
	
	public void addPhoneInfo(String name, String phoneNumber, String birth, String department, String position) {
		list.add(new Company(name, phoneNumber, birth, department, position));
	}
	
	public void addPhoneInfo(String name, String phoneNumber, String birth, int studentId, String major) {
		list.add(new University(name, phoneNumber, birth, studentId, major));
	}
	
	public void listPhoneInfo() {
		Iterator<PhoneInfo> iterator = list.iterator();
		while(iterator.hasNext()) {
			iterator.next().show();
		}
	}
	
	public void searchPhoneInfo(String name) {
		boolean isFound = false;
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
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
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
		} finally {
			try {
				ols.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
