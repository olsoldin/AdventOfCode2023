import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
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

	@Test
	void sumGearRatios() {
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

		Assertions.assertEquals(467835, day3.sumGearRatios(engineSchematic));
	}

	@Test
	void sumGearRatiosFull() throws IOException {
		String engineSchematic = Utils.readTextFile("./res/Day3EngineSchematic.txt");

		Assertions.assertEquals(75220503, day3.sumGearRatios(engineSchematic));
	}

	@Test
	void isGear() {
		Assertions.assertTrue(day3.isGear('*'));
		Assertions.assertFalse(day3.isGear('.'));
		Assertions.assertFalse(day3.isGear('4'));
	}

	@Test
	void getGearRatio() {
		Assertions.assertEquals(6, day3.getGearRatio(3, 2));
		Assertions.assertEquals(100, day3.getGearRatio(25, 4));
		Assertions.assertEquals(256, day3.getGearRatio(2, 128));
	}

	@Test
	void getGearLocations() {
		String row1 = "467..114..";
		String row2 = "...*......";
		String row3 = "..35..633.";
		String row4 = "...*..*...";


		Assertions.assertIterableEquals(List.of(), day3.getGearLocations(row1));
		Assertions.assertIterableEquals(List.of(3), day3.getGearLocations(row2));
		Assertions.assertIterableEquals(List.of(), day3.getGearLocations(row3));
		Assertions.assertIterableEquals(Arrays.asList(3, 6), day3.getGearLocations(row4));
	}

	@Test
	void getAdjacentGears() {
		String row1 = "467..114..";
		String row2 = "...*......";
		String row3 = "..35..633.";

		int gearLoc = day3.getGearLocations(row2).get(0);
		List<NumberLocation> rowAbove = day3.getNumberLocations(row1);
		List<NumberLocation> row = day3.getNumberLocations(row2);
		List<NumberLocation> rowBelow = day3.getNumberLocations(row3);

		Assertions.assertIterableEquals(Arrays.asList(467, 35), day3.getAdjacentGears(gearLoc, rowAbove, row, rowBelow));
	}

	@Test
	void locIntersects() {
		List<NumberLocation> row = day3.getNumberLocations("467..114..");

		Assertions.assertTrue(day3.locIntersects(0, row.get(0)));
		Assertions.assertTrue(day3.locIntersects(1, row.get(0)));
		Assertions.assertTrue(day3.locIntersects(2, row.get(0)));
		Assertions.assertTrue(day3.locIntersects(3, row.get(0)));
		Assertions.assertFalse(day3.locIntersects(4, row.get(0)));

		Assertions.assertFalse(day3.locIntersects(3, row.get(1)));
		Assertions.assertTrue(day3.locIntersects(4, row.get(1)));
		Assertions.assertTrue(day3.locIntersects(5, row.get(1)));
		Assertions.assertTrue(day3.locIntersects(6, row.get(1)));
		Assertions.assertTrue(day3.locIntersects(7, row.get(1)));
		Assertions.assertTrue(day3.locIntersects(8, row.get(1)));
		Assertions.assertFalse(day3.locIntersects(9, row.get(1)));
	}
}