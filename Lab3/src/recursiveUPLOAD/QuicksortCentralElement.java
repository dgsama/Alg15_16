package recursiveUPLOAD;

/* This program can be used to sort n elements with 
 * the best algorithm. It is the QUICKSORT */
public class QuicksortCentralElement {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]); // size of the problem
		long t1 = 0, t2 = 0;

		String result = "";

		for (int n = 1000; n < 65536001; n *= 2) {
			System.out.println("n = " + n);
			v = new int[n];

			Vector.sorted(v);
			t1 = System.currentTimeMillis();
			for (int i = 0; i <= nTimes; i++)
				quicksort(v);
			t2 = System.currentTimeMillis();
			
			result = result + (t2-t1)+"\n";
			System.out.println("SORTED: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			/*Vector.inverselySorted(v);
			t1 = System.currentTimeMillis();
			for (int i = 0; i <= nTimes; i++)
				quicksort(v);
			t2 = System.currentTimeMillis();
			System.out.println("INVERSE: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);

			Vector.random(v, 1000000);
			t1 = System.currentTimeMillis();
			for (int i = 0; i <= nTimes; i++)
				quicksort(v);
			t2 = System.currentTimeMillis();
			System.out.println("RANDOM: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);*/
		}

		System.out.println("\n\n" + result);
	}

	private static int centralElement(int elements[], int left, int right) {
		return (left + right) / 2;
	}

	private static void quickSort(int elements[], int left, int right) {
		int i = left;
		int j = right - 1;
		int pivot;

		if (left < right) { // if there is one element it is not necessary
			int center = centralElement(elements, left, right);
			// if there are less than or equal to 3 elements, there are just
			// ordered
			pivot = elements[center]; // choose the pivot
			Util.interchange(elements, center, right); // hide the pivot
			do {
				while (elements[i] <= pivot && i < right)
					i++; // first element > pivot
				while (elements[j] >= pivot && j > left)
					j--; // first element < pivot
				if (i < j)
					Util.interchange(elements, i, j);
			} while (i < j); // end while

			// we set the position of the pivot
			Util.interchange(elements, i, right);
			quickSort(elements, left, i - 1);
			quickSort(elements, i + 1, right);
		} // if

	}

	public static void quicksort(int[] elements) {
		quickSort(elements, 0, elements.length - 1);
	}
}
