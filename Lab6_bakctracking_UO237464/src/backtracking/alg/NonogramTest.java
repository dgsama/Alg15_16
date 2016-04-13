package backtracking.alg;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class NonogramTest {

	@Test
	public void testA() throws FileNotFoundException {
		Nonogram case1 = new Nonogram("case1.txt");
		case1.calculate();
	
		boolean[][] expectedResult = { { false, false, true, true, true },
				{ false, false, true, false, false },
				{ true, true, true, true, false },
				{ true, true, false, false, false },
				{ false, true, false, true, true } };
		
		for (int i = 0; i < expectedResult.length; i++) {
			for (int j = 0; j < expectedResult[0].length; j++) {
				assertEquals(expectedResult[i][j], case1.squares[i][j]);
			}
		}

		case1.show();
	}

}
