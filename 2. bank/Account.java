package bank;

public class Account {
	private String id;
	private long balance;
	
	public Account() {}
	
	public Account(String id, long balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public void deposit(long amount) {
		balance += amount;
	}
	
	public boolean withdraw(long amount) {
		boolean isDone = false;
		if (balance >= amount) {
			balance -= amount;
			isDone = true;
		}
		return isDone;
	}

	public String getId() {
		return id;
	}

	public long getBalance() {
		return balance;
	}
	
}
