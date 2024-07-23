import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day4 {

	public Day4() {
	}

	public int getPileValue(@NotNull String pileOfScratchcards) {
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

	public int getNumScratchcards(@NotNull String pileOfScratchcards) {
		Map<Integer, Scratchcard> originalCards = new HashMap<>();

		String[] cards = pileOfScratchcards.trim().split("\n");

		for (String card : cards) {
			String cardNumber = card.split(":")[0].replaceAll("[^0-9]", "");
			int cardNum = Integer.parseInt(cardNumber);
			Set<Integer> winningNumbers = getWinningNumbers(card);
			int numWinners = getNumWinners(winningNumbers, card);

			originalCards.put(cardNum, new Scratchcard(cardNum, winningNumbers, numWinners));
		}

		return processCards(0, originalCards, originalCards.values());
	}

	public int processCards(int numProcessed, @NotNull Map<Integer, Scratchcard> originalCards, @NotNull Collection<Scratchcard> cardsToProcess) {
		// Let's exit once there's nothing left to process
		if (cardsToProcess.isEmpty()) {
			return numProcessed;
		}

		List<Scratchcard> newCards = new ArrayList<>();

		for (Scratchcard card : cardsToProcess) {
			numProcessed++;

			for (int i = card.getCardNumber() + 1; i < card.getCardNumber() + card.getNumWinners() + 1; i++) {
				if (originalCards.containsKey(i)) {
					newCards.add(originalCards.get(i));
				}
			}
		}

		return processCards(numProcessed, originalCards, newCards);
	}


	public @NotNull Set<Integer> getWinningNumbers(@NotNull String card) {
		Set<Integer> winningNums = new HashSet<>();

		String[] parts = card.split("\\|");
		String leftSide = parts[0].split(":")[1].trim();

		for (String number : leftSide.split("\\s+")) {
			winningNums.add(Integer.valueOf(number));
		}

		return winningNums;
	}

	public int getNumWinners(@NotNull Set<Integer> winningNumbers, @NotNull String card) {
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
