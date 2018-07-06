package de.exxcellent.challenge.data;

import lombok.Data;

/**
 * Data class to store information regarding the weather data.
 * <p>
 * Current version only stores information of day, maximum and minimum temperature values.
 * </p>
 */
@Data
public class WeatherData {

    /**
     * Property of number of day.
     */
    private int day;

    /**
     * Property of the maximum of temperature of the day.
     */
    private double maxTemp;

    /**
     * Property of the minimum of temperature of the day.
     */
    private double minTemp;
}
