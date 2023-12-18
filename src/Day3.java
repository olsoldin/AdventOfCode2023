import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {
	public Day3() {

	}

	public int sumPartNumbers(@NotNull String engineSchematic) {
		int sum = 0;

		String[] rows = engineSchematic.trim().split("\n");

		for (int i = 0; i < rows.length; i++) {
			String row = rows[i].trim();

			// find the locations of any digits in the row
			List<NumberLocation> numberLocations = getNumberLocations(row);

			String rowBefore = "";
			if (i > 0) {
				rowBefore = rows[i - 1].trim();
			}
			String rowAfter = "";
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

	public int sumGearRatios(String engineSchematic) {
		int sum = 0;

		String[] rows = engineSchematic.trim().split("\n");

		// eg.
		// rowNum: [ {number: 457, startIndex: 4, endIndex: 6}, {number: 555, startIndex: 7, endIndex: 9} ]
		Map<Integer, List<NumberLocation>> numLocs = new HashMap<>();
		// eg.
		// rowNum: [ gear1Index, gear2Index, gear3Index ]
		Map<Integer, List<Integer>> gearLocs = new HashMap<>();

		for (int i = 0; i < rows.length; i++) {
			String row = rows[i].trim();

			// find the locations of any digits in the row
			numLocs.put(i, getNumberLocations(row));
			// find the locations of any gears in the row
			gearLocs.put(i, getGearLocations(row));
		}

		for (int i = 0; i < rows.length; i++) {
			List<NumberLocation> rowAbove = new ArrayList<>(0);
			// The first row doesn't have anything above it
			if (i > 0) {
				rowAbove = numLocs.get(i - 1);
			}
			List<NumberLocation> row = numLocs.get(i);
			List<NumberLocation> rowBelow = new ArrayList<>(0);
			// The last row doesn't have anything below it
			if (i < rows.length - 1) {
				rowBelow = numLocs.get(i + 1);
			}

			for (Integer gearLoc : gearLocs.get(i)) {
				List<Integer> adjacentGears = getAdjacentGears(gearLoc, rowAbove, row, rowBelow);

				// We don't want to accidentally multiply by 0,
				// And we don't want to accidentally add 1
				int gearRatio = adjacentGears.isEmpty() ? 0 : 1;
				for (int gear : adjacentGears) {
					gearRatio *= gear;
				}
				sum += gearRatio;
			}

		}

		return sum;
	}

	public List<Integer> getAdjacentGears(int gearLoc, List<NumberLocation> rowAbove, List<NumberLocation> row, List<NumberLocation> rowBelow) {
		List<Integer> adjacentGears = new ArrayList<>();

		List<NumberLocation> allLocs = new ArrayList<>(rowAbove.size() + row.size() + rowBelow.size());
		allLocs.addAll(rowAbove);
		allLocs.addAll(row);
		allLocs.addAll(rowBelow);

		for (NumberLocation loc : allLocs) {
			if (locIntersects(gearLoc, loc)) {
				adjacentGears.add(loc.number());
				if (adjacentGears.size() > 2) {
					// no need to keep going, we know it's too many
					break;
				}
			}
		}

		if (adjacentGears.size() == 2) {
			return adjacentGears;
		}
		return List.of();
	}

	public boolean locIntersects(int gearLoc, NumberLocation numberLocation) {
		return gearLoc >= numberLocation.startIndex() - 1 && gearLoc <= numberLocation.endIndex() + 1;
	}

	public List<Integer> getGearLocations(String row) {
		List<Integer> gearLocs = new ArrayList<>();

		for (int i = 0; i < row.length(); i++) {
			if (isGear(row.charAt(i))) {
				gearLocs.add(i);
			}
		}

		return gearLocs;
	}

	public boolean isGear(char input) {
		return input == '*';
	}

	public int getGearRatio(int gear1, int gear2) {
		return gear1 * gear2;
	}
}
