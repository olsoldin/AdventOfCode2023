import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day1Test {

	@Test
	void calibrationSum() {
		String calibrationDocument = """
				1abc2
				pqr3stu8vwx
				a1b2c3d4e5f
				treb7uchet
				""";
		int output = Day1.calibrationSum(calibrationDocument);

		Assertions.assertEquals(142, output);
	}

	@Test
	void calibrationSumFull() throws IOException {
		String calibrationDocument = Utils.readTextFile("./res/Day1CalibrationDocument.txt");

		int output = Day1.calibrationSum(calibrationDocument);

		Assertions.assertEquals(52974, output);
	}

	@Test
	void calibrationSumPart2() {
		String calibrationDocument = """
				two1nine
				eightwothree
				abcone2threexyz
				xtwone3four
				4nineeightseven2
				zoneight234
				7pqrstsixteen
				""";
		int output = Day1.calibrationSumPart2(calibrationDocument);

		Assertions.assertEquals(281, output);
	}

	@Test
	void calibrationSumPart2Part() {
		String calibrationDocument = "3gngzkpkgrf";

		int output1 = Day1.calibrationSum(calibrationDocument);
		int output2 = Day1.calibrationSumPart2(calibrationDocument);

		Assertions.assertEquals(output1, output2);
	}

	@Test
	void calibrationSumPart2Full() throws IOException {
		String calibrationDocument = Utils.readTextFile("./res/Day1CalibrationDocument.txt");

		int output = Day1.calibrationSumPart2(calibrationDocument);

		Assertions.assertEquals(53340, output);
	}
}