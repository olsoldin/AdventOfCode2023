import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

class UtilsTest {

	@Test
	void readNonExistentTextFile() {
		String fileName = "not a real file";

		Assertions.assertThrows(IOException.class, () -> Utils.readTextFile(fileName));
	}

	@Test
	void readExistingFile() throws IOException {
		String fileName = "./res/SimpleFile.txt";

		Assertions.assertDoesNotThrow(() -> Utils.readTextFile(fileName));

		Assertions.assertEquals("This is a simple text file", Utils.readTextFile(fileName));
	}

	@Test
	void textToSingleInt() {
		Assertions.assertEquals(0, Utils.textToSingleInt("Zero"));
		Assertions.assertEquals(1, Utils.textToSingleInt("one"));
		Assertions.assertEquals(2, Utils.textToSingleInt("two"));
		Assertions.assertEquals(3, Utils.textToSingleInt("three"));
		Assertions.assertEquals(4, Utils.textToSingleInt("four"));
		Assertions.assertEquals(5, Utils.textToSingleInt("five"));
		Assertions.assertEquals(6, Utils.textToSingleInt("six"));
		Assertions.assertEquals(7, Utils.textToSingleInt("seven"));
		Assertions.assertEquals(8, Utils.textToSingleInt("eight"));
		Assertions.assertEquals(9, Utils.textToSingleInt("nine"));

		Assertions.assertEquals(-1, Utils.textToSingleInt("one two"));
		Assertions.assertEquals(5, Utils.textToSingleInt(" \n\n  five   "));
		Assertions.assertEquals(-1, Utils.textToSingleInt(""));
	}

	@Test
	void startsWithTextInt() {
		Assertions.assertEquals(0, Utils.startsWithTextInt("zERo"));
		Assertions.assertEquals(1, Utils.startsWithTextInt("onetwothree"));
		Assertions.assertEquals(2, Utils.startsWithTextInt("tWo dfdsfd"));
		Assertions.assertEquals(3, Utils.startsWithTextInt("three 555"));
		Assertions.assertEquals(4, Utils.startsWithTextInt("   four 66345"));
		Assertions.assertEquals(5, Utils.startsWithTextInt("\n\n  five"));
		Assertions.assertEquals(6, Utils.startsWithTextInt("six32"));
		Assertions.assertEquals(7, Utils.startsWithTextInt("seven df sf"));
		Assertions.assertEquals(8, Utils.startsWithTextInt("EIGHT"));
		Assertions.assertEquals(9, Utils.startsWithTextInt("nine"));
		Assertions.assertEquals(-1, Utils.startsWithTextInt("nothing one"));
		Assertions.assertEquals(-1, Utils.startsWithTextInt("on444e"));
		Assertions.assertEquals(-1, Utils.startsWithTextInt(""));
	}

	@Test
	void endsWithTextInt() {
		Assertions.assertEquals(0, Utils.endsWithTextInt("zERo"));
		Assertions.assertEquals(1, Utils.endsWithTextInt("threetwoone"));
		Assertions.assertEquals(2, Utils.endsWithTextInt(" dfdsfd tWo"));
		Assertions.assertEquals(3, Utils.endsWithTextInt("555 three"));
		Assertions.assertEquals(4, Utils.endsWithTextInt(" 66345  four "));
		Assertions.assertEquals(5, Utils.endsWithTextInt("\n\n  five"));
		Assertions.assertEquals(6, Utils.endsWithTextInt("32six"));
		Assertions.assertEquals(7, Utils.endsWithTextInt(" df sfseven"));
		Assertions.assertEquals(8, Utils.endsWithTextInt("EIGHT"));
		Assertions.assertEquals(9, Utils.endsWithTextInt("nine"));
		Assertions.assertEquals(-1, Utils.endsWithTextInt("one nothing "));
		Assertions.assertEquals(-1, Utils.endsWithTextInt("on444e"));
		Assertions.assertEquals(-1, Utils.endsWithTextInt(""));
	}

	@Test
	void extractIntegers() {
		Assertions.assertEquals(List.of(1, 2, 3, 4, 5), Utils.extractIntegers("1 2 3 4 5"));
		Assertions.assertEquals(List.of(1, 2, 3, 4, 5), Utils.extractIntegers("hi1mum!2 3 4 5"));
		Assertions.assertEquals(List.of(1, 2, 3, 4, 5), Utils.extractIntegers("1   2  3   4   5   "));
		Assertions.assertEquals(List.of(), Utils.extractIntegers(""));
		Assertions.assertEquals(List.of(), Utils.extractIntegers("Hello World!"));
	}

	@Test
	void extractIntegerAsOne() {
		Assertions.assertEquals(BigInteger.valueOf(1234), Utils.extractBigIntegerAsOne("1234"));
		Assertions.assertEquals(BigInteger.valueOf(1234), Utils.extractBigIntegerAsOne("1 2 3 4 "));
		Assertions.assertEquals(BigInteger.valueOf(1234), Utils.extractBigIntegerAsOne("1Hi!23 4 "));
	}
}