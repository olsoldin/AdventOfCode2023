import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day7 {

	private static final String ORDER = "AKQJT98765432";
	private static final String ORDER_JOKER = "AKQT98765432J";
	private static final String JOKER = "J";


	private int compareHands(@NotNull Tuple<String, Long> o1, @NotNull Tuple<String, Long> o2) {
		HAND h1 = getHandType(o1);
		HAND h2 = getHandType(o2);

		// The hands are the same, whichever one has the higher card at the start should be first
		if (h1.equals(h2)) {
			String s1 = o1.startIndex();
			String s2 = o2.startIndex();
			for (int i = 0; i < s1.length(); i++) {
				int comparison = Integer.compare(Day7.ORDER.indexOf(s2.charAt(i)), Day7.ORDER.indexOf(s1.charAt(i)));
				if (comparison != 0) return comparison;
			}
		}

		// Order by second first, so the sorted array is in reverse order (more powerful hands at the end)
		// This makes it easier to calculate the winnings using the index
		return h2.compareTo(h1);
	}

	private int compareHandsJoker(@NotNull Thruple<String, String, Long> o1, @NotNull Thruple<String, String, Long> o2) {
		HAND h1 = getHandType(new Tuple<>(o1.x(), o1.z()));
		HAND h2 = getHandType(new Tuple<>(o2.x(), o2.z()));

		if (h1.equals(h2)) {
			// We should go back to the original hands containing the jokers to compare them
			String s1 = o1.y();
			String s2 = o2.y();
			for (int i = 0; i < s1.length(); i++) {
				int comparison = Integer.compare(ORDER_JOKER.indexOf(s2.charAt(i)), ORDER_JOKER.indexOf(s1.charAt(i)));
				if (comparison != 0) return comparison;
			}
		}

		// Order by second first, so the sorted array is in reverse order (more powerful hands at the end)
		// This makes it easier to calculate the winnings using the index
		return h2.compareTo(h1);
	}

	public enum HAND {
		FIVE_OF_A_KIND,
		FOUR_OF_A_KIND,
		FULL_HOUSE,
		THREE_OF_A_KIND,
		TWO_PAIR,
		ONE_PAIR,
		HIGH_CARD
	}

	public Day7() {
	}

	public Long calculateTotalWinnings(@NotNull String hands) {
		List<Tuple<String, Long>> parsedHands = parseHands(hands);
		List<Tuple<String, Long>> sortedHands = sortHands(parsedHands);
		return calculateWinnings(sortedHands);
	}

	public @NotNull HAND getHandType(@NotNull Tuple<String, Long> hand) {
		Map<String, Integer> cardCount = new HashMap<>();

		for (char c : hand.startIndex().toCharArray()) {
			String cha = String.valueOf(c);
			cardCount.merge(cha, 1, Integer::sum);
		}

		int uniqueCards = cardCount.size();
		long maxCountOfACard = cardCount.values().stream()
				.mapToLong(i -> i)
				.max()
				.orElse(0);

		if (uniqueCards == 1) return HAND.FIVE_OF_A_KIND;
		if (uniqueCards == 2) {
			if (maxCountOfACard == 4) return HAND.FOUR_OF_A_KIND;
			return HAND.FULL_HOUSE;
		}
		if (uniqueCards == 3) {
			if (maxCountOfACard == 3) return HAND.THREE_OF_A_KIND;
			return HAND.TWO_PAIR;
		}
		if (uniqueCards == 4) return HAND.ONE_PAIR;
		return HAND.HIGH_CARD;
	}


	public @NotNull List<Tuple<String, Long>> sortHands(@NotNull List<Tuple<String, Long>> hands) {
		List<Tuple<String, Long>> sortedHands = new ArrayList<>(hands);
		sortedHands.sort(this::compareHands);
		return sortedHands;
	}

	public @NotNull List<Tuple<String, Long>> sortJokerHands(@NotNull List<Thruple<String, String, Long>> hands) {
		List<Thruple<String, String, Long>> sortedHands = new ArrayList<>(hands);
		sortedHands.sort(this::compareHandsJoker);
		return sortedHands.stream().map(hand -> new Tuple<>(hand.x(), hand.z())).toList();
	}

	public @NotNull List<Tuple<String, Long>> parseHands(@NotNull String input) throws NumberFormatException {
		List<Tuple<String, Long>> hands = new ArrayList<>();

		String[] lines = input.trim().split("\n");
		for (String line : lines) {
			String[] parts = line.split(" ");
			String cards = parts[0];
			Long bid = Long.parseLong(parts[1]);
			Tuple<String, Long> hand = new Tuple<>(cards, bid);
			hands.add(hand);
		}

		return hands;
	}

	public @NotNull Long calculateWinnings(@NotNull List<Tuple<String, Long>> sortedHands) {
		long winnings = 0L;

		for (int i = 0; i < sortedHands.size(); i++) {
			Long bid = sortedHands.get(i).endIndex();
			int rank = i + 1;
			winnings += bid * rank;
		}

		return winnings;
	}


	public Long calculateTotalWinningsWithJokers(@NotNull String hands) {
		List<Tuple<String, Long>> parsedHands = parseHands(hands);

		// Keep the original hand for sorting later
		// convert all jokers to best match
		// get the hand type
		List<Thruple<String, String, Long>> jokerHands = parsedHands.stream().map(hand -> new Thruple<>(getHandWithoutJokers(hand).startIndex(), hand.startIndex(), hand.endIndex())).toList();
		List<Tuple<String, Long>> sortedHands = sortJokerHands(jokerHands);

		return calculateWinnings(sortedHands);
	}

	public @NotNull Tuple<String, Long> getHandWithoutJokers(@NotNull Tuple<String, Long> hand) {
		// Doesn't contain any jokers anyway
		if (!hand.startIndex().contains(JOKER)) {
			return hand;
		}

		// Figure out which card (other than a joker) is the most common
		Map<String, Integer> cardCount = new HashMap<>();

		for (char c : hand.startIndex().toCharArray()) {
			String cha = String.valueOf(c);
			cardCount.merge(cha, 1, Integer::sum);
		}

		int uniqueCards = cardCount.size();
		int maxCountOfACard = 0;
		String modeCard = null;

		for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
			String currentCard = entry.getKey();
			Integer currentNumInstances = entry.getValue();

			if (JOKER.equals(currentCard)) {
				// Don't want to accidentally say the joker is the most common card!
				continue;
			}

			if (currentNumInstances > maxCountOfACard || modeCard == null) {
				maxCountOfACard = currentNumInstances;
				modeCard = currentCard;
			} else if (currentNumInstances == maxCountOfACard) {
				// If the number of cards are the same, check which card is higher up the deck
				int comparison = Integer.compare(ORDER_JOKER.indexOf(modeCard), ORDER_JOKER.indexOf(currentCard));
				if (comparison > 0) {
					modeCard = currentCard;
				}
			}
		}

		// hands without jokers are returned early
		String handStr = hand.startIndex();
		// If there are 5x jokers
		if (uniqueCards == 1 || modeCard == null) {
			// get the highest value card (in this case, Aces)
			modeCard = String.valueOf(ORDER_JOKER.charAt(0));
		}

		// Replace all Jokers with the mode card
		handStr = handStr.replaceAll(JOKER, modeCard);
		return new Tuple<>(handStr, hand.endIndex());
	}

	public HAND getHandTypeWithJoker(@NotNull Tuple<String, Long> hand) {
		Map<String, Integer> cardCount = new HashMap<>();

		for (char c : hand.startIndex().toCharArray()) {
			String cha = String.valueOf(c);
			cardCount.merge(cha, 1, Integer::sum);
		}

		int uniqueCards = cardCount.size();
		int maxCountOfACard = 0;
		String modeCard = null;

		for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
			String currentCard = entry.getKey();
			Integer currentNumInstances = entry.getValue();

			if (JOKER.equals(currentCard)) {
				// Don't want to accidentally say the joker is the most common card!
				continue;
			}

			if (currentNumInstances > maxCountOfACard || modeCard == null) {
				maxCountOfACard = currentNumInstances;
				modeCard = currentCard;
			} else if (currentNumInstances == maxCountOfACard) {
				// If the number of cards are the same, check which card is higher up the deck
				int comparison = Integer.compare(ORDER_JOKER.indexOf(modeCard), ORDER_JOKER.indexOf(currentCard));
				if (comparison > 0) {
					modeCard = currentCard;
				}
			}
		}

		String handStr = hand.startIndex();
		// Joker
		if (handStr.contains(JOKER)) {
			// In case there are 5x jokers
			if (uniqueCards == 1 || modeCard == null) {
				return HAND.FIVE_OF_A_KIND;
			}
			// Replace all Jokers with the mode card
			handStr = handStr.replaceAll(JOKER, modeCard);
			return getHandTypeWithJoker(new Tuple<>(handStr, hand.endIndex()));
		}

		if (uniqueCards == 1) return HAND.FIVE_OF_A_KIND;
		if (uniqueCards == 2) {
			if (maxCountOfACard == 4) return HAND.FOUR_OF_A_KIND;
			return HAND.FULL_HOUSE;
		}
		if (uniqueCards == 3) {
			if (maxCountOfACard == 3) return HAND.THREE_OF_A_KIND;
			return HAND.TWO_PAIR;
		}
		if (uniqueCards == 4) return HAND.ONE_PAIR;
		return HAND.HIGH_CARD;
	}
}
