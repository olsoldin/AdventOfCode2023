import java.math.BigInteger;

public class Day5 {

	public static String SEED_TO_SOIL = "seed-to-soil";
	public static String SOIL_TO_FERTILIZER = "soil-to-fertilizer";
	public static String FERTILIZER_TO_WATER = "fertilizer-to-water";
	public static String WATER_TO_LIGHT = "water-to-light";
	public static String LIGHT_TO_TEMPERATURE = "light-to-temperature";
	public static String TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity";
	public static String HUMIDITY_TO_LOCATION = "humidity-to-location";

	public Day5() {
	}

	public BigInteger lowestLocation(String almanac) {
		BigInteger lowestLocation = BigInteger.valueOf(Long.MAX_VALUE);

		String[] seeds = almanac.split("\n")[0]
				.split(":")[1]
				.trim()
				.split(" ");

		String seedToSoilMap = getMap(SEED_TO_SOIL, almanac);
		String soilToFertilizerMap = getMap(SOIL_TO_FERTILIZER, almanac);
		String fertilizerToWaterMap = getMap(FERTILIZER_TO_WATER, almanac);
		String waterToLightMap = getMap(WATER_TO_LIGHT, almanac);
		String lightToTemperatureMap = getMap(LIGHT_TO_TEMPERATURE, almanac);
		String temperatureToHumidityMap = getMap(TEMPERATURE_TO_HUMIDITY, almanac);
		String humidityToLocationMap = getMap(HUMIDITY_TO_LOCATION, almanac);


		for (String sseed : seeds) {
			BigInteger seed = new BigInteger(sseed);
			BigInteger soil = mapValue(seedToSoilMap, seed);
			BigInteger fertilizer = mapValue(soilToFertilizerMap, soil);
			BigInteger water = mapValue(fertilizerToWaterMap, fertilizer);
			BigInteger light = mapValue(waterToLightMap, water);
			BigInteger temperature = mapValue(lightToTemperatureMap, light);
			BigInteger humidity = mapValue(temperatureToHumidityMap, temperature);
			BigInteger location = mapValue(humidityToLocationMap, humidity);

			lowestLocation = lowestLocation.min(location);
		}


		return lowestLocation;
	}

	public String getMap(String mapName, String almanac) {
		String map = "";

		String[] parts = almanac.split("\n\n");

		for (String part : parts) {
			if (part.trim().toLowerCase().startsWith(mapName.toLowerCase())) {
				map = part;
				break;
			}
		}

		map = map.substring(map.indexOf("\n"));
		return map.trim();
	}

	public BigInteger mapValue(String map, BigInteger input) {
		String[] mapRows = map.split("\n");

		for (String row : mapRows) {
			String[] parts = row.split("\\s");
			BigInteger outIndex = new BigInteger(parts[0]);
			BigInteger inIndex = new BigInteger(parts[1]);
			BigInteger range = new BigInteger(parts[2]);

			if (input.compareTo(inIndex) >= 0 && input.compareTo(inIndex.add(range)) < 0) {
				BigInteger difference = input.subtract(inIndex);
				return outIndex.add(difference);
			}
		}

		return input;
	}
}
