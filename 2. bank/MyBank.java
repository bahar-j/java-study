package bank;

public class MyBank {
	private Customer[] customers;
	private int customersNum;
	
	public MyBank() {
		this.customers = new Customer[5];
		this.customersNum = 0;
	}

	public void addCustomer(String id, String name, long balance) {
		this.customers[customersNum++] = new Customer(id, name, balance);
	}
	
	public int getCustomersNum() {
		return customersNum;
	}
	
	public Customer getCustomer(String id) throws Exception {
		for(int i = 0; i < customersNum; i++) {
			if (customers[i].getId().equals(id)) return customers[i];
		}
		throw new Exception("해당 id의 고객을 찾을 수 없습니다.");
	}
	
	public Customer[] getAllCustomers() {
		Customer[] copiedCustomer = new Customer[customersNum];
		System.arraycopy(customers, 0, copiedCustomer, 0, customersNum);
		return copiedCustomer;
	}
}
