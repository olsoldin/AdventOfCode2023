import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

	public static final @NotNull String SEED_TO_SOIL = "seed-to-soil";
	public static final @NotNull String SOIL_TO_FERTILIZER = "soil-to-fertilizer";
	public static final @NotNull String FERTILIZER_TO_WATER = "fertilizer-to-water";
	public static final @NotNull String WATER_TO_LIGHT = "water-to-light";
	public static final @NotNull String LIGHT_TO_TEMPERATURE = "light-to-temperature";
	public static final @NotNull String TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity";
	public static final @NotNull String HUMIDITY_TO_LOCATION = "humidity-to-location";

	public Day5() {
	}

	public @NotNull BigInteger lowestLocation(@NotNull String almanac) {
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

	public @NotNull BigInteger lowestLocationPart2(@NotNull String almanac) {
		BigInteger lowestLocation = BigInteger.valueOf(Long.MAX_VALUE);

		String[] seedsToProcess = almanac.split("\n")[0]
				.split(":")[1]
				.trim()
				.split(" ");


		List<Thruple<BigInteger, BigInteger, BigInteger>> seedToSoilMap = parseMap(getMap(SEED_TO_SOIL, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> soilToFertilizerMap = parseMap(getMap(SOIL_TO_FERTILIZER, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> fertilizerToWaterMap = parseMap(getMap(FERTILIZER_TO_WATER, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> waterToLightMap = parseMap(getMap(WATER_TO_LIGHT, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> lightToTemperatureMap = parseMap(getMap(LIGHT_TO_TEMPERATURE, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> temperatureToHumidityMap = parseMap(getMap(TEMPERATURE_TO_HUMIDITY, almanac));
		List<Thruple<BigInteger, BigInteger, BigInteger>> humidityToLocationMap = parseMap(getMap(HUMIDITY_TO_LOCATION, almanac));


		for (int i = 0; i < seedsToProcess.length - 1; i++) {
			BigInteger start = new BigInteger(seedsToProcess[i]);
			BigInteger range = new BigInteger(seedsToProcess[++i]);

			while (range.compareTo(BigInteger.ZERO) >= 0) {
				BigInteger soil = mapValue(seedToSoilMap, start.add(range));
				BigInteger fertilizer = mapValue(soilToFertilizerMap, soil);
				BigInteger water = mapValue(fertilizerToWaterMap, fertilizer);
				BigInteger light = mapValue(waterToLightMap, water);
				BigInteger temperature = mapValue(lightToTemperatureMap, light);
				BigInteger humidity = mapValue(temperatureToHumidityMap, temperature);
				BigInteger location = mapValue(humidityToLocationMap, humidity);

				lowestLocation = lowestLocation.min(location);
				range = range.subtract(BigInteger.ONE);
			}
		}


		return lowestLocation;
	}

	public @NotNull String getMap(@NotNull String mapName, @NotNull String almanac) {
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

	public @NotNull List<Thruple<BigInteger, BigInteger, BigInteger>> parseMap(@NotNull String map) {
		List<Thruple<BigInteger, BigInteger, BigInteger>> parsedMaps = new ArrayList<>();

		String[] rows = map.split("\n");

		for (String row : rows) {
			String[] parts = row.split("\\s");
			BigInteger outIndex = new BigInteger(parts[0]);
			BigInteger inIndex = new BigInteger(parts[1]);
			BigInteger range = new BigInteger(parts[2]);

			parsedMaps.add(new Thruple<>(inIndex, outIndex, range));
		}

		return parsedMaps;
	}

	public BigInteger mapValue(@NotNull String map, @NotNull BigInteger input) {
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

	public BigInteger mapValue(@NotNull List<Thruple<BigInteger, BigInteger, BigInteger>> map, @NotNull BigInteger input) {

		for (Thruple<BigInteger, BigInteger, BigInteger> row : map) {
			BigInteger outIndex = row.y();
			BigInteger inIndex = row.x();
			BigInteger range = row.z();

			if (input.compareTo(inIndex) >= 0 && input.compareTo(inIndex.add(range)) < 0) {
				BigInteger difference = input.subtract(inIndex);
				return outIndex.add(difference);
			}
		}

		return input;
	}
}
