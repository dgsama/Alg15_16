package labs.lab1_1;

/**
 * This program serves to measure times automatically increasing the size of the
 * problem. In addition, we use a repetition value determined by nTimes, an
 * argument of the program
 * 
 * @author viceg
 */
public class Vector4 {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]);
		long t1, t2, tf1, tf2, ts1 = 0, ts2 = 0, tm1 = 0, tm2 = 0;
		int sum = 0;
		int[] m = new int[2];

		String tiemposFill = "";
		String tiemposSum = "";
		String tiemposMax = "";

		for (int n = 10; n <= 100000000; n *= 5) { // n is increased *5
			v = new int[n];

			tf1 = System.currentTimeMillis();
			Vector1.fillIn(v);
			tf2 = System.currentTimeMillis();

			tm1 = System.currentTimeMillis();
			Vector1.maximum(v, m);
			tm2 = System.currentTimeMillis();

			ts1 = System.currentTimeMillis();
			// We have to repeat the whole process to be measured
			for (int repetition = 1; repetition <= nTimes; repetition++) {

				sum = Vector1.sum(v);

			}
			ts2 = System.currentTimeMillis();

			System.out.println("Size = " + n +"** Fill in time = " + (tf2 - tf1)
					+ "ms **  Sum = " + sum + "--> Sum time = " + (ts2 - ts1)
					+ "ms ** Maximum time = " + (tm2 - tm1) + "ms ** nTimes = "
					+ nTimes);

			tiemposFill += (tf2 - tf1) + "\n";
			tiemposSum += (ts2 - ts1) + "\n";
		tiemposMax += (tm2 - tm1) + "\n";

		}// for
		
		System.out.println("\n FILL IN \n\n"+tiemposFill);
		System.out.println("\n SUM \n\n"+tiemposSum);
		System.out.println("\n MAX \n\n"+tiemposMax);

	}// main

}
