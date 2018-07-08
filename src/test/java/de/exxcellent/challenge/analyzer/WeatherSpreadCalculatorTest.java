package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.WeatherData;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class WeatherSpreadCalculatorTest {

    private WeatherSpreadCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new WeatherSpreadCalculator();
    }

    @Test
    @DisplayName("Null-Handling of parameter")
    @SuppressWarnings("ReturnValueIgnored")
    void nullParameter() {
        assertThatNullPointerException()
                .isThrownBy(() -> calculator.calculate(null))
                .as("Pass null to calculator")
                .withMessageContaining("weatherData");
    }

    @Test
    @DisplayName("<max> - <min> = <spread>")
    void calculation() {
        final int min = RandomUtils.nextInt();
        final int max = RandomUtils.nextInt();
        final WeatherData data = new WeatherData(UUID.randomUUID().toString(), min, max);

        assertThat(calculator.calculate(data))
                .as("Has calculation result")
                .isEqualTo(max - min);
    }

}
