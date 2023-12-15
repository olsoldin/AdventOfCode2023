import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day3Test {

	private Day3 day3;

	@BeforeEach
	void setUp() {
		day3 = new Day3();
	}

	@Test
	void sumPartNumbers() {
		String engineSchematic = """
				467..114..
				...*......
				..35..633.
				......#...
				617*......
				.....+.58.
				..592.....
				......755.
				...$.*....
				.664.598..
				""";

		Assertions.assertEquals(4361, day3.sumPartNumbers(engineSchematic));
	}

	@Test
	void testShortSumPartNumbers() {
		String engineSchematic = """
				467..114..
				...*......
				100.100...
				...2......
				1........8
				""";

		Assertions.assertEquals(667, day3.sumPartNumbers(engineSchematic));
	}

	@Test
	void sumPartNumbersFull() throws IOException {
		String engineSchematic = Utils.readTextFile("./res/Day3EngineSchematic.txt");

		Assertions.assertEquals(509115, day3.sumPartNumbers(engineSchematic));
	}

	@Test
	void getNumberLocations() {
		String row1 = "467..114..";

		List<NumberLocation> row1Res = day3.getNumberLocations(row1);
		Assertions.assertEquals(0, row1Res.get(0).startIndex());
	}

	@Test
	void isSymbol() {
		String row1 = "467..114..";
		String row2 = "...*......";
		String row3 = "..35..633.";
		String row4 = "......#...";

		Assertions.assertFalse(day3.isSymbol(row1, 4));
		Assertions.assertTrue(day3.isSymbol(row2, 3));
		Assertions.assertFalse(day3.isSymbol(row2, 5));
		Assertions.assertFalse(day3.isSymbol(row3, 4));
		Assertions.assertTrue(day3.isSymbol(row4, 6));

		Assertions.assertFalse(day3.isSymbol("", 4));
	}
}