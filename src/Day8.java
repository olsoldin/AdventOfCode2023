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

		return numStepsToGetToNode(moves, nodes, "AAA", "ZZZ");

	}


	private long numStepsToGetToNode(List<MOVE> moves, Map<String, Tuple<String, String>> nodes, String startingNode, String lastNode) {
		int numSteps = 0;
		String currentNode = startingNode;

		while (!currentNode.endsWith(lastNode)) {
			MOVE currentMove = moves.get(numSteps++ % moves.size());
			currentNode = getNextNode(nodes, currentNode, currentMove);
		}

		return numSteps;
	}


	public long getNumberOfStepsPart2(@NotNull String mapStr) {
		String[] lines = mapStr.split("\n");
		String[] nodesStr = new String[lines.length - 2];
		System.arraycopy(lines, 2, nodesStr, 0, nodesStr.length);

		// First row is the moves
		List<MOVE> moves = parseMoves(lines[0]);

		// The rest are the nodes
		Map<String, Tuple<String, String>> nodes = parseNodes(nodesStr);

		// Get the num steps from start to finish for each chain
		List<Long> chainCounts = new ArrayList<>();
		for (String currentNode : nodes.keySet()) {
			if (currentNode.endsWith("A")) {
				chainCounts.add(numStepsToGetToNode(moves, nodes, currentNode, "Z"));
			}
		}

		// Return the lowest common multiple of all step counts
		return Utils.findLCM(chainCounts);
	}
}
