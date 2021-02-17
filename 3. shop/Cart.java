package shop;

public class Cart {
	private Product[] products;
	private int idx;
	private int total;
	
	public Cart() {
		products = new Product[5];
		idx = 0;
		total = 0;
	}
	
	public void addCart(Product product) {
		products[idx++] = product;
		total += product.applyDiscount(); // 인터페이스에 정의된 추상 메서드이므로 형변환 없이 사용 가능
	}
	
	public void printProducts() {
		for(int i = 0; i < idx; i++) {
			products[i].printOwnProduct();
		}
		System.out.println("장바구니 제품 총액: "+total);
	}

}
