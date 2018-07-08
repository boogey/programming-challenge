package de.exxcellent.challenge.analyzer;

import lombok.NonNull;

/**
 * Interface to calculate the spread between two numeric values provided by the generic data type.
 *
 * @param <D> as generic type that provides the integer values.
 */
public interface ISpreadCalculator<D> {

    /**
     * Calculate the spread between two numeric values, that are provided both from passed data object.
     *
     * @param data as generic type that should provide the numeric values to calculate the spread.
     * @return the calculated integer value.
     */
    int calculate(@NonNull D data);
}
