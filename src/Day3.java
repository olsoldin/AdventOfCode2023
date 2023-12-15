import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Day3 {
	public Day3() {

	}

	public int sumPartNumbers(@NotNull String engineSchematic) {
		int sum = 0;

		String[] rows = engineSchematic.trim().split("\n");

		String blanks = new String(new char[rows[0].length()]).replace('\0', ' ');

		for (int i = 0; i < rows.length; i++) {
			String row = rows[i].trim();

			// find the locations of any digits in the row
			List<NumberLocation> numberLocations = getNumberLocations(row);

			String rowBefore = blanks;
			if (i > 0) {
				rowBefore = rows[i - 1].trim();
			}
			String rowAfter = blanks;
			if (i < rows.length - 1) {
				rowAfter = rows[i + 1].trim();
			}

			for (NumberLocation numberLocation : numberLocations) {
				for (int j = numberLocation.startIndex(); j <= numberLocation.endIndex(); j++) {
					// Check north-west, west and south-west
					if (j > 0) {
						if (isSymbol(rowBefore, j - 1)
								|| isSymbol(row, j - 1)
								|| isSymbol(rowAfter, j - 1)) {
							sum += numberLocation.number();
							break;
						}
					}
					// North, middle, south
					if (isSymbol(rowBefore, j)
							|| isSymbol(row, j)
							|| isSymbol(rowAfter, j)) {
						sum += numberLocation.number();
						break;
					}
					if (j < row.length() - 1) {
						// North-east, east, south-east
						if (j < numberLocation.endIndex() + 1) {
							if (isSymbol(rowBefore, j + 1)
									|| isSymbol(row, j + 1)
									|| isSymbol(rowAfter, j + 1)) {
								sum += numberLocation.number();
								break;
							}
						}
					}
				}
			}
		}
		return sum;
	}

	public boolean isSymbol(@NotNull String haystack, int needle) {
		if (haystack.trim().isEmpty()) {
			return false;
		}
		return !Character.isDigit(haystack.charAt(needle)) && haystack.charAt(needle) != '.';
	}

	public List<NumberLocation> getNumberLocations(@NotNull String row) {
		List<NumberLocation> numberLocations = new ArrayList<>();
		StringBuilder currNum = new StringBuilder();

		int beginIndex = -1;
		int endIndex = -1;
		for (int j = 0; j < row.length(); j++) {
			char ch = row.charAt(j);

			if (Character.isDigit(ch)) {
				currNum.append(ch);
			}

			if (Character.isDigit(ch) && beginIndex == -1) {
				beginIndex = j;
			} else if (Character.isDigit(ch) && beginIndex >= 0) {
				endIndex = j;
			}

			boolean isAtEndOfRow = j + 1 == row.length();
			boolean isNextDigitChar = !isAtEndOfRow && Character.isDigit(row.charAt(j + 1));
			if (beginIndex >= 0 && endIndex == -1 && !isNextDigitChar) {
				endIndex = beginIndex;
			}
			if (beginIndex >= 0 && endIndex >= 0 && (isAtEndOfRow || !isNextDigitChar)) {
				numberLocations.add(new NumberLocation(Integer.parseInt(currNum.toString()), beginIndex, endIndex));
				currNum = new StringBuilder();
				beginIndex = -1;
				endIndex = -1;
			}
		}

		return numberLocations;
	}
}
