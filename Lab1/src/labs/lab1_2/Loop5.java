package labs.lab1_2;

import java.util.Random;

public class Loop5 {
	public static void loop5(int n) {
		/* O(n^3 logn) Algorithm */
		Random rn = new Random();
		@SuppressWarnings("unused")
		int cont = 0;
		for (int i = 1; i <= n; i++)
			for (int k = 1; k <= n; k++)
				for (int l = 1; l <= n; l++)
					for (int j = 1; j <= n; j *= 2)
						cont += rn.nextInt();
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);
		String result = "";

		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				loop5(n);
			}

			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**nTimes="
					+ nTimes);
			result += (t2 - t1) + '\n';
		} // for

		System.out.println(result);
	}// main

}
