package recursiveUPLOAD;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

/* This program can be used to sort n elements with 
 * the best algorithm. It is the QUICKSORT */

public class QuicksortCentralElement_Parallel extends RecursiveAction {
	
	private static final long serialVersionUID = 1L;
	static int[] v;
	int elements[];
	int left;
	int right;

	public QuicksortCentralElement_Parallel(int elements[], int left, int right) {
		this.left = left;
		this.right = right;
		this.elements = elements;
	}

	public static void main(String arg[]) {
		int nTimes = Integer.parseInt(arg[0]);
		long t1 = 0, t2 = 0;
		
		String result = "";

		for (int n = 10000; n <= 65536001; n *= 2) {
			v = new int[n];
			Vector.sorted(v);
			t1 = System.currentTimeMillis();
			
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				QuicksortCentralElement_Parallel a = new QuicksortCentralElement_Parallel(v, 0,
						v.length - 1);
				ForkJoinPool pool = new ForkJoinPool();
				pool.invoke(a);
			}
			
			t2 = System.currentTimeMillis();
			
			
			result = result + (t2-t1)+"\n";
			
			System.out.println("SORTED: TIME=" + (t2 - t1) + " **nTimes=" + nTimes);
		}
	}

	@Override
	protected void compute() {
		int i = left;
		int j = right - 1;
		int pivot;
		if (left < right) { // if there is one element it is not necessary
			int center = (left + right) / 2;
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
			// set position of the pivot
			Util.interchange(elements, i, right);

			QuicksortCentralElement_Parallel a = new QuicksortCentralElement_Parallel(elements,
					left, i - 1);
			QuicksortCentralElement_Parallel b = new QuicksortCentralElement_Parallel(elements,
					i + 1, right);
			invokeAll(a, b);
		}
	}
}
