package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.FootballTeamData;
import de.exxcellent.challenge.data.WeatherData;
import lombok.NonNull;

/**
 * Functional implementation to calculate the spread between the minimum and maximum temperature of the passing
 * {@link WeatherData}.
 */
public class FootballSpreadCalculator implements ISpreadCalculator<FootballTeamData> {

    @Override
    public int calculate(@NonNull final FootballTeamData teamData) {
        return teamData.getGoals() - teamData.getGoalsAllowed();
    }
}
