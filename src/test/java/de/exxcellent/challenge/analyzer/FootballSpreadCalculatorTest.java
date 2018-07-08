package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.FootballTeamData;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class FootballSpreadCalculatorTest {

    private FootballSpreadCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new FootballSpreadCalculator();
    }

    @Test
    @DisplayName("Null-Handling of parameter")
    @SuppressWarnings("ReturnValueIgnored")
    void nullParameter() {
        assertThatNullPointerException()
                .isThrownBy(() -> calculator.calculate(null))
                .as("Pass null to calculator")
                .withMessageContaining("teamData");
    }

    @Test
    @DisplayName("<max> - <min> = <spread>")
    void calculation() {
        final int goals = RandomUtils.nextInt();
        final int goalsAllowed = RandomUtils.nextInt();
        final FootballTeamData data = new FootballTeamData(UUID.randomUUID().toString(), goals, goalsAllowed);

        assertThat(calculator.calculate(data))
                .as("Has calculation result")
                .isEqualTo(goals - goalsAllowed);
    }

}
