package backtracking.alg;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PosibilitiesBacktracking {
	ArrayList<Integer> constraints;
	ArrayList<boolean[]> result;

	public PosibilitiesBacktracking(ArrayList<Integer> c) {
		constraints = c;
		result = new ArrayList<boolean[]>();
	}

	public void BackTracking(int index, boolean[] v) {
		if (index == v.length) {
			if (correct(v)) {
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
				BackTracking(index + 1, v);
				if (i == 0) {
					v[index] = false;
				} else {
					v[index] = true;
				}
			}
		}
	}

	private boolean correct(boolean[] v) {
		int posOfFirst = getFirstPos(v);
		if (posOfFirst != -1) {
			int counter = 0;
			boolean blankFounded = false;
			ArrayList<Integer> result = new ArrayList<Integer>();

			for (int i = posOfFirst; i < v.length; i++) {
				boolean value = v[i];
				if (value) {
					counter++;
					blankFounded = false;
					if (i == v.length - 1)
						result.add(counter);
				} else if (!value && !blankFounded) {
					result.add(counter);
					counter = 0;
					blankFounded = true;
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

	private int getFirstPos(boolean[] vector) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i]==true) {
				return i;
			}
		}
		return -1;
	}
}
