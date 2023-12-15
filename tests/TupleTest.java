import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TupleTest {

	@Test
	public void testTuple() {
		Tuple<Integer, Integer> integers = new Tuple<>(1, 2);
		Tuple<String, String> strings = new Tuple<>("Hello", "World");
		Tuple<String, Integer> strInt = new Tuple<>("Hello", 30);

		Assertions.assertEquals(1, integers.startIndex());
		Assertions.assertEquals(2, integers.endIndex());

		Assertions.assertEquals("Hello", strings.startIndex());
		Assertions.assertEquals("World", strings.endIndex());

		Assertions.assertEquals("Hello", strInt.startIndex());
		Assertions.assertEquals(30, strInt.endIndex());

		Tuple<String, String> strings2 = new Tuple<>("Hello", "World");
		Tuple<Integer, Integer> integers2 = new Tuple<>(1, 2);
		Assertions.assertEquals(strings, strings2);
		Assertions.assertEquals(integers, integers2);
		Assertions.assertEquals(strings.hashCode(), strings2.hashCode());
		Assertions.assertEquals(integers.hashCode(), integers2.hashCode());
	}

}