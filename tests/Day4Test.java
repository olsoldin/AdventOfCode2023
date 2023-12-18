import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

class Day4Test {

	private Day4 day4;

	@BeforeEach
	void setUp() {
		day4 = new Day4();
	}

	@Test
	void getPileValue() {
		String pile = """
				Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
				Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
				Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
				Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
				Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
				Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
				""";

		Assertions.assertEquals(13, day4.getPileValue(pile));
	}

	@Test
	void getPileValueFull() throws IOException {
		String pile = Utils.readTextFile("./res/Day4Pile.txt");

		Assertions.assertEquals(20829, day4.getPileValue(pile));
	}

	@Test
	void getWinningNumbers() {
		String card1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
		String card2 = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19";
		String card3 = "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1";
		String card4 = "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83";
		String card5 = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36";
		String card6 = "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";

		Assertions.assertEquals(Set.of(41, 48, 83, 86, 17), day4.getWinningNumbers(card1));
		Assertions.assertEquals(Set.of(13, 32, 20, 16, 61), day4.getWinningNumbers(card2));
		Assertions.assertEquals(Set.of(1, 21, 53, 59, 44), day4.getWinningNumbers(card3));
		Assertions.assertEquals(Set.of(41, 92, 73, 84, 69), day4.getWinningNumbers(card4));
		Assertions.assertEquals(Set.of(87, 83, 26, 28, 32), day4.getWinningNumbers(card5));
		Assertions.assertEquals(Set.of(31, 18, 13, 56, 72), day4.getWinningNumbers(card6));
	}

	@Test
	void getNumWinners() {
		String card1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
		String card2 = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19";
		String card3 = "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1";
		String card4 = "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83";
		String card5 = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36";
		String card6 = "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";

		Assertions.assertEquals(4, day4.getNumWinners(Set.of(41, 48, 83, 86, 17), card1));
		Assertions.assertEquals(2, day4.getNumWinners(Set.of(13, 32, 20, 16, 61), card2));
		Assertions.assertEquals(2, day4.getNumWinners(Set.of(1, 21, 53, 59, 44), card3));
		Assertions.assertEquals(1, day4.getNumWinners(Set.of(41, 92, 73, 84, 69), card4));
		Assertions.assertEquals(0, day4.getNumWinners(Set.of(87, 83, 26, 28, 32), card5));
		Assertions.assertEquals(0, day4.getNumWinners(Set.of(31, 18, 13, 56, 72), card6));

	}

	@Test
	void getCardScore() {
		Assertions.assertEquals(1, day4.getCardScore(1));
		Assertions.assertEquals(2, day4.getCardScore(2));
		Assertions.assertEquals(4, day4.getCardScore(3));
		Assertions.assertEquals(8, day4.getCardScore(4));
		Assertions.assertEquals(16, day4.getCardScore(5));
		Assertions.assertEquals(32, day4.getCardScore(6));
		Assertions.assertEquals(64, day4.getCardScore(7));
		Assertions.assertEquals(128, day4.getCardScore(8));
		Assertions.assertEquals(256, day4.getCardScore(9));
		Assertions.assertEquals(512, day4.getCardScore(10));
	}
}