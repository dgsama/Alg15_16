package dynamic.alg;

public class RoadsInCity {

	int[][] map;
	char[][] visualMap;

	public RoadsInCity(int n1, int n2) {
		map = new int[n1][n2];
		visualMap = new char[n1][n2];
		visualMap = crearMapaVisual(n1, n2);

	}

	public void addBarrier(int b1, int b2) {
		map[b1][b2] = -1;
	}

	public long calculate(int x1, int y1, int x2, int y2) {
		if (!isPosible(x1, y1, x2, y2)) {
			return -1;
		}
		int result = 0;

		visualMap[x1][y1] = 's';
		visualMap[x2][y2] = 'f';
		show();
		showB();

		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				map[i][j] = calcularCasilla(i, j);
			}
		}
		result = map[x2][y2];
		if (result > 0) {
			return result;
		} else {
			return -1;
		}
	}

	private boolean isPosible(int x1, int y1, int x2, int y2) {
		int aux = map.length;
		int aux1 = map[0].length;
		
		/// MIRAR PARA CUANDO ESTA FUERA DE LOS LIMITES
		// prueba de que estoy haciendo git bien
		if (x1 < aux && x2 < aux && y1 < aux1 && y2 < aux1) {
			return false;
		} else if (x1 <= x2 || y1 >= y2) {
			return false;
		}
		return true;
	}

	public int calcularCasilla(int i, int j) {
		int result = 1;

		if (map[i - 1][j] > 0 && map[i][j - 1] > 0) {
			result = map[i - 1][j] + map[i][j - 1];
		} else if (map[i - 1][j] < 0 && map[i][j - 1] > 0) {
			result = map[i][j - 1];
		} else if (map[i - 1][j] > 0 && map[i][j - 1] < 0) {
			result = map[i - 1][j];
		}
		return result;
	}

	public char[][] crearMapaVisual(int a, int b) {
		char[][] aux = new char[a][b];
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				aux[i][j] = '-';
			}
		}
		return aux;
	}

	public void show() {

		System.out.print("THIS IS A MAP OF DIMENSIONS " + map.length + "x"
				+ map[0].length);
		for (int i = 0; i < map.length; i++) {
			System.out.println();
			for (int j = 0; j < map[0].length; j++) {

				System.out.print(map[i][j] + " ");
			}
		}
		System.out.println();
	}

	public void showB() {

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
