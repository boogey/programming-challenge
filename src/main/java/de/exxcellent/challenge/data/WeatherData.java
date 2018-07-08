package de.exxcellent.challenge.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"Day", "MxT", "MnT"})
public class WeatherData {

    /**
     * Property of number of day.
     */
    @JsonProperty("Day")
    private String day = "";

    /**
     * Property of the minimum of temperature of the day.
     */
    @JsonProperty("MnT")
    private int minTemp = 0;

    /**
     * Property of the maximum of temperature of the day.
     */
    @JsonProperty("MxT")
    private int maxTemp = 0;
}
