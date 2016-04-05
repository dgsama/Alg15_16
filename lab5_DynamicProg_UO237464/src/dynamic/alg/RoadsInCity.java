package dynamic.alg;

public class RoadsInCity {

	long[][] map;
	char[][] visualMap;

	public RoadsInCity(int n1, int n2) {
		map = new long[n1][n2];
		visualMap = new char[n1][n2];
		visualMap = crearMapaVisual(n1, n2);

	}

	public void addBarrier(int b1, int b2) {
		map[b1][b2] = -1;
		visualMap[b1][b2] = 'x';
	}

	private void fillInitialMap(int si, int sj, int ei, int ej) {
		map[si][sj] = 1;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i > si || i < ei) {
					map[i][j] = -1;
				}
				if (j < sj || j > ej) {
					map[i][j] = -1;
				}
			}
		}
	}

	public long calculate(int si, int sj, int ei, int ej) {
		long result = 0;
		// Look if we can reach the end point.
		if (!isPosible(si, sj, ei, ej)) {
			System.err.println("Something is invalid in this map!!");
			return -1;
		}
		// Complete the initial maps.
		fillInitialMap(si, sj, ei, ej);
		visualMap[si][sj] = 's';
		visualMap[ei][ej] = 'f';

		// Print the maps in the console.
		show();
		showGraphicMap();

		// Calculate the values of the number of ways to all the reachable
		// nodes.
		for (int i = si; i >= ei; i--) {
			for (int j = sj; j <= ej; j++) {
				map[i][j] = calcularCasilla(i, j);
				if (i == ei && j == ej) {
					break;
				}
			}
		}
		show();

		// Assign to the local variable the value of the ways to arrive to the
		// end point and return it.
		result = map[ei][ej];
		if (result >= 0) {
			return result;
		} else {
			return -1;
		}
	}

	private long calcularCasilla(int i, int j) {
		long aux = -1;
		if (map[i][j] >= 0) {
			if (i + 1 < map.length && j - 1 >= 0) {
				// Both possible.
				if (map[i][j - 1] >= 0 && map[i + 1][j] >= 0) {
					aux = map[i][j - 1] + map[i + 1][j];
				}
				// Only left node possible.
				else if (map[i][j - 1] >= 0 && map[i + 1][j] < 0) {
					aux = map[i][j - 1];
				}
				// Only down node possible.
				else if (map[i][j - 1] < 0 && map[i + 1][j] >= 0) {
					aux = map[i + 1][j];
				} else {
					aux = map[i][j];
				}
			} else if (i + 1 < map.length && j - 1 < 0) {// LEFT OUT OF BOUNDS
				if (map[i + 1][j] > 0) {
					aux = map[i + 1][j];
				} else {
					aux = map[i][j];
				}

			} else if (i + 1 >= map.length && j - 1 >= 0) {// DOWN OUT OF BONDS
				if (map[i][j - 1] > 0) {
					aux = map[i][j - 1];
				} else {
					aux = map[i][j];
				}

			} else {
				aux = map[i][j];
			}
		}

		return aux;
	}

	private boolean isPosible(int si, int sj, int ei, int ej) {
		if (map.length < 1 && map[0].length < 1) {
			return false;
		} else if (si == map.length || ei == map.length || sj == map[0].length || ej == map[0].length) {
			return false;
		} else if (si <= ei && sj >= ej) {
			return false;
		} else {
			return true;
		}
	}

	private char[][] crearMapaVisual(int a, int b) {
		char[][] aux = new char[a][b];
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				aux[i][j] = '-';
			}
		}
		return aux;
	}

	private void show() {

		System.out.print("THIS IS A MAP OF DIMENSIONS " + map.length + "x" + map[0].length);
		for (int i = 0; i < map.length; i++) {
			System.out.println();
			for (int j = 0; j < map[0].length; j++) {

				System.out.print(map[i][j] + " ");
			}
		}
		System.out.println();
	}

	private void showGraphicMap() {

		for (int i = 0; i < visualMap.length; i++) {
			System.out.println();
			for (int j = 0; j < visualMap[0].length; j++) {
				System.out.print(visualMap[i][j] + " ");
			}
		}
		System.out.println();
		System.out.println();
	}

}
