import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class Day7Test {

	private Day7 day7;

	@BeforeEach
	void setUp() {
		day7 = new Day7();
	}

	@Test
	void sortList() {
		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L); // 1
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L); // 4
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);  // 3
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L); // 2
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L); // 5

		List<Tuple<String, Long>> hands = List.of(
				hand1,
				hand2,
				hand3,
				hand4,
				hand5);

		List<Tuple<String, Long>> sortedHands = day7.sortHands(hands);

		Assertions.assertEquals(hand1, sortedHands.get(0));
		Assertions.assertEquals(hand4, sortedHands.get(1));
		Assertions.assertEquals(hand3, sortedHands.get(2));
		Assertions.assertEquals(hand2, sortedHands.get(3));
		Assertions.assertEquals(hand5, sortedHands.get(4));

		Tuple<String, Long> hand6 = new Tuple<>("3334A", 765L); // 3
		Tuple<String, Long> hand7 = new Tuple<>("33346", 684L); // 1
		Tuple<String, Long> hand8 = new Tuple<>("3334K", 28L);  // 2

		List<Tuple<String, Long>> hands2 = List.of(
				hand6,
				hand7,
				hand8);

		List<Tuple<String, Long>> sortedHands2 = day7.sortHands(hands2);

		Assertions.assertEquals(hand7, sortedHands2.get(0));
		Assertions.assertEquals(hand8, sortedHands2.get(1));
		Assertions.assertEquals(hand6, sortedHands2.get(2));
	}

	@Test
	void getHandType() {
		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L);
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L);
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L);
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L);
		Tuple<String, Long> hand6 = new Tuple<>("QQQQQ", 483L);
		Tuple<String, Long> hand7 = new Tuple<>("12345", 483L);

		Assertions.assertEquals(Day7.HAND.ONE_PAIR, day7.getHandType(hand1));
		Assertions.assertEquals(Day7.HAND.THREE_OF_A_KIND, day7.getHandType(hand2));
		Assertions.assertEquals(Day7.HAND.TWO_PAIR, day7.getHandType(hand3));
		Assertions.assertEquals(Day7.HAND.TWO_PAIR, day7.getHandType(hand4));
		Assertions.assertEquals(Day7.HAND.THREE_OF_A_KIND, day7.getHandType(hand5));
		Assertions.assertEquals(Day7.HAND.FIVE_OF_A_KIND, day7.getHandType(hand6));
		Assertions.assertEquals(Day7.HAND.HIGH_CARD, day7.getHandType(hand7));
	}

	@Test
	void parseHands() {
		String input = """
				32T3K 765
				T55J5 684
				KK677 28
				KTJJT 220
				QQQJA 483
				""";

		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L);
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L);
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L);
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L);

		List<Tuple<String, Long>> hands = List.of(
				hand1,
				hand2,
				hand3,
				hand4,
				hand5);

		List<Tuple<String, Long>> day7Hands = day7.parseHands(input);

		Assertions.assertIterableEquals(hands, day7Hands);
	}

	@Test
	void calculateWinnings() {
		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L);
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L);
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L);
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L);

		List<Tuple<String, Long>> hands = List.of(
				hand1,
				hand4,
				hand3,
				hand2,
				hand5);

		Assertions.assertEquals(6440, day7.calculateWinnings(hands));
	}

	@Test
	void calculateTotalWinnings() {
		String input = """
				32T3K 765
				T55J5 684
				KK677 28
				KTJJT 220
				QQQJA 483
				""";

		Assertions.assertEquals(6440, day7.calculateTotalWinnings(input));
	}

	@Test
	void calculateTotalWinningsFull() throws IOException {
		String hands = Utils.readTextFile("./res/Day7Hands.txt");
		Assertions.assertEquals(250602641, day7.calculateTotalWinnings(hands));
	}

	@Test
	void getHandTypeWithJoker() {
		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L);
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L);
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L);
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L);
		Tuple<String, Long> hand6 = new Tuple<>("QQQQQ", 483L);
		Tuple<String, Long> hand7 = new Tuple<>("12345", 483L);
		Tuple<String, Long> hand8 = new Tuple<>("JJJJJ", 483L);
		Tuple<String, Long> hand9 = new Tuple<>("AATTJ", 483L);
		Tuple<String, Long> hand10 = new Tuple<>("AJJJT", 483L);


		Assertions.assertEquals(Day7.HAND.ONE_PAIR, day7.getHandTypeWithJoker(hand1));
		Assertions.assertEquals(Day7.HAND.FOUR_OF_A_KIND, day7.getHandTypeWithJoker(hand2));
		Assertions.assertEquals(Day7.HAND.TWO_PAIR, day7.getHandTypeWithJoker(hand3));
		Assertions.assertEquals(Day7.HAND.FOUR_OF_A_KIND, day7.getHandTypeWithJoker(hand4));
		Assertions.assertEquals(Day7.HAND.FOUR_OF_A_KIND, day7.getHandTypeWithJoker(hand5));
		Assertions.assertEquals(Day7.HAND.FIVE_OF_A_KIND, day7.getHandTypeWithJoker(hand6));
		Assertions.assertEquals(Day7.HAND.HIGH_CARD, day7.getHandTypeWithJoker(hand7));
		Assertions.assertEquals(Day7.HAND.FIVE_OF_A_KIND, day7.getHandTypeWithJoker(hand8));
		Assertions.assertEquals(Day7.HAND.FULL_HOUSE, day7.getHandTypeWithJoker(hand9));
		Assertions.assertEquals(Day7.HAND.FOUR_OF_A_KIND, day7.getHandTypeWithJoker(hand10));
	}

	@Test
	void getHandWithoutJokers() {
		Tuple<String, Long> hand1 = new Tuple<>("32T3K", 765L);
		Tuple<String, Long> hand2 = new Tuple<>("T55J5", 684L);
		Tuple<String, Long> hand3 = new Tuple<>("KK677", 28L);
		Tuple<String, Long> hand4 = new Tuple<>("KTJJT", 220L);
		Tuple<String, Long> hand5 = new Tuple<>("QQQJA", 483L);
		Tuple<String, Long> hand6 = new Tuple<>("QQQQQ", 483L);
		Tuple<String, Long> hand7 = new Tuple<>("12345", 483L);
		Tuple<String, Long> hand8 = new Tuple<>("JJJJJ", 483L);
		Tuple<String, Long> hand9 = new Tuple<>("AATTJ", 483L);
		Tuple<String, Long> hand10 = new Tuple<>("AJJJT", 483L);

		Assertions.assertEquals(hand1, day7.getHandWithoutJokers(hand1));
		Assertions.assertEquals(new Tuple<>("T5555", 684L), day7.getHandWithoutJokers(hand2));
		Assertions.assertEquals(hand3, day7.getHandWithoutJokers(hand3));
		Assertions.assertEquals(new Tuple<>("KTTTT", 220L), day7.getHandWithoutJokers(hand4));
		Assertions.assertEquals(new Tuple<>("QQQQA", 483L), day7.getHandWithoutJokers(hand5));
		Assertions.assertEquals(hand6, day7.getHandWithoutJokers(hand6));
		Assertions.assertEquals(hand7, day7.getHandWithoutJokers(hand7));
		Assertions.assertEquals(new Tuple<>("AAAAA", 483L), day7.getHandWithoutJokers(hand8));
		Assertions.assertEquals(new Tuple<>("AATTA", 483L), day7.getHandWithoutJokers(hand9));
		Assertions.assertEquals(new Tuple<>("AAAAT", 483L), day7.getHandWithoutJokers(hand10));
	}

	@Test
	void calculateTotalWinningsWithJokers() {
		String input = """
				32T3K 765
				T55J5 684
				KK677 28
				KTJJT 220
				QQQJA 483
				""";

		Assertions.assertEquals(5905, day7.calculateTotalWinningsWithJokers(input));
	}

	@Test
	void calculateTotalWinningsWithJokersFull() throws IOException {
		String hands = Utils.readTextFile("./res/Day7Hands.txt");
		// 251232425 is too high
		// changed the logic to sorting
		// 250964151 is too low
		// changed the logic to sorting again
		// 251037509 is juuust right
		Assertions.assertEquals(251037509, day7.calculateTotalWinningsWithJokers(hands));
	}
}