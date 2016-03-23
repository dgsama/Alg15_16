package labs.lab1_1;

/**
 * This program serves to measure times automatically increasing the size of the
 * problem (n) and also using a time scale determined by nTimes, which is taken
 * from the argument arg[1] It also gets as an execution argument (arg[0]) the
 * operation on which we will focus the measurement (options 0,1,2 respectively)
 * 
 * @author viceg
 */

public class Diagonal2 {
	static int[][] a;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[1]); // nTimes
		int option = Integer.parseInt(arg[0]); // selected option
		long t1, t2;

		// n is incremented * 2
		for (int n = 3; n < 1000; n *= 2) {
			Diagonal1 d1 = new Diagonal1();
			a = new int[n][n];
			if (option == 0) { // fill in the matrix
				t1 = System.currentTimeMillis();
				for (int repetitions = 0; repetitions < nTimes; repetitions++)
					d1.fillIn(a);
				t2 = System.currentTimeMillis();

				System.out.println("Fill in Option|| time: " + (t2 - t1) + " || nTimes: " + nTimes);
			}

			else if (option == 1) { // sum of the diagonal (one way)
				d1.fillIn(a);

				t1 = System.currentTimeMillis();
				for (int repetitions = 0; repetitions < nTimes; repetitions++)
					d1.sum1Diagonal(a);
				t2 = System.currentTimeMillis();

				System.out.println("Sum1 Option|| time: " + (t2 - t1) + " || nTimes: " + nTimes);
			}

			else if (option == 2) { // sum of the diagonal (another way)
				d1.fillIn(a);

				t1 = System.currentTimeMillis();
				for (int repetitions = 0; repetitions < nTimes; repetitions++)
					d1.sum2Diagonal(a);
				t2 = System.currentTimeMillis();

				System.out.println("Sum2 Option|| time: " + (t2 - t1) + " || nTimes: " + nTimes);
			} else
				System.out.println("INCORRECT OPTION");
		} // main

	} // class
}
