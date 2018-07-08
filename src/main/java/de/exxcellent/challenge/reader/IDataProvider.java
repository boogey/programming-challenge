package de.exxcellent.challenge.reader;

import java.io.IOException;
import java.util.Iterator;

/**
 * Define lazy data provider that request data from underlying data source. Data source is depend on the implementation
 * class.
 *
 * @param <D> as generic type that specify the data content.
 */
public interface IDataProvider<D> extends Iterator<D> {

    /**
     * Lazy start method to perform the data request.
     *
     * @throws IOException if data could not be successful received
     */
    void requestData() throws IOException;
}
