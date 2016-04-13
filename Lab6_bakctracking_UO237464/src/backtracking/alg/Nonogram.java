package backtracking.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Nonogram {

	public static boolean[][] squares;
	public ArrayList<ArrayList<Integer>> rowsNumbers;
	public ArrayList<ArrayList<Integer>> columnsNumbers;

	private int size;
	private static final char black = 'x';
	private static final char white = '.';

	/**
	 * Constructor for the class Nonogram
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public Nonogram(String file) throws FileNotFoundException {
		rowsNumbers = new ArrayList<ArrayList<Integer>>();
		columnsNumbers = new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> aux = parseFile(file);

		for (int i = 0; i < aux.size(); i++) {
			if (i < getSize()) {
				rowsNumbers.add(aux.get(i));
			} else {
				columnsNumbers.add(aux.get(i));
			}
		}
		squares = new boolean[getSize()][getSize()];
	}

	// Getter and setter for attribute size
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Method to show the matrix of the nonogram
	 */
	protected void show() {
		System.out.println("Nonogram of dimension " + getSize());
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				if (squares[i][j] == false) {
					System.out.print((char) white + " ");
				} else {
					System.out.print((char) black + " ");
				}
			}
			System.out.println("");
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * Method to read the files with the information for every case.
	 * 
	 * @param file
	 * @return ArrayList of ArrayList of integers with the conditions for the
	 *         problem.
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<Integer>> parseFile(String file)
			throws FileNotFoundException {

		FileReader f = new FileReader(file);
		BufferedReader br;

		ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> auxI = null;
		String[] auxS;

		try {
			br = new BufferedReader(f);
			setSize(Integer.parseInt(br.readLine()));
			String a = "";
			while ((a = br.readLine()) != null) {
				auxS = a.split(" ");
				for (int j = 0; j < auxS.length; j++) {
					auxI = new ArrayList<Integer>();
					auxI.add(Integer.parseInt(auxS[j]));
				}
				lines.add(auxI);
			}
			br.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public void calculate() {
		boolean[][] result = squares;
		recCalculate(result);
		squares = result;
	}

	private void recCalculate(boolean[][] result) {

	}
}
