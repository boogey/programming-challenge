package de.exxcellent.challenge.analyzer;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SmallestSpreadAnalyzer<D> {

    public Set<D> call(@NonNull final Map<D, Integer> spreadMap) {
        final Optional<Integer> smallestNumber = spreadMap.values()
                .stream()
                .sorted()
                .findFirst();

        Preconditions.checkState(smallestNumber.isPresent(), "Passed map is empty");

        return spreadMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == smallestNumber.get())
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }
}
