import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

	public static int sumPossibleGames(@NotNull String inGames, String bag) {
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

			int minRed = minNumCubes("red", game);
			int minGreen = minNumCubes("green", game);
			int minBlue = minNumCubes("blue", game);
			sum += (minRed * minGreen * minBlue);
		}

		return sum;
	}

	public static int minNumCubes(String colour, @NotNull String game) {
		int maxColour = 0;
		String[] rounds = game.split(";");

		for (String round : rounds) {
			int currentColour = getNumCubes(colour, round);
			maxColour = Math.max(maxColour, currentColour);
		}

		return maxColour;
	}

	public static int getNumCubes(String colour, String bag) {
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
