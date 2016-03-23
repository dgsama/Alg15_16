package labs.lab1_1;

import java.util.Random;

/**
 * This program can solve a problem in two ways. The problem is to calculate the
 * sum of the elements of the main diagonal of a square matrix of order n.
 * 
 * @author viceg
 */
public class Diagonal1 {
	static int[][] a;

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]);
		a = new int[n][n];

		long t1 = System.currentTimeMillis();
		fillIn(a);
		long t2 = System.currentTimeMillis();

		write(a);
		System.out.println("Fill in time = " + (t2 - t1));
		
		
		t1 = System.currentTimeMillis();
		int s1 = sum1Diagonal(a);
		t2 = System.currentTimeMillis();
		System.out.println("SUM1 in time = " + (t2 - t1));
					
		t1 = System.currentTimeMillis();
		int s2 = sum2Diagonal(a);
		t2 = System.currentTimeMillis();
		System.out.println("SUM2 in time = " + (t2 - t1));

		System.out.println("FIRST SOLUTION =" + s1);
		System.out.println("SECOND SOLUTION =" + s2);
	} // main

	/**
	 * This method gives random values ​​to an array of integers, using the
	 * Random class of the java.util package It has a quadratic complexity
	 * O(n^2)
	 * 
	 * @param a
	 *            Matrix to be filled in
	 */
	public static void fillIn(int[][] a) {
		Random r = new Random();
		int n = a.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = r.nextInt(199) - 99; // values between -99 y 99

	}

	/**
	 * This method writes the square matrix. It has a quadratic complexity
	 * O(n^2)
	 * 
	 * @param a
	 *            Matrix with values
	 */
	public static void write(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(a[i][j] + "\t");
			System.out.println();
		}
	}

	/**
	 * This method iteratively computes the sum of the diagonal It has a
	 * quadratic complexity O(n^2)
	 * 
	 * @param a
	 *            Matrix with numbers
	 * @return The addition of all the numbers of the main diagonal
	 */
	public static int sum1Diagonal(int[][] a) {
		int n = a.length;
		int s = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == j)
					s = s + a[i][j];
		return s;
	}

	/**
	 * This method iteratively computes the sum of the diagonal It has a linear
	 * complexity O(n)
	 * 
	 * @param a
	 *            Matrix with numbers
	 * @return The addition of all the numbers of the main diagonal
	 */
	public static int sum2Diagonal(int[][] a) {
		int n = a.length;
		int s = 0;
		for (int i = 0; i < n; i++)
			s = s + a[i][i];
		return s;
	}

}
