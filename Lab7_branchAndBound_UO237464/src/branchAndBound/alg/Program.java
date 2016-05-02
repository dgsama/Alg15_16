package branchAndBound.alg;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {

		long[] times = new long[7];
		long t1, t2;

		/**CASE 1**/
		t1 = System.currentTimeMillis();
		MySolution s1 = new MySolution("case1.txt");
		t2 = System.currentTimeMillis();
		times[0] = t2 - t1;
		s1.printSolutionTrace();
		
		
		/**CASE 2**/
		MySolution s2 = new MySolution("case2.txt");
		t2 = System.currentTimeMillis();
		times[1] = t2 - t1;
		s2.printSolutionTrace();

		
		/**CASE 3**/
		t1 = System.currentTimeMillis();
		MySolution s3 = new MySolution("case3.txt");
		t2 = System.currentTimeMillis();
		times[2] = t2 - t1;
		s3.printSolutionTrace();

		
		/**CASE 4**/
		t1 = System.currentTimeMillis();
		MySolution s4 = new MySolution("case4.txt");
		t2 = System.currentTimeMillis();
		times[3] = t2 - t1;
		s4.printSolutionTrace();

		
		/**CASE 5**/
		t1 = System.currentTimeMillis();
		MySolution s5 = new MySolution("case5.txt");
		t2 = System.currentTimeMillis();
		times[4] = t2 - t1;
		s5.printSolutionTrace();

		
		/**CASE 6**/
		t1 = System.currentTimeMillis();
		MySolution s6 = new MySolution("case6.txt");
		t2 = System.currentTimeMillis();
		times[5] = t2 - t1;
		s6.printSolutionTrace();

		
		/**CASE 7**/
		t1 = System.currentTimeMillis();
		MySolution s7 = new MySolution("case7.txt");
		t2 = System.currentTimeMillis();
		times[6] = t2 - t1;
		s7.printSolutionTrace();

		for (int i = 0; i < times.length; i++) {
			System.out.println("Time for case " + (i + 1) + " is = " + times[i] + "ms");
		}

	}

}
