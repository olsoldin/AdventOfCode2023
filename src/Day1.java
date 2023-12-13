public class Day1 {

	/**
	 * On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.
	 * <p>
	 * For example:
	 * <p>
	 * 1abc2
	 * pqr3stu8vwx
	 * a1b2c3d4e5f
	 * treb7uchet
	 * <p>
	 * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
	 * <p>
	 * Consider your entire calibration document. What is the sum of all of the calibration values?
	 */
	public static int calibrationSum(String calibrationDocument) {
		int sum = 0;
		String[] lines = calibrationDocument.split("\n");

		for (String line : lines) {
			String firstNum = "";
			String lastNum = "";

			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (Character.isDigit(c)) {
					firstNum = String.valueOf(c);
					break;
				}
			}

			for (int i = line.length() - 1; i >= 0; i--) {
				char c = line.charAt(i);
				if (Character.isDigit(c)) {
					lastNum = String.valueOf(c);
					break;
				}
			}

			Logger.log(line.trim() + " --> " + firstNum + lastNum);
			sum += Integer.parseInt(firstNum + lastNum);
		}

		Logger.log("Sum = " + sum);
		Logger.log();

		return sum;
	}

	/**
	 * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
	 * <p>
	 * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
	 * <p>
	 * two1nine
	 * eightwothree
	 * abcone2threexyz
	 * xtwone3four
	 * 4nineeightseven2
	 * zoneight234
	 * 7pqrstsixteen
	 * <p>
	 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
	 *
	 * @param calibrationDocument
	 *
	 * @return
	 */
	public static int calibrationSumPart2(String calibrationDocument) {
		int sum = 0;

		String[] lines = calibrationDocument.split("\n");

		for (String line : lines) {
			String firstNum = "";
			String lastNum = "";

			for (int i = 0; i < line.length(); i++) {
				int startsWith = Utils.startsWithTextInt(line.substring(i));
				if (startsWith >= 0) {
					firstNum = String.valueOf(startsWith);
					break;
				}

				char c = line.charAt(i);
				if (Character.isDigit(c)) {
					firstNum = String.valueOf(c);
					break;
				}
			}

			for (int i = line.length() - 1; i >= 0; i--) {
				int endsWith = Utils.endsWithTextInt(line.substring(0, i + 1));
				if (endsWith >= 0) {
					lastNum = String.valueOf(endsWith);
					break;
				}

				char c = line.charAt(i);
				if (Character.isDigit(c)) {
					lastNum = String.valueOf(c);
					break;
				}
			}

			Logger.log(line.trim() + " --> " + firstNum + lastNum);
			sum += Integer.parseInt(firstNum + lastNum);
		}

		Logger.log("Sum = " + sum);
		Logger.log();

		return sum;
	}
}
