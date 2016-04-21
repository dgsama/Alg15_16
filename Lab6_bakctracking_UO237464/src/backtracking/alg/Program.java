package backtracking.alg;

import java.io.FileNotFoundException;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {

		long t1, t2;
		// ///////////////////////////////////////////////////////////
		t1 = System.currentTimeMillis();
		Nonogram case1 = new Nonogram("case1.txt");
		case1.calculate();
		t2 = System.currentTimeMillis();
		System.out.println("caso 1 = " + (t2 - t1) + ".\n");

		// ///////////////////////////////////////////////////////////
		t1 = System.currentTimeMillis();
		Nonogram case2 = new Nonogram("case2.txt");
		case2.calculate();
		t2 = System.currentTimeMillis();
		System.out.println("caso 2 = " + (t2 - t1) + ".\n");

		// ///////////////////////////////////////////////////////////
		t1 = System.currentTimeMillis();
		Nonogram case3 = new Nonogram("case3.txt");
		case3.calculate();
		t2 = System.currentTimeMillis();
		System.out.println("caso 3 = " + (t2 - t1) + ".\n");

		// ///////////////////////////////////////////////////////////
		t1 = System.currentTimeMillis();
		Nonogram case4 = new Nonogram("case4.txt");
		case4.calculate();
		t2 = System.currentTimeMillis();
		System.out.println("caso 4 = " + (t2 - t1) + ".\n");

		// ///////////////////////////////////////////////////////////
		t1 = System.currentTimeMillis();
		Nonogram case5 = new Nonogram("case5.txt");
		case5.calculate();
		t2 = System.currentTimeMillis();
		System.out.println("caso 5 = " + (t2 - t1) + ".\n");
	}
}
