package kosta.phone;

public class Company extends PhoneInfo {
	
	private String department;
	private String position;
	
	public Company(String name, String phoneNumber, String birth, String department, String position) {
		super(name, phoneNumber, birth);
		this.department = department;
		this.position = position;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		System.out.println("부서: "+ department);
		System.out.println("직책 : "+ position);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	

}
