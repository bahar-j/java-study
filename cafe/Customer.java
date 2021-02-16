package kosta.cafe;

import java.io.Serializable;

public class Customer implements Serializable {

	private String name;
	private String number;
	private String id;
	private String pw;
	private String nickName;
	private int coupon;
	private Coffee liked;
	
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
	
	public Customer(String name, String number, String id, String pw, String nickName, int coupon, Coffee liked) {
		this.name = name;
		this.number = number;
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.coupon = coupon;
		this.liked = liked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public Coffee getLiked() {
		return liked;
	}

	public void setLiked(Coffee liked) {
		this.liked = liked;
	}
	
	
}