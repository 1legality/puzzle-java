import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ca.etsmtl.pegsolitaire.Helpers;

import java.io.Reader;
import java.io.StringReader;

public class UnitTestHelpers {

	private static Helpers        helpers     = null;
	private static Reader         mockInput   = null;
	private static int[][]        puzzleArray = null;
	private static String         puzzle      = "0011100\n" +
									            "0011100\n" +
			                                    "1111111\n" +
			                                    "1111111\n" +
			                                    "1111111\n" +
			                                    "0011100\n" +
			                                    "0011100";

	@BeforeClass
	public static void setup() {
		mockInput = new StringReader(puzzle);
		helpers   = new Helpers();

		puzzleArray = helpers.readPuzzleFile(mockInput);
	}

	@Test
	public void TestLine1() {
		assertArrayEquals(puzzleArray[0], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void TestLine2() {
		assertArrayEquals(puzzleArray[1], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void TestLine3() {
		assertArrayEquals(puzzleArray[2], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void TestLine4() {
		assertArrayEquals(puzzleArray[3], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void TestLine5() {
		assertArrayEquals(puzzleArray[4], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void TestLine6() {
		assertArrayEquals(puzzleArray[5], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void TestLine7() {
		assertArrayEquals(puzzleArray[6], new int[] {0,0,1,1,1,0,0});
	}
}