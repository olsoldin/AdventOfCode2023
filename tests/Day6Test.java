import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day6Test {

	private Day6 day6;

	@BeforeEach
	void setUp() {
		day6 = new Day6();
	}

	@Test
	void numWaysToBeatRecord() {
		String records = """
				Time:      7  15   30
				Distance:  9  40  200
				""";
		Assertions.assertEquals(Long.valueOf(288), day6.numWaysToBeatRecord(records));
	}
	@Test
	void numWaysToBeatRecordFull() throws IOException {
		String records = Utils.readTextFile("./res/Day6times.txt");
		Assertions.assertEquals(Long.valueOf(1195150), day6.numWaysToBeatRecord(records));
	}

	@Test
	void calculateDistances() {
		Assertions.assertEquals(List.of(0, 6, 10, 12, 12, 10, 6, 0), day6.calculatePossibleDistances(7));
		Assertions.assertEquals(List.of(0, 3, 4, 3, 0), day6.calculatePossibleDistances(4));

	}

	@Test
	void numWaysToBeatRecordPart2() {
		String record = """
				Time:      7  15   30
				Distance:  9  40  200
				""";
		Assertions.assertEquals(71503, day6.numWaysToBeatRecordPart2(record));
	}

	@Test
	void numWaysToBeatRecordPart2Full() throws IOException {
		String record = Utils.readTextFile("./res/Day6times.txt");
		Assertions.assertEquals(42550411, day6.numWaysToBeatRecordPart2(record));
	}
}