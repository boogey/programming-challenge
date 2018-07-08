package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.FootballSpreadCalculator;
import de.exxcellent.challenge.analyzer.WeatherSpreadCalculator;
import de.exxcellent.challenge.data.FootballTeamData;
import de.exxcellent.challenge.data.WeatherData;

import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static void main(final String... args) {

        final ExecutorService executor = Executors.newCachedThreadPool();
        final CompletionService<Supplier<String>> service = new ExecutorCompletionService<>(executor);
        service.submit(() -> {
            final TaskSolver<WeatherData> task = new TaskSolver<>("de/exxcellent/challenge/weather.csv",
                    new WeatherSpreadCalculator(),
                    WeatherData.class);
            final Set<WeatherData> data = task.call();
            return () -> {
                final StringBuilder builder = new StringBuilder();
                data.stream()
                        .map(d -> String.format("Day with smallest temperature spread : %s%n", d.getDay()))
                        .forEach(builder::append);
                return builder.toString();
            };
        });

        service.submit(() -> {
            final TaskSolver<FootballTeamData> task = new TaskSolver<>("de/exxcellent/challenge/football.csv",
                    new FootballSpreadCalculator(),
                    FootballTeamData.class);
            final Set<FootballTeamData> data = task.call();
            return () -> {
                final StringBuilder builder = new StringBuilder();
                data.stream()
                        .map(d -> String.format("Team with smallest goal spread       : %s%n", d.getTeam()))
                        .forEach(builder::append);
                return builder.toString();
            };
        });

        for (int i = 0; i < 2; i++) {
            try {
                final Future<Supplier<String>> result = service.take();
                System.out.println(result.get().get());
            } catch (final InterruptedException e) {
                // Something went wrong with a task submitted
                System.out.println("Error Interrupted exception");
                e.printStackTrace();
            } catch (final ExecutionException e) {
                // Something went wrong with the result
                e.printStackTrace();
                System.out.println("Error get() threw exception");
            } finally {
                executor.shutdownNow();
            }
        }
    }
}
