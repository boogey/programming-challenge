package de.exxcellent.challenge.analyzer;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SmallestSpreadAnalyzerTest {

    @Test
    @DisplayName("Check valid single object choose")
    void smallestObject() {
        assertSmallestObjects(1);
    }

    @Test
    @DisplayName("Check valid multiple object choose")
    void smallestObjects() {
        assertSmallestObjects(RandomUtils.nextInt(2, 10));
    }

    void assertSmallestObjects(final int number) {
        final Map<Object, Integer> data = new HashMap<>();
        final Set<Object> smallestObjectList = new HashSet<>();
        for (int i = 0; i < number; i++) {
            final Object smallest = new Object();
            smallestObjectList.add(smallest);
            data.put(smallest, 0);
        }
        data.put(new Object(), 2);

        final SmallestSpreadAnalyzer<Object> analyzer = new SmallestSpreadAnalyzer<>();

        final Set<Object> smallestObject = analyzer.call(data);

        assertThat(smallestObject)
                .containsOnly(smallestObjectList.toArray());
    }
}