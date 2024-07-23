import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {

	@org.jetbrains.annotations.NotNull
	@org.jetbrains.annotations.Contract("_ -> new")
	public static String readTextFile(@NotNull String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName))).replaceAll("\r\n", "\n");
	}

	public static int startsWithTextInt(String str) {
		str = str.trim().toLowerCase();
		if (str.startsWith("zero")) return 0;
		else if (str.startsWith("one")) return 1;
		else if (str.startsWith("two")) return 2;
		else if (str.startsWith("three")) return 3;
		else if (str.startsWith("four")) return 4;
		else if (str.startsWith("five")) return 5;
		else if (str.startsWith("six")) return 6;
		else if (str.startsWith("seven")) return 7;
		else if (str.startsWith("eight")) return 8;
		else if (str.startsWith("nine")) return 9;
		else return -1;
	}

	public static int endsWithTextInt(String str) {
		str = str.trim().toLowerCase();
		if (str.endsWith("zero")) return 0;
		else if (str.endsWith("one")) return 1;
		else if (str.endsWith("two")) return 2;
		else if (str.endsWith("three")) return 3;
		else if (str.endsWith("four")) return 4;
		else if (str.endsWith("five")) return 5;
		else if (str.endsWith("six")) return 6;
		else if (str.endsWith("seven")) return 7;
		else if (str.endsWith("eight")) return 8;
		else if (str.endsWith("nine")) return 9;
		else return -1;
	}

	public static int textToSingleInt(@NotNull String str) {
		return switch (str.trim().toLowerCase()) {
			case "zero" -> 0;
			case "one" -> 1;
			case "two" -> 2;
			case "three" -> 3;
			case "four" -> 4;
			case "five" -> 5;
			case "six" -> 6;
			case "seven" -> 7;
			case "eight" -> 8;
			case "nine" -> 9;
			default -> -1;
		};
	}

	public static @NotNull List<Integer> extractIntegers(@NotNull String input) {
		List<Integer> ints = new ArrayList<>();

		// Split the string on any non-digit characters
		Pattern pattern = Pattern.compile("\\D+");
		String[] parts = pattern.split(input);

		for (String part : parts) {
			if (part.trim().isEmpty()) {
				continue;
			}
			try {
				ints.add(Integer.valueOf(part));
			} catch (NumberFormatException e) {
				System.err.println(part + " is not an Integer: " + e);
			}
		}

		return ints;
	}

	public static @Nullable BigInteger extractBigIntegerAsOne(@NotNull String input) {
		// Split the string on any non-digit characters
		Pattern pattern = Pattern.compile("\\D+");
		String[] parts = pattern.split(input);

		StringBuilder sb = new StringBuilder();

		for (String part : parts) {
			sb.append(part);
		}

		try {
			return new BigInteger(sb.toString());
		} catch (NumberFormatException e) {
			System.err.println(sb + " is not a number: " + e);
		}
		return null;
	}


	public static long findGCD(long a, long b) {
		if (b == 0) return a;
		return findGCD(b, a % b);
	}

	public static long findLCM(@NotNull List<Long> numbers) {
		long lcm = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {
			long gcd = findGCD(lcm, numbers.get(i));
			lcm = (lcm * numbers.get(i)) / gcd;
		}
		return lcm;
	}
}
