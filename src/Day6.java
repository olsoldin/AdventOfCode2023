import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

	public Day6() {
	}

	public Long numWaysToBeatRecord(@NotNull String records) {
		String[] lines = records.split("\n");
		List<Integer> times = Utils.extractIntegers(lines[0]);
		List<Integer> dists = Utils.extractIntegers(lines[1]);

		List<Long> numWaysToBeatRecord = new ArrayList<>();
		for (int i = 0; i < times.size(); i++) {
			Integer winningDist = dists.get(i);

			long betterThanWinning = calculatePossibleDistances(times.get(i)).stream()
					.filter(dist -> winningDist < dist)
					.count();

			if (betterThanWinning > 0) {
				numWaysToBeatRecord.add(betterThanWinning);
			}
		}

		return numWaysToBeatRecord.stream()
				.reduce((a, b) -> a * b)
				.orElse(null);
	}

	public List<Integer> calculatePossibleDistances(Integer time) {
		List<Integer> distances = new ArrayList<>();

		for (int btnPressed = 0; btnPressed <= time; btnPressed++) {
			int timeMoving = time - btnPressed;

			// time the button is pressed == the speed
			distances.add(btnPressed * timeMoving);
		}

		return distances;
	}

	public long numWaysToBeatRecordPart2(@NotNull String record) {
		String[] lines = record.split("\n");
		BigInteger time = Utils.extractBigIntegerAsOne(lines[0]);
		BigInteger dist = Utils.extractBigIntegerAsOne(lines[1]);

		long numWinningWays = 0;

		for (BigInteger btnPressed = BigInteger.ZERO; btnPressed.compareTo(time) <= 0; btnPressed = btnPressed.add(BigInteger.ONE)) {
			assert time != null;
			BigInteger timeMoving = time.subtract(btnPressed);

			// time the button is pressed == the speed
			BigInteger distance = btnPressed.multiply(timeMoving);

			if (distance.compareTo(dist) > 0) {
				numWinningWays++;
			}
		}

		return numWinningWays;
	}
}
