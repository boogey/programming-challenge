package de.exxcellent.challenge;

import com.google.common.collect.ImmutableSet;
import de.exxcellent.challenge.analyzer.ISpreadCalculator;
import de.exxcellent.challenge.analyzer.SmallestSpreadAnalyzer;
import de.exxcellent.challenge.reader.ClassLoaderReader;
import de.exxcellent.challenge.reader.GenericCsvReader;
import lombok.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskSolver<D> implements Callable<Set<D>> {

    private final String filePath;
    private final ISpreadCalculator<D> calculator;
    private final Class<D> pojoClass;

    TaskSolver(@NonNull final String file, @NonNull final ISpreadCalculator<D> calculator, @NonNull final Class<D> pojoClass) {
        filePath = file;
        this.calculator = calculator;
        this.pojoClass = pojoClass;
    }

    @Override
    public Set<D> call() throws Exception {
        final GenericCsvReader<D> reader = new GenericCsvReader<>();
        final ClassLoaderReader source = new ClassLoaderReader(filePath);
        reader.configureReader(source, pojoClass);
        if (reader.requestData()) {
            final Set<D> data = ImmutableSet.copyOf(reader);
            final Map<D, Integer> calculatedData = data.stream()
                    .collect(Collectors.toMap(Function.identity(), calculator::calculate));
            final SmallestSpreadAnalyzer<D> analyzer = new SmallestSpreadAnalyzer<>();
            return analyzer.call(calculatedData);
        } else {
            throw new IOException(
                    String.format("No valid weatherData found inside system class loader at %s",
                            filePath));
        }
    }
}
