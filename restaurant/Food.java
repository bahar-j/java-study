package orderManagement;

public class Food {
	private String name;
	private int price;

	public Food() {}
	
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	
	public void print() {
		System.out.println("이름: "+name);
		System.out.println("가격: "+price);
	}
	
}
