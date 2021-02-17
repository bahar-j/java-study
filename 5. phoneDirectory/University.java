package phoneDirectory;

public class University extends PhoneInfo {
	private int studentId;
	private String major;
	
	public University(String name, String phoneNumber, String birth, int studentId, String major) {
		super(name, phoneNumber, birth);
		this.studentId = studentId;
		this.major = major;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		System.out.println("학번 : "+ studentId);
		System.out.println("전공 : "+ major);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
	
}
