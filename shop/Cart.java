package shop;

public class Cart {
	Product[] products;
	int idx;
	
	public Cart() {
		products = new Product[5];
		idx = 0;
	}
	
	public void addCart(Product product) {
		products[idx++] = product;
	}
	
	public void printProducts() {
		for(int i = 0; i < idx; i++) {
			if (products[i] instanceof NoteBook) {
				NoteBook notebook = (NoteBook) products[i];
				notebook.printOwnProduct();
			} else if (products[i] instanceof Phone) {
				Phone phone = (Phone) products[i];
				phone.printOwnProduct();
			}
		}
	}

}
