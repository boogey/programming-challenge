package de.exxcellent.challenge.data;

import com.opencsv.bean.CsvBindByName;
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
public class FootballTeamData {

    /**
     * Property of number of day.
     */
    @CsvBindByName(column = "Team")
    private String team = "";

    /**
     * Property of the minimum of temperature of the day.
     */
    @CsvBindByName(column = "Goals")
    private int goals = 0;

    /**
     * Property of the maximum of temperature of the day.
     */
    @CsvBindByName(column = "Goals Allowed")
    private int goalsAllowed = 0;

}
