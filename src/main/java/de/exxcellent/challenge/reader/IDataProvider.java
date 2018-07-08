package de.exxcellent.challenge.reader;

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
     * @return <code>true</code> if the request was successful, <code>false</code> otherwise.
     */
    boolean requestData();
}
