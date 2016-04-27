package branchAndBound.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

		if (n >= k) {
			n = k;
		}

		int[] v = fillV(n);
		rootNode = new Selection(v, k, c);
	}

	/**
	 * Constructor for the problem in which you have to pass the parameters of
	 * the problem
	 * 
	 * @param n
	 *            size of vector
	 * @param k
	 *            number of elements for result
	 * @param c
	 *            final amount
	 */
	public MySolution(int n, int k, int c) {
		
		if (n >= k) {
			n = k;
		}

		int[] v = fillV(n);

		rootNode = new Selection(v, k, c);
	}

	/**
	 * Method to fill the array of integers.
	 * 
	 * @param n
	 *            = size
	 * @return full integer array
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
	 *            = name of the file
	 * @param data
	 *            = vector of data
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

	public Selection(int[] v, int k, int c) {

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("===============\n");
		/*
		 * for (int i=0; i<partialSolution.length; i++) { if (partialSolution[i]
		 * != -1) sb.append("THE TASK " + partialSolution[i] +
		 * " IS ASSIGNED TO THE WORKER " + i + "\n"); else
		 * sb.append("THE TASK FOR WORKER " + i + " IS NOT ASSIGNED YET \n"); }
		 * sb.append("Heuristic value = " + heuristicValue + "\n");
		 */
		sb.append("===============\n");
		return sb.toString();
	}

	@Override
	public void calculateHeuristicValue() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Node> expand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSolution() {
		// TODO Auto-generated method stub
		return false;
	}

}
