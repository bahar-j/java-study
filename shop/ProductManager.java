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
		// TODO Auto-generated method stub
		for(int i = 0; i < idx; i++) {
			if (products[i] instanceof NoteBook) {
				NoteBook notebook = (NoteBook)products[i];
				notebook.printOwnProduct();
			} else if (products[i] instanceof Phone) {
				Phone phone = (Phone)products[i];
				phone.printOwnProduct();
			}
		}
	}
	
	public Product getProductById(int id) throws Exception {
		int idOfProduct = 0;
		
		for(int i = 0; i < idx; i++) {
			if (products[i] instanceof NoteBook) {
				NoteBook notebook = (NoteBook)products[i];
				idOfProduct = notebook.getId();
				if (idOfProduct == id) {
					return notebook;
				}
			} else if (products[i] instanceof Phone) {
				Phone phone = (Phone)products[i];
				idOfProduct = phone.getId();
				if (idOfProduct == id) {
					return phone;
				}
			}
		}
		
		throw new Exception("찾으시는 제품이 없습니다.");
	}

}
