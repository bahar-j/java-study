package restaurant;

public class SaleBook {
	private int totalSales;
	
	public SaleBook() {
		totalSales = 0;
	}
	
	public void add(int sum) {
		totalSales += sum;
	}
	
	public int getTotalSales() {
		return totalSales;
	}
}
