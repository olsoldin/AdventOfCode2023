import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class Day8Test {

	private Day8 day8;

	@BeforeEach
	void setUp() {
		day8 = new Day8();
	}

	@Test
	void parseMoves() {
		List<Day8.MOVE> move1 = List.of(Day8.MOVE.LEFT, Day8.MOVE.LEFT, Day8.MOVE.LEFT, Day8.MOVE.RIGHT);
		List<Day8.MOVE> move2 = List.of();
		List<Day8.MOVE> move3 = List.of(Day8.MOVE.LEFT);
		List<Day8.MOVE> move4 = List.of();

		Assertions.assertEquals(move1, day8.parseMoves("LLLR"));
		Assertions.assertEquals(move2, day8.parseMoves(""));
		Assertions.assertEquals(move3, day8.parseMoves("FTGYLSS"));
		Assertions.assertEquals(move4, day8.parseMoves("4trfvxf"));
	}

	@Test
	void parseNodes() {
		Map<String, Tuple<String, String>> node1 = new HashMap<>();
		node1.put("AAA", new Tuple<>("BBB", "CCC"));

		Assertions.assertEquals(node1, day8.parseNodes(new String[]{"AAA = (BBB, CCC)"}));
	}

	@Test
	void getNextNode() {
		Map<String, Tuple<String, String>> nodes = new HashMap<>();
		nodes.put("AAA", new Tuple<>("BBB", "CCC"));
		nodes.put("BBB", new Tuple<>("AAA", "CCC"));

		Assertions.assertEquals("BBB", day8.getNextNode(nodes, "AAA", Day8.MOVE.LEFT));
		Assertions.assertEquals("CCC", day8.getNextNode(nodes, "AAA", Day8.MOVE.RIGHT));
		Assertions.assertEquals("AAA", day8.getNextNode(nodes, "BBB", Day8.MOVE.LEFT));
		Assertions.assertEquals("CCC", day8.getNextNode(nodes, "BBB", Day8.MOVE.RIGHT));
	}

	@Test
	void getNumberOfSteps() {
		String input = """
				RL
				
				AAA = (BBB, CCC)
				BBB = (DDD, EEE)
				CCC = (ZZZ, GGG)
				DDD = (DDD, DDD)
				EEE = (EEE, EEE)
				GGG = (GGG, GGG)
				ZZZ = (ZZZ, ZZZ)
				""";
		String input2 = """
				LLR
				
				AAA = (BBB, BBB)
				BBB = (AAA, ZZZ)
				ZZZ = (ZZZ, ZZZ)
				""";

		Assertions.assertEquals(2, day8.getNumberOfSteps(input));
		Assertions.assertEquals(6, day8.getNumberOfSteps(input2));
	}

	@Test
	void getNumberOfStepsFull() throws IOException {
		String input = Utils.readTextFile("./res/Day8Map.txt");

		Assertions.assertEquals(12643, day8.getNumberOfSteps(input));
	}
}