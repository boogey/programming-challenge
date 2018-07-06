package de.exxcellent.challenge.data;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class WeatherDataTest {

    private void assertWeatherData(final WeatherData data) {
        assertSoftly(s -> {
            s.assertThat(data.getDay())
                    .as("Has default day value")
                    .isEqualTo(0);
            s.assertThat(data.getMinTemp())
                    .as("Has default min temp")
                    .isEqualTo(0.0);
            s.assertThat(data.getMaxTemp())
                    .as("Has default max temp")
                    .isEqualTo(0.0);
        });
    }

    @TestFactory
    @DisplayName("Constructor tests")
    Collection<DynamicTest> constructors() {
        return Arrays.asList(
                dynamicTest("Non arg constructor", () -> assertWeatherData(new WeatherData())),
                dynamicTest("All arg constructor",
                        () -> assertWeatherData(
                                new WeatherData(RandomUtils.nextInt(), RandomUtils.nextInt(), RandomUtils.nextInt()))));
    }

    @TestFactory
    @DisplayName("Property Tests")
    Collection<DynamicTest> getterSetter() {
        return Arrays.asList(setterGetterTest("day", RandomUtils.nextInt()),
                setterGetterTest("minTemp", RandomUtils.nextInt()),
                setterGetterTest("maxTemp", RandomUtils.nextInt()));
    }

    DynamicTest setterGetterTest(final String property, final Object value) {
        return dynamicTest(String.format("Set-Get property %s", property), () -> {
            final WeatherData data = new WeatherData();
            try {
                PropertyUtils.setSimpleProperty(data, property, value);

                final Object dataValue = PropertyUtils.getSimpleProperty(data, property);
                assertThat(dataValue)
                        .as("Has %s", value)
                        .isEqualTo(value);
            } catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                fail("Unable to set or get property", e);
            }
        });
    }
}
