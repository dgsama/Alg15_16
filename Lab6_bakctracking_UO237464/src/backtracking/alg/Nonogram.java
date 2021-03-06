package backtracking.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Nonogram {

	public boolean[][] squares;
	public boolean[][] squaresSolution;
	public static ArrayList<ArrayList<Integer>> rowsNumbers;
	public static ArrayList<ArrayList<Integer>> columnsNumbers;

	private ArrayList<Integer> constraints;
	private ArrayList<boolean[]> result;

	private static int size;
	private static boolean found = false;

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
		squaresSolution = new boolean[getSize()][getSize()];
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
	protected void show(boolean[][] aux) {
		System.out.println("Nonogram of dimension " + getSize());
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {
				if (aux[i][j] == false) {
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
				auxI = new ArrayList<Integer>();
				for (int j = 0; j < auxS.length; j++) {
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
		backtracking(0);
	}

	private void backtracking(int row) {
		if (found == false) {
			if (row == getSize()) {
				if (validSolution() == true) {
					found = true;
					storeSolution(squares);
					show(squaresSolution);
				}
			} else {
				boolean[] tempRow = squares[row];
				boolean[][] posibilities = checkOptions(row);
				for (int i = 0; i < posibilities.length; i++) {
					squares[row] = posibilities[i];
					backtracking(row + 1);
					squares[row] = tempRow;
				}
			}
		}
	}

	private void storeSolution(boolean[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				squaresSolution[i][j] = s[i][j];
			}
		}

	}

	private boolean validSolution() {
		for (int i = 0; i < getSize(); i++) {
			if (columnsChecking(i) == false) {
				return false;
			}
		}
		return true;
	}

	private boolean columnsChecking(int colIndex) {
		ArrayList<Integer> columnConstraints = columnsNumbers.get(colIndex);
		int counter = 0;
		int indexFirstElement = getFirst(colIndex);

		if (columnConstraints.size() > 1) {

			if (indexFirstElement == -1) {
				return false;
			}

			int index = 0;
			boolean white = false;
			int[] aux = new int[columnConstraints.size()];

			for (int i = 0; i < squares.length && index < aux.length; i++) {
				boolean v = squares[i][colIndex];
				if (v == true) {
					counter++;
					white = false;
					if (i == (squares[colIndex].length - 1)) {
						aux[index] = counter;
						index++;
					}
				} else if (v == false && white == false) {
					aux[index] = counter;
					counter = 0;
					white = true;
					index++;
				}
			}

			for (int i = 0; i < columnConstraints.size(); i++) {
				if (columnConstraints.get(i) != aux[i]) {
					return false;
				}
			}
			return true;
		} else {

			if (indexFirstElement != -1) {
				for (int i = indexFirstElement; i < squares.length; i++) {
					if (squares[i][colIndex] == true) {
						counter++;
					} else {
						if (moreSquares(i, colIndex) == true) {
							return false;
						} else {
							break;
						}
					}
				}
				return (counter == columnConstraints.get(0));
			} else {
				return true;
			}
		}
	}

	private boolean moreSquares(int startIndex, int colIndex) {
		for (int i = startIndex; i < squares.length; i++) {
			if (squares[i][colIndex] == true) {
				return true;
			}
		}
		return false;
	}

	private int getFirst(int col) {
		for (int i = 0; i < squares.length; i++) {
			if (squares[i][col] == true) {
				return i;
			}
		}
		return -1;
	}

	public void ImprimirRestriccionesColumna() {

		for (int i = 0; i < columnsNumbers.size(); i++) {

			ArrayList<Integer> aux = columnsNumbers.get(i);

			for (int j = 0; j < aux.size(); j++) {
				System.out.print(aux.get(j) + ", ");
			}
			System.out.println();
		}

	}

	public void ImprimirRestriccionesFilas() {

		for (int i = 0; i < rowsNumbers.size(); i++) {

			ArrayList<Integer> aux = rowsNumbers.get(i);

			for (int j = 0; j < aux.size(); j++) {
				System.out.print(aux.get(j) + ", ");
			}
			System.out.println();
		}

	}

	private boolean[][] checkOptions(int row) {
		ArrayList<Integer> auxCons = rowsNumbers.get(row);
		boolean[][] aux;

		if (auxCons.size() > 1) {
			boolean[] r = new boolean[getSize()];
			PosibilitiesBacktracking p = new PosibilitiesBacktracking(auxCons);
			p.optionsBacktracking(0, r);
			aux = new boolean[p.result.size()][getSize()];
			for (int i = 0; i < aux.length; i++) {
				aux[i] = p.result.get(i);
			}
			return aux;
		} else {
			int c = auxCons.get(0);
			int p = squares.length + 1 - c;
			aux = new boolean[p][squares.length];
			for (int i = 0; i < p; i++) {
				for (int j = i; j < c + i; j++) {
					aux[i][j] = true;
				}
			}
			return aux;
		}
	}

	/**
	 * Auxiliary class for compute the possible solution for each row
	 * 
	 * @author David
	 *
	 */
	private class PosibilitiesBacktracking {
		ArrayList<Integer> constraints;
		ArrayList<boolean[]> result;

		public PosibilitiesBacktracking(ArrayList<Integer> c) {
			constraints = c;
			result = new ArrayList<boolean[]>();
		}

		public void optionsBacktracking(int index, boolean[] v) {
			if (index == v.length) {
				if (optionIsOk(v)) {
					boolean[] aux = new boolean[v.length];
					for (int i = 0; i < v.length; i++) {
						aux[i] = v[i];
					}
					result.add(aux);
				}
			} else {
				for (int i = 0; i < 2; i++) {
					if (i == 0) {
						v[index] = true;
					} else {
						v[index] = false;
					}
					optionsBacktracking(index + 1, v);
					if (i == 0) {
						v[index] = false;
					} else {
						v[index] = true;
					}
				}
			}
		}

		private boolean optionIsOk(boolean[] v) {
			int posOfFirst = getFirstPosOption(v);
			if (posOfFirst != -1) {
				int counter = 0;
				boolean white = false;
				ArrayList<Integer> result = new ArrayList<Integer>();

				for (int i = posOfFirst; i < v.length; i++) {
					boolean value = v[i];
					if (value == true) {
						counter++;
						white = false;
						if (i == v.length - 1) {
							result.add(counter);
						}
					} else if (value == false && white == false) {
						result.add(counter);
						counter = 0;
						white = true;
					}
				}
				// If the row is empty
				if (result.size() > constraints.size()) {
					return false;
				}

				while (constraints.size() > result.size()) {
					result.add(0);
				}

				for (int i = 0; i < constraints.size(); i++) {
					if (constraints.get(i) != result.get(i)) {
						return false;
					}
				}
				return true;

			} else if (constraints.get(0) == 0) {
				return true;
			} else {
				return false;
			}
		}

		private int getFirstPosOption(boolean[] vector) {
			for (int i = 0; i < vector.length; i++) {
				if (vector[i] == true) {
					return i;
				}
			}
			return -1;
		}
	}

}
