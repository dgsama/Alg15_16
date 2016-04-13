package backtracking.alg;

import java.io.FileNotFoundException;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		Nonogram case1 = new Nonogram("case1.txt");
		case1.show();
		Nonogram case2 = new Nonogram("case2.txt");
		case2.show();
		Nonogram case3 = new Nonogram("case3.txt");
		case3.show();
		Nonogram case4 = new Nonogram("case4.txt");
		case4.show();
		Nonogram case5 = new Nonogram("case5.txt");
		case5.show();
	}

}
