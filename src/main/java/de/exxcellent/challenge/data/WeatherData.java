package de.exxcellent.challenge.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class to store information regarding the weather data.
 * <p>
 * Current version only stores information of day, maximum and minimum temperature values.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {

    /**
     * Property of number of day.
     */
    @CsvBindByName(column = "Day")
    private String day = "";

    /**
     * Property of the minimum of temperature of the day.
     */
    @CsvBindByName(column = "MnT")
    private int minTemp = 0;

    /**
     * Property of the maximum of temperature of the day.
     */
    @CsvBindByName(column = "MxT")
    private int maxTemp = 0;
}
