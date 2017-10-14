import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ca.etsmtl.pegsolitaire.FileLoader;

import java.io.Reader;
import java.io.StringReader;

public class TestUnitLoader {

	private static FileLoader     loader       = null;
	private static int            numberOfPegs = 0;
	private static Reader         mockInput    = null;
	private static int[][]        puzzleArray  = null;
	private static String         puzzle       = "0011100\n" +
									             "0011100\n" +
			                                     "1111111\n" +
			                                     "1111111\n" +
			                                     "1111111\n" +
			                                     "0011100\n" +
			                                     "0011100";


	@BeforeClass
	public static void setup() {
		mockInput    = new StringReader(puzzle);
		loader       = new FileLoader();

		puzzleArray  = loader.readPuzzleFile(mockInput);
		numberOfPegs = loader.getNumberOfPegs();
 	}

	@Test
	public void testLine1() {
		assertArrayEquals(puzzleArray[0], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void testLine2() {
		assertArrayEquals(puzzleArray[1], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void testLine3() {
		assertArrayEquals(puzzleArray[2], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void testLine4() {
		assertArrayEquals(puzzleArray[3], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void testLine5() {
		assertArrayEquals(puzzleArray[4], new int[] {1,1,1,1,1,1,1});
	}

	@Test
	public void testLine6() {
		assertArrayEquals(puzzleArray[5], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void testLine7() {
		assertArrayEquals(puzzleArray[6], new int[] {0,0,1,1,1,0,0});
	}

	@Test
	public void testNumberOfPegs() {
		assertEquals(33, loader.getNumberOfPegs());
	}
}