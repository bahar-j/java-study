package shop;

public class NoteBook implements Product {
	
	private int productId;
	private String productName;
	private int price;
	private int discountPercentage;
	
	public NoteBook() {}
	
	public NoteBook(int productId, String productName, int price) {
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
	public void applyDiscount() {
		// TODO Auto-generated method stub
		this.price = (int)(price * (1 - discountPercentage/100.0));
	}
	
	public int getId() {
		return this.productId;
	}
	
	public void setDiscountPercentage(int percentage) {
		this.discountPercentage = percentage;
	}

}
