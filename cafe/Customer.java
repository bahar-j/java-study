package cafe;

import java.io.Serializable;

public class Customer implements Serializable {

	private String name;
	private String number;
	private String id;
	private String pw;
	private String nickName;
	private int coupon;
	private Coffee preference;

	public Customer() {

	}

	public Customer(String name, String number, String id, String pw, String nickName) {
		this.name = name;
		this.number = number;
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.coupon = 0;
	}

	public Customer(String name, String number, String id, String pw, String nickName, int coupon, Coffee preference) {
		this.name = name;
		this.number = number;
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.coupon = coupon;
		this.preference = preference;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNickName() {
		return nickName;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public Coffee getPreference() {
		return preference;
	}

	public void setPreference(Coffee preference) {
		this.preference = preference;
	}


}
