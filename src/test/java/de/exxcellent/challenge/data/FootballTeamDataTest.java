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

class FootballTeamDataTest {

    private void assertFootballTeamData(
            final FootballTeamData data, final String expectedTeam, final int expectedGoals, final int expectedGoalsAllowed) {
        assertSoftly(s -> {
            s.assertThat(data.getTeam())
                    .as("Has default day value")
                    .isEqualTo(expectedTeam);
            s.assertThat(data.getGoals())
                    .as("Has default min temp")
                    .isEqualTo(expectedGoals);
            s.assertThat(data.getGoalsAllowed())
                    .as("Has default max temp")
                    .isEqualTo(expectedGoalsAllowed);
        });
    }

    @TestFactory
    @DisplayName("Constructor tests")
    Collection<DynamicTest> constructors() {
        return Arrays.asList(
                dynamicTest("Non arg constructor",
                        () -> assertFootballTeamData(new FootballTeamData(), "", 0, 0)),
                dynamicTest("All arg constructor",
                        () -> {
                            final String team = UUID.randomUUID().toString();
                            final int min = RandomUtils.nextInt();
                            final int max = RandomUtils.nextInt();
                            assertFootballTeamData(new FootballTeamData(team, min, max), team, min, max);
                        }));
    }

    @TestFactory
    @DisplayName("Property Tests")
    Collection<DynamicTest> getterSetter() {
        return Arrays.asList(setterGetterTest("team", UUID.randomUUID().toString()),
                setterGetterTest("goals", RandomUtils.nextInt()),
                setterGetterTest("goalsAllowed", RandomUtils.nextInt()));
    }

    DynamicTest setterGetterTest(final String property, final Object value) {
        return dynamicTest(String.format("Set-Get property %s", property), () -> {
            final FootballTeamData data = new FootballTeamData();
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
