package backtracking.alg;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class NonogramTest {

	@Test
	public void testA() throws IOException {
		Nonogram case1 = new Nonogram("case1.txt");

		case1.show();
		case1.calculate();
		case1.show();
		
		boolean[][] expectedResult = { { false, false, true, true, true }, { false, false, true, false, false },
				{ true, true, true, true, false }, { true, true, false, false, false },
				{ false, true, false, true, true } };

		for (int i = 0; i < expectedResult.length; i++) {
			for (int j = 0; j < expectedResult[0].length; j++) {
				assertEquals(expectedResult[i][j], case1.result[i][j]);
			}
		}
	}

}
