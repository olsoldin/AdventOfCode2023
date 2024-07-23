import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day2Test {

	@Test
	void sumPossibleGames() {
		String games = """
				Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
				Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
				Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
				Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
				Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
				""";

		String bag = "12 red cubes, 13 green cubes, and 14 blue cubes";

		Assertions.assertEquals(8, Day2.sumPossibleGames(games, bag));
	}

	@Test
	void sumPossibleGamesFull() throws IOException {
		String games = Utils.readTextFile("./res/games.txt");

		String bag = "12 red cubes, 13 green cubes, and 14 blue cubes";

		Assertions.assertEquals(1867, Day2.sumPossibleGames(games, bag));
	}

	@Test
	void testGetNumInBag() {
		String bag = "12 red cubes, 13 green cubes, and 14 blue cubes";
		String cubes = "3 green, 4 blue, 1 red";

		Assertions.assertEquals(12, Day2.getNumCubes("red", bag));
		Assertions.assertEquals(13, Day2.getNumCubes("green", bag));
		Assertions.assertEquals(14, Day2.getNumCubes("blue", bag));
		Assertions.assertEquals(0, Day2.getNumCubes("turquoise", bag));
		Assertions.assertEquals(0, Day2.getNumCubes("", bag));

		Assertions.assertEquals(3, Day2.getNumCubes("green", cubes));
		Assertions.assertEquals(4, Day2.getNumCubes("blue", cubes));
		Assertions.assertEquals(1, Day2.getNumCubes("red", cubes));
		Assertions.assertEquals(0, Day2.getNumCubes("turquoise", cubes));
		Assertions.assertEquals(0, Day2.getNumCubes("", cubes));
	}

	@Test
	void testSumPowersMinCubes() {
		String games = """
				Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
				Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
				Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
				Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
				Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
				""";

		Assertions.assertEquals(2286, Day2.sumPowersMinCubes(games));
	}


	@Test
	void testMinNumCubes() {
		String game1 = "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
		String game2 = "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
		String game3 = "8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
		String game4 = "1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		String game5 = "6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

		Assertions.assertEquals(4 * 2 * 6, Day2.minNumCubes(game1));
		//noinspection PointlessArithmeticExpression
		Assertions.assertEquals(1 * 3 * 4, Day2.minNumCubes(game2));
		Assertions.assertEquals(20 * 13 * 6, Day2.minNumCubes(game3));
		Assertions.assertEquals(14 * 3 * 15, Day2.minNumCubes(game4));
		Assertions.assertEquals(6 * 3 * 2, Day2.minNumCubes(game5));
	}

	@Test
	void testSumPowersMinCubesFull() throws IOException {
		String games = Utils.readTextFile("./res/games.txt");

		Assertions.assertEquals(84538, Day2.sumPowersMinCubes(games));
	}
}