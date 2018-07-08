package de.exxcellent.challenge.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class to store information regarding the football table data.
 * <p>
 * Current version only stores information of team name, goals and goals allowed.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballTeamData {

    /**
     * Property of number of day.
     */
    @JsonProperty("Team")
    private String team = "";

    /**
     * Property of the minimum of temperature of the day.
     */
    @JsonProperty("Goals")
    private int goals = 0;

    /**
     * Property of the maximum of temperature of the day.
     */
    @JsonProperty("Goals Allowed")
    private int goalsAllowed = 0;

}
