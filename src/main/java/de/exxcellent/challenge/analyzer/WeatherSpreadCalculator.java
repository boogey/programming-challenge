package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.WeatherData;
import lombok.NonNull;

/**
 * Functional implementation to calculate the spread between the minimum and maximum temperature of the passing {@link WeatherData}.
 */
public class WeatherSpreadCalculator implements ISpreadCalculator<WeatherData> {

    @Override
    public int calculate(@NonNull final WeatherData weatherData) {
        return weatherData.getMaxTemp() - weatherData.getMinTemp();
    }
}
