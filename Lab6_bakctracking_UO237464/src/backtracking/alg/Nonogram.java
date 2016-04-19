package backtracking.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Nonogram {

	public static boolean[][] squares;
	public static ArrayList<ArrayList<Integer>> rowsNumbers;
	public static ArrayList<ArrayList<Integer>> columnsNumbers;

	private static int size;
	private static final char black = 'x';
	private static final char white = '.';
	private static boolean found = false;

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
	public ArrayList<ArrayList<Integer>> parseFile(String file) throws FileNotFoundException {

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
		backtracking(0, 0);
	}

	private void backtracking(int x, int y) {

		if (isASolution(x)) {
			found = true;
			show();
		} else {
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					squares[x][y] = false;
				} else if (i == 1) {
					squares[x][y] = true;
				}

				if (restriccionesF(x) == false && restricionC(y) == false) {
					// Primer if restriccionesFilaContador
					// Segundo cambioFila
					// else
					if (cambioFila(x, y) && restriccionesFilaContador(x) == true) {// si
																					// pasa
																					// asegurarse
																					// de
																					// que
																					// no
																					// hay
																					// restriccion
																					// en
																					// toda
																					// la
																					// fila
						backtracking(x + 1, 0);
						squares[x][y] = false;
					} else if (cambioFila(x, y) && restriccionesFilaContador(x) == false) {
						squares[x][y] = false;
					} else {
						backtracking(x, y + 1);
						squares[x][y] = false;
					}

				}
			}
		}
	}

	private boolean restriccionesF(int row) {
		ArrayList<Integer> row_list = rowsNumbers.get(row);
		int cont = 0;
		int contglob = 0;
		int r = 0;
		int res = row_list.get(r);
		for (int i = 0; i < size; i++) {
			if (res != 0) {
				if (squares[row][i] == true) {
					cont = cont + 1;
					contglob = contglob + 1;
				} else {
					if (cont <= res) {
						cont = 0;
						r = r + 1;
						if (r < row_list.size())
							res = row_list.get(r);
					} else
						return true;
				}

			}
		}
		return false;
	}

	private boolean restricionC(int column) {
		ArrayList<Integer> col_list = columnsNumbers.get(column);
		int cont = 0;
		int r = 0;
		int res = col_list.get(r);
		for (int i = 0; i < size; i++) {
			if (res != 0) {
				if (squares[i][column] == true) {
					cont = cont + 1;
				} else {
					if (cont <= res) {
						cont = 0;
						r = r + 1;
						if (r < col_list.size())
							res = col_list.get(r);
					} else
						return true;
				}
			}
		}
		return false;

	}

	private boolean cambioFila(int x, int y) {
		y = y + 1;
		if (y == size)
			return true;
		return false;
	}

	private boolean restriccionesFilaContador(int fila) {
		ArrayList<Integer> r_list = rowsNumbers.get(fila);
		int contres = 0;
		int contnono = 0;
		int res;
		for (int i = 0; i < r_list.size(); i++) {
			res = r_list.get(i);
			if (res != 0)
				contres += res;
		}
		for (int i = 0; i < size; i++) {
			if (squares[fila][i] == true)
				contnono += 1;
		}
		if (contnono == contres)
			return true;
		return false;
	}

	private boolean isASolution(int x) {
		if (x == size) {
			return true;
		}
		return false;
	}

	public void imprimirCondicionesFilas() {
		ArrayList<ArrayList<Integer>> aux = rowsNumbers;

		System.out.println("Filas");
		for (int i = 0; i < aux.size(); i++) {
			ArrayList<Integer> aux1 = aux.get(i);
			for (int j = 0; j < aux1.size(); j++) {
				System.out.print(aux1.get(j) + ", ");
			}
			System.out.println("");
		}

	}
	
	public void imprimirCondicionescolumnas() {
		ArrayList<ArrayList<Integer>> aux = columnsNumbers;
		
		System.out.println("columnas");
		for (int i = 0; i < aux.size(); i++) {
			ArrayList<Integer> aux1 = aux.get(i);
			for (int j = 0; j < aux1.size(); j++) {
				System.out.print(aux1.get(j) + ", ");
			}
			System.out.println("");
		}

	}

}
