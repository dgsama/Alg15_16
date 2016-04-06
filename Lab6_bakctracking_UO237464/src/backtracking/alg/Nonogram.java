package backtracking.alg;

public class Nonogram {

	public int[][] squares;

	private static final int black = 35;
	private static final int white = 79;

	public static void main(String[] args) {
		Nonogram a = new Nonogram(4, 4);
		a.squares[2][2] = 1;
		a.squares[0][0] = 1;
		a.squares[1][1] = 1;
		a.squares[3][3] = 1;

		a.show();
	}

	public Nonogram(int a, int b) {
		squares = new int[a][b];
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

}
