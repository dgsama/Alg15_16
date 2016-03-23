package greedy.alg;

public class Payment {

	String source;
	String target;
	int value;

	public Payment(String source, String target, int value) {
		this.source = source;
		this.target = target;
		this.value = value;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

	public int getValue() {
		return value;
	}

}
