package branchAndBound.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import branchAndBound.util.BranchAndBound;
import branchAndBound.util.Node;

public class MySolution extends BranchAndBound {

	public Random random;

	/**
	 * Constructor for the problem using a file with the problem parameters
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public MySolution(String file) throws FileNotFoundException {
		int n = 0, k = 0, c = 0;
		int[] data = new int[3];

		parseFile(file, data);

		n = data[0];
		k = data[1];
		c = data[2];

		n = proveN(n, c);

		int[] v = fillV(n, c);

		Node node = new Selection(v, k, c);
		rootNode = node;
		rootNode.calculateHeuristicValue();
		branchAndBound(rootNode);
	}

	/**
	 * Constructor for the problem in which you have to pass the parameters of
	 * the problem
	 * 
	 * @param n
	 * @param k
	 * @param c
	 */
	public MySolution(int n, int k, int c) {
		n = proveN(n, c);
		int[] v = fillV(n, c);

		Node node = new Selection(v, k, c);
		rootNode = node;
		rootNode.calculateHeuristicValue();
		branchAndBound(rootNode);

	}

	private int proveN(int n, int c) {
		if (n > c) {
			return c;
		} else {
			return n;
		}

	}

	/**
	 * Method to fill the array of integers.
	 * 
	 * @param n
	 * @return full
	 */
	private int[] fillV(int n, int c) {
		int[] out = new int[n];

		/**
		 * THIS PART OF THE CODE IS FOR GENERATE A RANDOM VECTOR ArrayList
		 * <Integer> auxOut = new ArrayList<Integer>(); int aux = 0; random =
		 * new Random();
		 * 
		 * while (auxOut.size() < out.length) { aux = random.nextInt(c); if
		 * (aux>0 && auxOut.contains(aux) == false) { auxOut.add(aux); } }
		 * 
		 * for (int i = 0; i < out.length; i++) { out[i] = auxOut.get(i); }
		 * 
		 * out = sortArray(out);
		 */

		for (int i = 0; i < out.length; i++) {
			out[i] = i + 1;
		}
		return out;
	}

	/**
	 * Method to sort the random vector
	 * 
	 * @param out
	 * @return
	 */
	private int[] sortArray(int[] out) {
		int aux;
		for (int i = 0; i < out.length - 1; i++) {
			for (int j = i + 1; j < out.length; j++) {
				if (out[j] < out[i]) {
					aux = out[i];
					out[i] = out[j];
					out[j] = aux;
				}
			}
		}
		return out;
	}

	/**
	 * Method to read the problem from a .txt file
	 * 
	 * @param file
	 * @param data
	 * @throws FileNotFoundException
	 */
	private void parseFile(String file, int[] data)
			throws FileNotFoundException {
		FileReader f = new FileReader(file);
		BufferedReader br;

		try {
			br = new BufferedReader(f);
			String a = "";
			while ((a = br.readLine()) != null) {
				String[] aux = a.split(" ");
				for (int j = 0; j < aux.length; j++) {
					data[j] = Integer.parseInt(aux[j]);
				}
			}
			br.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/***************************************************/

/***************************************************/
class Selection extends Node {

	public int k; // Numbers of elements of the solution
	public int c; // Final sum of each vector solution
	public int[] numbers; // All the numbers to compute the solution vector
	public int n; // Size of the numbers array

	public boolean[] markedElements;

	private int sum; // Suma en cada paso

	public Selection(int[] v, int k, int c) {
		this.k = k;
		this.c = c;
		numbers = v;
		n = numbers.length;
		depth = 1;

		// log.debug("PROBLEM");
		String message = "Size = " + numbers.length
				+ ", Number of elements to do the sum = " + k
				+ " and final sum of the solution vector = " + c;
		// log.debug(message);
		System.out.println("PROBLEM\n");
		System.out.println(message);

		markedElements = new boolean[n];
	}

	public Selection(Selection parent, boolean[] markedElements, int value) {
		parentID = parent.getID();
		depth = parent.getDepth() + 1;
		k = parent.k;
		c = parent.c;
		n = parent.n;
		numbers = parent.numbers.clone();

		this.markedElements = markedElements;
		this.sum = parent.sum + value;
		calculateHeuristicValue();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("-----------\nVector:");
		for (int i = 0; i < numbers.length; i++) {
			sb.append(" " + numbers[i]);
		}
		sb.append("\nMarked:  ");
		for (int i = 0; i < markedElements.length; i++) {
			if (markedElements[i])
				sb.append(i + 1 + ", ");
		}
		sb.append("\nDepth = " + depth + "\n");
		sb.append("\nHeuristic value = " + heuristicValue + "\n");
		sb.append("-----------\n");
		return sb.toString();
	}

	@Override
	public void calculateHeuristicValue() {
		int options = 0;
		if (sum > c || depth > k || (c - sum == 0 && depth != k)) {
			heuristicValue = Integer.MAX_VALUE;
		} else if (c == sum && depth == k) {
			heuristicValue = Integer.MIN_VALUE;
		} else {
			// options = possibleOptions() + 1;
			heuristicValue = (c - sum);// / options;
		}
	}

	/**
	 * Method to know how many numbers we can use at this moment of the
	 * computation
	 * 
	 * @return int
	 */
	private int possibleOptions() {
		int counter = 0;
		for (int i = 0; i < markedElements.length; i++) {
			if (markedElements[i] == false) {
				if (sum + numbers[i] <= c) {
					counter++;
				} else {
					break;
				}
			}
		}
		return counter;
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<>();

		boolean[] markNotTakingIt = this.markedElements.clone();
		Selection vector1 = new Selection(this, markNotTakingIt, 0);

		boolean[] markTakingIt = this.markedElements.clone();
		markTakingIt[depth - 1] = true;
		Selection vector2 = new Selection(this, markTakingIt,
				numbers[depth - 1]);

		result.add(vector1);
		result.add(vector2);

		return result;
	}

	@Override
	public int initialValuePruneLimit() {
		return c + 1;
	}

	@Override
	public boolean isSolution() {
		return depth == k && sum == c;
	}

}
