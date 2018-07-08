package de.exxcellent.challenge.reader;

import java.io.IOException;

/**
 * Describe the data source provider that could be provide a special source based on the expected class. For generic
 * usage without to know which source is contained, the {@link #provideSourceType(Class)} method could be used to check,
 * if the implementation provide a special source.
 */
public interface IDataSource {

    /**
     * Check the underlying implementation if the passed source type (represent as {@link Class} reference) is
     * supported by interface implementation.
     *
     * @param sourceType as {@link Class} reference that should be supported.
     * @return <code>true</code> if the passed source type is supported. <code>false</code> otherwise.
     */
    boolean provideSourceType(Class<?> sourceType);

    /**
     * Returns the underlying data source type-safe.
     * <p>
     * Method call should be ensure, that the {@link #provideSourceType(Class)} is called previously.
     * </p>
     *
     * @param expectedSource as {@link Class} that match the return value type.
     * @param <S>            as generic class type that is expected from caller point of view.
     * @return the underlying source object.
     * @throws IOException if source could not be found.
     */
    <S> S getSource(Class<S> expectedSource) throws IOException;
}
