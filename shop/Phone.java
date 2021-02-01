package shop;

public class Phone implements Product {
	
	private int productId;
	private String productName;
	private int price;
	private int discountPercentage;
	
	public Phone() {}
	
	public Phone(int productId, String productName, int price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	@Override
	public void printOwnProduct() {
		// TODO Auto-generated method stub
		System.out.println("제품번호: "+productId);
		System.out.println("제품명: "+productName);
		System.out.println("가격: "+price);
	}

	@Override
	public int applyDiscount() {
		// TODO Auto-generated method stub
		this.price = (int)(price * (1- discountPercentage/100.0));
		return this.price
	}
	
	public int getId() {
		return this.productId;
	}
	
	public void setDiscountPercentage(int percentage) {
		this.discountPercentage = percentage;
	}

}
