package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.WeatherData;
import lombok.NonNull;

import java.util.function.Function;

/**
 * Functional implementation to calculate the spread between the minimum and maximum temperature of the passing {@link WeatherData}.
 */
public class WeatherSpreadCalculator implements Function<WeatherData, Integer> {

    @Override
    public Integer apply(@NonNull final WeatherData weatherData) {
        return weatherData.getMaxTemp() - weatherData.getMinTemp();
    }
}
