import java.util.HashSet;
import java.util.Set;

public class Day4 {

	public Day4() {
	}

	public int getPileValue(String pileOfScratchcards) {
		int sum = 0;

		String[] cards = pileOfScratchcards.trim().split("\n");

		for (String card : cards) {
			Set<Integer> winningNumbers = getWinningNumbers(card);
			int numWinners = getNumWinners(winningNumbers, card);
			int cardScore = getCardScore(numWinners);

			sum += cardScore;
		}

		return sum;
	}

	public Set<Integer> getWinningNumbers(String card) {
		Set<Integer> winningNums = new HashSet<>();

		String[] parts = card.split("\\|");
		String leftSide = parts[0].split(":")[1].trim();

		for (String number : leftSide.split("\\s+")) {
			winningNums.add(Integer.valueOf(number));
		}

		return winningNums;
	}

	public int getNumWinners(Set<Integer> winningNumbers, String card) {
		int numWinners = 0;

		String[] parts = card.split("\\|");
		String rightSide = parts[1].trim();

		for (String number : rightSide.split("\\s+")) {
			if (winningNumbers.contains(Integer.valueOf(number))) {
				numWinners++;
			}
		}

		return numWinners;
	}

	public int getCardScore(int numWinners) {
		return (int) Math.pow(2, numWinners - 1);
	}
}
