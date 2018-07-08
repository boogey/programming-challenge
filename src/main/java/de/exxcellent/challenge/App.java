package de.exxcellent.challenge;

import com.google.common.collect.ImmutableSet;
import de.exxcellent.challenge.analyzer.SmallestSpreadAnalyzer;
import de.exxcellent.challenge.analyzer.WeatherSpreadCalculator;
import de.exxcellent.challenge.data.WeatherData;
import de.exxcellent.challenge.reader.ClassLoaderReader;
import de.exxcellent.challenge.reader.GenericCsvReader;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static void main(final String... args) {

        final GenericCsvReader<WeatherData> reader = new GenericCsvReader<>();
        final ClassLoaderReader source = new ClassLoaderReader("de/exxcellent/challenge/weather.csv");
        reader.configureReader(source, WeatherData.class);
        if (reader.requestData()) {
            final Set<WeatherData> data = ImmutableSet.copyOf(reader);
            final WeatherSpreadCalculator calculator = new WeatherSpreadCalculator();
            final Map<WeatherData, Integer> calculatedData = data.stream()
                    .collect(Collectors.toMap(Function.identity(), calculator::calculate));
            final SmallestSpreadAnalyzer<WeatherData> analyzer = new SmallestSpreadAnalyzer<>();
            final Set<WeatherData> smallestWeatherData = analyzer.call(calculatedData);

            smallestWeatherData.forEach(d -> System.out.println(String.format("Day with smallest temperature spread : %s%n", d.getDay())));
        } else {
            System.out.println(String.format("No data available from %s", source));
        }

        final String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        final String teamWithSmallesGoalSpread = "A good team"; // Your goal analysis function call …

        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallesGoalSpread);
    }
}
