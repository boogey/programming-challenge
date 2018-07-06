package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.WeatherData;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class WeatherSpreadCalculatorTest {

    private Function<WeatherData, Integer> calculator;

    @BeforeEach
    void setUp() {
        // TODO create instance of calculator
        calculator = new WeatherSpreadCalculator();
    }

    @Test
    @DisplayName("Null-Handling of parameter")
    void nullParameter() {
        assertThatNullPointerException()
                .isThrownBy(() -> calculator.apply(null))
                .as("Pass null to calculator")
                .withMessageContaining("weatherData");
    }

    @Test
    @DisplayName("<max> - <min> = <spread>")
    void calculation() {
        final int min = RandomUtils.nextInt();
        final int max = RandomUtils.nextInt();
        final WeatherData data = new WeatherData(RandomUtils.nextInt(), min, max);

        assertThat(calculator.apply(data))
                .as("Has calculation result")
                .isEqualTo(max - min);
    }

}
