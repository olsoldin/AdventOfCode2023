import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day8 {

	public Day8() {
	}

	public enum MOVE {
		LEFT, RIGHT
	}

	public List<MOVE> parseMoves(@NotNull String map) {
		List<MOVE> moves = new ArrayList<>(map.length());
		for (char c : map.toCharArray()) {
			if (c == 'L') {
				moves.add(MOVE.LEFT);
			} else if (c == 'R') {
				moves.add(MOVE.RIGHT);
			}
		}
		return moves;
	}

	public Map<String, Tuple<String, String>> parseNodes(String @NotNull [] map) {
		Map<String, Tuple<String, String>> nodes = new HashMap<>();

		for (String line : map) {
			String[] parts = line.split("=");
			String nodeName = parts[0].trim();

			String[] nodeElements = parts[1].split(",");

			String leftNode = nodeElements[0].replaceAll("\\(", "").trim();
			String rightNode = nodeElements[1].replaceAll("\\)", "").trim();

			nodes.put(nodeName, new Tuple<>(leftNode, rightNode));
		}

		return nodes;
	}

	public String getNextNode(@NotNull Map<String, Tuple<String, String>> nodes, String currentNodeStr, @NotNull MOVE move) {
		Tuple<String, String> currentNode = nodes.get(currentNodeStr);
		return switch (move) {
			case LEFT -> currentNode.startIndex();
			case RIGHT -> currentNode.endIndex();
		};
	}


	public long getNumberOfSteps(@NotNull String mapStr) {
		String[] lines = mapStr.split("\n");
		String[] nodesStr = new String[lines.length - 2];
		System.arraycopy(lines, 2, nodesStr, 0, nodesStr.length);

		// First row is the moves
		List<MOVE> moves = parseMoves(lines[0]);

		// The rest are the nodes
		Map<String, Tuple<String, String>> nodes = parseNodes(nodesStr);


		long numSteps = 0;

		// First node
		String currentNode = "AAA";

		String lastNode = "ZZZ";
		while (!Objects.equals(currentNode, lastNode)) {
			MOVE currentMove = moves.get((int) (numSteps++ % moves.size()));
			currentNode = getNextNode(nodes, currentNode, currentMove);
		}

		return numSteps;
	}
}
