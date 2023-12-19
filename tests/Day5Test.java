import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

class Day5Test {

	private Day5 day5;

	@BeforeEach
	void setUp() {
		day5 = new Day5();
	}

	@Test
	void lowestLocation() {
		String almanac = """
				seeds: 79 14 55 13
				    
				seed-to-soil map:
				50 98 2
				52 50 48
				    
				soil-to-fertilizer map:
				0 15 37
				37 52 2
				39 0 15
				    
				fertilizer-to-water map:
				49 53 8
				0 11 42
				42 0 7
				57 7 4
				    
				water-to-light map:
				88 18 7
				18 25 70
				    
				light-to-temperature map:
				45 77 23
				81 45 19
				68 64 13
				    
				temperature-to-humidity map:
				0 69 1
				1 0 69
				    
				humidity-to-location map:
				60 56 37
				56 93 4
				""";

		Assertions.assertEquals(BigInteger.valueOf(35), day5.lowestLocation(almanac));
	}

	@Test
	void lowestLocationFull() throws IOException {
		String almanac = Utils.readTextFile("./res/Day5Almanac.txt");

		Assertions.assertEquals(BigInteger.valueOf(174137457), day5.lowestLocation(almanac));
	}

	@Test
	void getMap() {
		String almanac = """
				seeds: 79 14 55 13
				    
				seed-to-soil map:
				50 98 2
				52 50 48
				    
				soil-to-fertilizer map:
				0 15 37
				37 52 2
				39 0 15
				    
				fertilizer-to-water map:
				49 53 8
				0 11 42
				42 0 7
				57 7 4
				    
				water-to-light map:
				88 18 7
				18 25 70
				    
				light-to-temperature map:
				45 77 23
				81 45 19
				68 64 13
				    
				temperature-to-humidity map:
				0 69 1
				1 0 69
				    
				humidity-to-location map:
				60 56 37
				56 93 4
				""";

		String seedToSoil = """
				50 98 2
				52 50 48
				""";

		String soilToFertilizer = """
				0 15 37
				37 52 2
				39 0 15
				""";

		String fertilizerToWater = """
				49 53 8
				0 11 42
				42 0 7
				57 7 4
				""";

		String waterToLight = """
				88 18 7
				18 25 70
				""";

		String lightToTemperature = """
				45 77 23
				81 45 19
				68 64 13
				""";

		String temperatureToHumidity = """
				0 69 1
				1 0 69
				""";

		String humidityToLocation = """
				60 56 37
				56 93 4
				""";

		Assertions.assertEquals(seedToSoil.trim(), day5.getMap(Day5.SEED_TO_SOIL, almanac));
		Assertions.assertEquals(soilToFertilizer.trim(), day5.getMap(Day5.SOIL_TO_FERTILIZER, almanac));
		Assertions.assertEquals(fertilizerToWater.trim(), day5.getMap(Day5.FERTILIZER_TO_WATER, almanac));
		Assertions.assertEquals(waterToLight.trim(), day5.getMap(Day5.WATER_TO_LIGHT, almanac));
		Assertions.assertEquals(lightToTemperature.trim(), day5.getMap(Day5.LIGHT_TO_TEMPERATURE, almanac));
		Assertions.assertEquals(temperatureToHumidity.trim(), day5.getMap(Day5.TEMPERATURE_TO_HUMIDITY, almanac));
		Assertions.assertEquals(humidityToLocation.trim(), day5.getMap(Day5.HUMIDITY_TO_LOCATION, almanac));
	}

	@Test
	void mapValue() {
		String seedToSoil = """
				50 98 2
				52 50 48
				""";

		String soilToFertilizer = """
				0 15 37
				37 52 2
				39 0 15
				""";

		String fertilizerToWater = """
				49 53 8
				0 11 42
				42 0 7
				57 7 4
				""";

		String waterToLight = """
				88 18 7
				18 25 70
				""";

		String lightToTemperature = """
				45 77 23
				81 45 19
				68 64 13
				""";

		String temperatureToHumidity = """
				0 69 1
				1 0 69
				""";

		String humidityToLocation = """
				60 56 37
				56 93 4
				""";

		Assertions.assertEquals(BigInteger.valueOf(81), day5.mapValue(seedToSoil, BigInteger.valueOf(79)));
		Assertions.assertEquals(BigInteger.valueOf(81), day5.mapValue(soilToFertilizer, BigInteger.valueOf(81)));
		Assertions.assertEquals(BigInteger.valueOf(81), day5.mapValue(fertilizerToWater, BigInteger.valueOf(81)));
		Assertions.assertEquals(BigInteger.valueOf(74), day5.mapValue(waterToLight, BigInteger.valueOf(81)));
		Assertions.assertEquals(BigInteger.valueOf(78), day5.mapValue(lightToTemperature, BigInteger.valueOf(74)));
		Assertions.assertEquals(BigInteger.valueOf(78), day5.mapValue(temperatureToHumidity, BigInteger.valueOf(78)));
		Assertions.assertEquals(BigInteger.valueOf(82), day5.mapValue(humidityToLocation, BigInteger.valueOf(78)));

		Assertions.assertEquals(BigInteger.valueOf(14), day5.mapValue(seedToSoil, BigInteger.valueOf(14)));
		Assertions.assertEquals(BigInteger.valueOf(53), day5.mapValue(soilToFertilizer, BigInteger.valueOf(14)));
		Assertions.assertEquals(BigInteger.valueOf(49), day5.mapValue(fertilizerToWater, BigInteger.valueOf(53)));
		Assertions.assertEquals(BigInteger.valueOf(42), day5.mapValue(waterToLight, BigInteger.valueOf(49)));
		Assertions.assertEquals(BigInteger.valueOf(42), day5.mapValue(lightToTemperature, BigInteger.valueOf(42)));
		Assertions.assertEquals(BigInteger.valueOf(43), day5.mapValue(temperatureToHumidity, BigInteger.valueOf(42)));
		Assertions.assertEquals(BigInteger.valueOf(43), day5.mapValue(humidityToLocation, BigInteger.valueOf(43)));
	}
}