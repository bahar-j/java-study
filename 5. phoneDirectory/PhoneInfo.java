package phoneDirectory;

import java.io.Serializable;

public class PhoneInfo implements Serializable {
	// 멤버 변수 선언
	// 멤버 메서드 정의
	private String name;
	private String phoneNumber;
	private String birth;
	
	public PhoneInfo() {}
	
	public PhoneInfo(String name, String phoneNumber, String birth) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birth = birth;
	}
	
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getBirth() {
		return birth;
	}
	
	public void updateNumber(String number) {
		this.phoneNumber = number;
	}
	
	public void show() {
		System.out.println("이름: "+ name);
		System.out.println("전화번호: "+ phoneNumber);
		System.out.println("생년월일 : "+ birth);
	}

	@Override
	public String toString() {
		return "PhoneInfo [name=" + name + ", phoneNumber=" + phoneNumber + ", birth=" + birth + "]";
	}
	

//	@Override
//	public int compareTo(PhoneInfo person) {
//		// TODO Auto-generated method stub
//		if(this.name.compareTo(person.getName()) < 0) {
//			return -1;
//		} else if (this.name.compareTo(person.getName()) > 0) {
//			return 1;
//		}
//		return 0;
//	}
}
