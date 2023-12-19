import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

	@org.jetbrains.annotations.NotNull
	@org.jetbrains.annotations.Contract("_ -> new")
	public static String readTextFile(String fileName) throws IOException {
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
}
