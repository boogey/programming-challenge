package de.exxcellent.challenge.data;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class WeatherDataTest {

    private void assertWeatherData(
            final WeatherData data, final String expectedDay, final int expectedMin, final int expectedMax) {
        assertSoftly(s -> {
            s.assertThat(data.getDay())
                    .as("Has default day value")
                    .isEqualTo(expectedDay);
            s.assertThat(data.getMinTemp())
                    .as("Has default min temp")
                    .isEqualTo(expectedMin);
            s.assertThat(data.getMaxTemp())
                    .as("Has default max temp")
                    .isEqualTo(expectedMax);
        });
    }

    @TestFactory
    @DisplayName("Constructor tests")
    Collection<DynamicTest> constructors() {
        return Arrays.asList(
                dynamicTest("Non arg constructor",
                        () -> assertWeatherData(new WeatherData(), "", 0, 0)),
                dynamicTest("All arg constructor",
                        () -> {
                            final String day = UUID.randomUUID().toString();
                            final int min = RandomUtils.nextInt();
                            final int max = RandomUtils.nextInt();
                            assertWeatherData(new WeatherData(day, min, max), day, min, max);
                        }));
    }

    @TestFactory
    @DisplayName("Property Tests")
    Collection<DynamicTest> getterSetter() {
        return Arrays.asList(setterGetterTest("day", UUID.randomUUID().toString()),
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
