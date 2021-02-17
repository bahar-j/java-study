package shop;

public class ProductManager {
	
	private Product[] products;
	private int idx;
	
	public ProductManager() {
		products = new Product[10];
		idx = 0;
	}
	
	public void addProduct(Product product) {
		products[idx++] = product;
	}

	public void printProducts() {
		for(int i = 0; i < idx; i++) {
			products[i].printOwnProduct();
		}
	}
	
	public Product getProductById(int id) throws Exception {
		int selectedProductId = 0;

		for(int i = 0; i < idx; i++) {
			selectedProductId = products[i].getId();
			if (selectedProductId == id) {
				return products[i];
			}
		}
		
		throw new Exception("찾으시는 제품이 없습니다.");
	}

}
