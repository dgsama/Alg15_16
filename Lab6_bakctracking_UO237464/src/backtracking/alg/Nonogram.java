package backtracking.alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Nonogram {

	public int[][] squares;
	public ArrayList<ArrayList<Integer>> rowsNumbers;
	public ArrayList<ArrayList<Integer>> columnsNumbers;
	private int size;

	private static final char black = 'x';
	private static final char white = '.';

	public static void main(String[] args) throws FileNotFoundException {
		Nonogram case1 = new Nonogram("case1.txt");

		case1.show();
	}

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
		squares = new int[getSize()][getSize()];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private void show() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				if (squares[i][j] == 0) {
					System.out.print((char) white + " ");
				} else {
					System.out.print((char) black + " ");
				}
			}
			System.out.println("");
		}
	}

	public ArrayList<ArrayList<Integer>> parseFile(String file)
			throws FileNotFoundException {
		FileReader f = new FileReader(file);
		BufferedReader br;

		ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> auxI = new ArrayList<Integer>();
		String[] auxS;
		try {
			br = new BufferedReader(f);
			setSize(Integer.parseInt(br.readLine()));

			while ((auxS = br.readLine().split("")) != null) {
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

}
