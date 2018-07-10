package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.FootballSpreadCalculator;
import de.exxcellent.challenge.analyzer.ISpreadCalculator;
import de.exxcellent.challenge.analyzer.WeatherSpreadCalculator;
import de.exxcellent.challenge.data.FootballTeamData;
import de.exxcellent.challenge.data.WeatherData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.mock;

class TaskSolverTest {

    @SuppressWarnings(value = "unchecked")
    @TestFactory
    Collection<DynamicTest> nullConstructorValues() {
        return Arrays.asList(
                dynamicTest("file is <null>",
                        () -> assertThatNullPointerException()
                                .isThrownBy(() -> new TaskSolver<>(
                                        null, mock(ISpreadCalculator.class), Object.class))),
                dynamicTest("ISpreadCalculator is <null>",
                        () -> assertThatNullPointerException()
                                .isThrownBy(() -> new TaskSolver<>(
                                        UUID.randomUUID().toString(), null, Object.class))),
                dynamicTest("Class is <null>",
                        () -> assertThatNullPointerException()
                                .isThrownBy(() -> new TaskSolver<Object>(
                                        UUID.randomUUID().toString(), mock(ISpreadCalculator.class), null))));
    }

    @TestFactory
    @DisplayName("Task solver")
    Collection<DynamicTest> taskSolvers() {
        return Arrays.asList(
                dynamicTest("Get Weather result",
                        () -> assertTaskResult(WeatherData.class, new WeatherSpreadCalculator())),
                dynamicTest("Get Football result",
                        () -> assertTaskResult(FootballTeamData.class, new FootballSpreadCalculator())));
    }

    private <D> void assertTaskResult(final Class<D> pojoClass, final ISpreadCalculator<D> calculator) {
        final TaskSolver<D> task = new TaskSolver<>("/de/exxcellent/challenge/weather.csv",
                calculator,
                pojoClass);
        try {
            final Set<D> data = task.call();

            assertThat(data.isEmpty())
                    .isFalse();
        } catch (final Exception e) {
            fail("Exception was thrown", e);
        }
    }

    @Test
    @DisplayName("Invalid task")
    void invalidSolver() {
        final TaskSolver<Object> task = new TaskSolver<>(UUID.randomUUID().toString(),
                (d) -> 1,
                Object.class);

        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> task.call());
    }
}