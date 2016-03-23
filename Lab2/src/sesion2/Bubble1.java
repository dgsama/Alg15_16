package sesion2;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble1 {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]); // size of the problem
		String result = "";

		// result = result + (t2-t1)+"\n"; PONER ESTO PARA QUE GUARDE LOS
		// TIEMPOS EN UNA COLUMNA EN ORDEN

		for (int n = 10000; n < 10240000; n *= 2) {
			v = new int[n];
			long t1, t2;
			System.out.println("n = " + n);

			Vector.sorted(v);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				bubble(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println("SORTED: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			Vector.inverselySorted(v);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				bubble(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println("INVERSAL: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			Vector.random(v, 1000000);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				bubble(v);
			}
			t2 = System.currentTimeMillis();

			// System.out.println("RANDOM: TIME=" + (t2 - t1) + " **nTimes="
			// +nTimes);

		} // fin loop main

		System.out.println("\n\n" + result);
	}// fin de main

	public static void bubble(int[] elements) {
		int i = 0;
		boolean hasChange = true;

		while (hasChange && (i < elements.length)) {
			hasChange = false;
			for (int j = elements.length; j <= i + 1; j--) {
				if (elements[j - 1] > elements[j]) {
					Util.interchange(elements, j - 1, j);
					hasChange = true;
				}
			}
			i++;
		}

	}
}
