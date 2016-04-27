package branchAndBound.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import branchAndBound.util.BranchAndBound;
import branchAndBound.util.Node;

public class MySolution extends BranchAndBound {

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

		int[] v = fillV(n);
		rootNode = new Selection(v, k, c);
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

		int[] v = fillV(n);
		rootNode = new Selection(v, k, c);
		
	}

	/**
	 * Method to fill the array of integers.
	 * 
	 * @param n
	 * @return full
	 */
	private int[] fillV(int n) {
		int[] out = new int[n];

		for (int i = 1; i < out.length; i++) {
			out[i] = i;
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
	private static Logger log = LoggerFactory.getLogger(Selection.class);

	public int k; // Numbers of elements of the solution
	public int c; // Final sum of each vector solution
	public int[] numbers; // All the numbers to compute the solution vector
	public int n; // Size of the numbers array

	public boolean[] mark;
	public int[] partialSolution;

	private int sum; //Suma en cada paso

	public Selection(int[] v, int k, int c) {
		this.k = k;
		this.c = c;
		numbers = v;
		n = numbers.length;

		log.debug("PROBLEM");
		String message = "Size = " + numbers.length
				+ ", Number of elements to do the sum = " + k
				+ " and final sum of the solution vector = " + c;
		log.debug(message);

		partialSolution = new int[n];
		for (int i = 0; i < partialSolution.length; i++) {
			partialSolution[i] = -1; // Initially, no assignments
		}
		mark = new boolean[n];
		for (int i = 0; i < mark.length; i++) {
			mark[i] = false; // Initially, no assignments
		}
	}

	public Selection(Selection parent, int j) {

	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void calculateHeuristicValue() {

	}

	@Override
	public ArrayList<Node> expand() {
		return null;
	}

	@Override
	public boolean isSolution() {
		 return depth == k && sum == c;
	}

}
