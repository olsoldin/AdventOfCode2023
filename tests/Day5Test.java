import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

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
	void lowestLocationPart2() {
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

		Assertions.assertEquals(BigInteger.valueOf(46), day5.lowestLocationPart2(almanac));
	}

	@Test
	void lowestLocationPart2Full() throws IOException {
		String almanac = Utils.readTextFile("./res/Day5Almanac.txt");

		Assertions.assertEquals(BigInteger.valueOf(1493866), day5.lowestLocationPart2(almanac));
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

	@Test
	void mapValue2() {
		List<Thruple<BigInteger, BigInteger, BigInteger>> seedToSoil = List.of(
				new Thruple<>(BigInteger.valueOf(98), BigInteger.valueOf(50), BigInteger.valueOf(2)),
				new Thruple<>(BigInteger.valueOf(50), BigInteger.valueOf(52), BigInteger.valueOf(48))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> soilToFertilizer = List.of(
				new Thruple<>(BigInteger.valueOf(15), BigInteger.valueOf(0), BigInteger.valueOf(37)),
				new Thruple<>(BigInteger.valueOf(52), BigInteger.valueOf(37), BigInteger.valueOf(2)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(39), BigInteger.valueOf(15))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> fertilizerToWater = List.of(
				new Thruple<>(BigInteger.valueOf(53), BigInteger.valueOf(49), BigInteger.valueOf(8)),
				new Thruple<>(BigInteger.valueOf(11), BigInteger.valueOf(0), BigInteger.valueOf(42)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(42), BigInteger.valueOf(7)),
				new Thruple<>(BigInteger.valueOf(7), BigInteger.valueOf(57), BigInteger.valueOf(4))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> waterToLight = List.of(
				new Thruple<>(BigInteger.valueOf(18), BigInteger.valueOf(88), BigInteger.valueOf(7)),
				new Thruple<>(BigInteger.valueOf(25), BigInteger.valueOf(18), BigInteger.valueOf(70))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> lightToTemperature = List.of(
				new Thruple<>(BigInteger.valueOf(77), BigInteger.valueOf(45), BigInteger.valueOf(23)),
				new Thruple<>(BigInteger.valueOf(45), BigInteger.valueOf(81), BigInteger.valueOf(19)),
				new Thruple<>(BigInteger.valueOf(64), BigInteger.valueOf(68), BigInteger.valueOf(13))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> temperatureToHumidity = List.of(
				new Thruple<>(BigInteger.valueOf(69), BigInteger.valueOf(0), BigInteger.valueOf(1)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(69))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> humidityToLocation = List.of(
				new Thruple<>(BigInteger.valueOf(56), BigInteger.valueOf(60), BigInteger.valueOf(37)),
				new Thruple<>(BigInteger.valueOf(93), BigInteger.valueOf(56), BigInteger.valueOf(4))
		);

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

	@Test
	void parseMap() {
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


		List<Thruple<BigInteger, BigInteger, BigInteger>> seedToSoil2 = List.of(
				new Thruple<>(BigInteger.valueOf(98), BigInteger.valueOf(50), BigInteger.valueOf(2)),
				new Thruple<>(BigInteger.valueOf(50), BigInteger.valueOf(52), BigInteger.valueOf(48))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> soilToFertilizer2 = List.of(
				new Thruple<>(BigInteger.valueOf(15), BigInteger.valueOf(0), BigInteger.valueOf(37)),
				new Thruple<>(BigInteger.valueOf(52), BigInteger.valueOf(37), BigInteger.valueOf(2)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(39), BigInteger.valueOf(15))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> fertilizerToWater2 = List.of(
				new Thruple<>(BigInteger.valueOf(53), BigInteger.valueOf(49), BigInteger.valueOf(8)),
				new Thruple<>(BigInteger.valueOf(11), BigInteger.valueOf(0), BigInteger.valueOf(42)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(42), BigInteger.valueOf(7)),
				new Thruple<>(BigInteger.valueOf(7), BigInteger.valueOf(57), BigInteger.valueOf(4))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> waterToLight2 = List.of(
				new Thruple<>(BigInteger.valueOf(18), BigInteger.valueOf(88), BigInteger.valueOf(7)),
				new Thruple<>(BigInteger.valueOf(25), BigInteger.valueOf(18), BigInteger.valueOf(70))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> lightToTemperature2 = List.of(
				new Thruple<>(BigInteger.valueOf(77), BigInteger.valueOf(45), BigInteger.valueOf(23)),
				new Thruple<>(BigInteger.valueOf(45), BigInteger.valueOf(81), BigInteger.valueOf(19)),
				new Thruple<>(BigInteger.valueOf(64), BigInteger.valueOf(68), BigInteger.valueOf(13))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> temperatureToHumidity2 = List.of(
				new Thruple<>(BigInteger.valueOf(69), BigInteger.valueOf(0), BigInteger.valueOf(1)),
				new Thruple<>(BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(69))
		);

		List<Thruple<BigInteger, BigInteger, BigInteger>> humidityToLocation2 = List.of(
				new Thruple<>(BigInteger.valueOf(56), BigInteger.valueOf(60), BigInteger.valueOf(37)),
				new Thruple<>(BigInteger.valueOf(93), BigInteger.valueOf(56), BigInteger.valueOf(4))
		);

		Assertions.assertEquals(seedToSoil2, day5.parseMap(seedToSoil));
		Assertions.assertEquals(soilToFertilizer2, day5.parseMap(soilToFertilizer));
		Assertions.assertEquals(fertilizerToWater2, day5.parseMap(fertilizerToWater));
		Assertions.assertEquals(waterToLight2, day5.parseMap(waterToLight));
		Assertions.assertEquals(lightToTemperature2, day5.parseMap(lightToTemperature));
		Assertions.assertEquals(temperatureToHumidity2, day5.parseMap(temperatureToHumidity));
		Assertions.assertEquals(humidityToLocation2, day5.parseMap(humidityToLocation));
	}
}