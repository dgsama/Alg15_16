package greedy.alg;

public class Person {
	String name;
	int toPay;
	int toReceive;
	int balance;

	public Person(String name) {
		this.name = name;
		toPay = 0;
		toReceive = 0;
		calculateBalance();
	}

	public Person() {
	}

	public void calculateBalance() {
		balance = toReceive - toPay;
	}

	public void setBalance(int i) {
		balance = i;
	}

	public int getBalance() {
		return balance;
	}

	public int getToPay() {
		return toPay;
	}

	public void setToPay(int tP) {
		this.toPay += tP;
		calculateBalance();
	}

	public int getToReceive() {
		return toReceive;
	}

	public void setToReceive(int tR) {
		this.toReceive += tR;
		calculateBalance();
	}

	public String getName() {
		return name;
	}

}
