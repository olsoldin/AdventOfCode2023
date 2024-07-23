import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

	public static int sumPossibleGames(@NotNull String inGames, @NotNull String bag) {
		int sum = 0;

		String[] games = inGames.split("\n");

		int numRed = getNumCubes("red", bag);
		int numGreen = getNumCubes("green", bag);
		int numBlue = getNumCubes("blue", bag);

		for (int i = 1; i <= games.length; i++) {
			String game = games[i - 1];
			// Get the part after "Game 1: "
			game = game.split(": ")[1];

			int maxRed = 0;
			int maxGreen = 0;
			int maxBlue = 0;

			String[] rounds = game.split(";");

			for (String round : rounds) {
				int red = getNumCubes("red", round);
				int green = getNumCubes("green", round);
				int blue = getNumCubes("blue", round);

				maxRed = Math.max(maxRed, red);
				maxGreen = Math.max(maxGreen, green);
				maxBlue = Math.max(maxBlue, blue);
			}

			if (maxRed <= numRed && maxGreen <= numGreen && maxBlue <= numBlue) {
				sum += i;
			}
		}

		return sum;
	}

	public static int sumPowersMinCubes(@NotNull String inGames) {
		int sum = 0;

		String[] games = inGames.split("\n");

		for (String game : games) {
			// Get the part after "Game 1: "
			game = game.split(": ")[1];

			sum += minNumCubes(game);
		}

		return sum;
	}

	public static int minNumCubes(@NotNull String game) {
		int maxRed = 0;
		int maxGreen = 0;
		int maxBlue = 0;
		String[] rounds = game.split(";");

		for (String round : rounds) {
			maxRed = Math.max(maxRed, getNumCubes("red", round));
			maxGreen = Math.max(maxGreen, getNumCubes("green", round));
			maxBlue = Math.max(maxBlue, getNumCubes("blue", round));

		}

		return maxRed * maxGreen * maxBlue;
	}

	public static int getNumCubes(@NotNull String colour, @NotNull String bag) {
		if (colour.trim().isEmpty()) {
			return 0;
		}
		Pattern pattern = Pattern.compile("(\\d+) " + colour.trim());
		Matcher matcher = pattern.matcher(bag);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return 0;
	}
}
