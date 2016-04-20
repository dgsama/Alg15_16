package backtracking.alg;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class NonogramTest {

	@Test
	public void testA() throws IOException {
		Nonogram case1 = new Nonogram("case1.txt");

		case1.calculate();
		
		boolean[][] expectedResult = { { false, false, true, true, true }, { false, false, true, false, false },
				{ true, true, true, true, false }, { true, true, false, false, false },
				{ false, true, false, true, true } };

		for (int i = 0; i < expectedResult.length; i++) {
			for (int j = 0; j < expectedResult[0].length; j++) {
				assertEquals(expectedResult[i][j], case1.result[i][j]);
			}
		}
	}
	
	@Test
	public void TestB() throws FileNotFoundException {
		Nonogram c = new Nonogram("case2.txt");
		c.calculate();
	}
	
	@Test
	public void TestC() throws FileNotFoundException {
		Nonogram c = new Nonogram("case2.txt");
		c.calculate();
	}

	
	@Test
	public void TestD() throws FileNotFoundException {
		Nonogram c = new Nonogram("case2.txt");
		c.calculate();
	}

	
	@Test
	public void TestE() throws FileNotFoundException {
		Nonogram c = new Nonogram("case2.txt");
		c.calculate();
	}


}
