package sesion2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion1 {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]);
		
		// String result = "";
		// result = result + (t2-t1)+"\n"; PONER ESTO PARA QUE GUARDE LOS
		// TIEMPOS EN UNA COLUMNA EN ORDEN

		for (int n = 10000; n <= Integer.MAX_VALUE; n *= 2) {
			v = new int[n];
			long t1 = 0, t2 = 0;
			System.out.println("n = " + n);

			Vector.sorted(v);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				insertion(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println("SORTED: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			Vector.inverselySorted(v);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				insertion(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println("INVERSELY_SORTED: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			Vector.random(v, 1000000);
			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				insertion(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println("RANDOM: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);
		}

		// System.out.println("\n\n" + result);
	}

	public static void insertion(int[] elements) {
		int x;
		int j;

		for (int i = 0; i < elements.length; i++) {
			x = elements[i];
			j = i - 1;
			while (j >= elements[0] && x < elements[j]) {
				elements[j + 1] = elements[j];
				j--;
			}
			elements[j + 1] = x;
		}

	}
}
