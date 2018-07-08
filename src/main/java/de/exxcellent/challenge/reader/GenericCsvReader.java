package de.exxcellent.challenge.reader;

import com.google.common.base.Preconditions;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Iterator;

/**
 * Generic csv reader that use the jackson csv reader mechanism inside. Class implements the {@link IDataProvider} and
 * Reader could be configured based on the {@link IDataSource} and the expected POJO class.
 *
 * @param <D> as generic type that contains the expected POJO class reference.
 */
public class GenericCsvReader<D> implements IDataProvider<D>, Closeable {

    private Iterator<D> dataContent;

    private IDataSource source;

    private Class<D> pojoClass;

    /**
     * Creates an new instance of {@link GenericCsvReader}.
     */
    public GenericCsvReader() {
        this.dataContent = Collections.emptyIterator();
    }

    private void configureReaderInternal(@NonNull final Reader reader, @NonNull final Class<D> pojoClass) {
        final CsvToBean<D> csv = new CsvToBeanBuilder<D>(reader)
                .withSeparator(',')
                .withType(pojoClass)
                .build();

        dataContent = csv.iterator();
    }

    @Override
    public void requestData() throws IOException {
        configureReaderInternal(source.getSource(Reader.class), pojoClass);
    }

    /**
     * Configure the csv reader directly.
     *
     * @param source    as {@link IDataSource} to specify the source of the csv encoded structure.
     * @param pojoClass as {@link Class} that represent the expected data class to directly create type-safe the
     *                  object instances.
     */
    public void configureReader(@NonNull final IDataSource source, @NonNull final Class<D> pojoClass) {
        Preconditions.checkArgument(source.provideSourceType(Reader.class), "data source not providing Reader");

        this.source = source;
        this.pojoClass = pojoClass;
    }

    @Override
    public boolean hasNext() {
        return dataContent.hasNext();
    }

    @Override
    public D next() {
        return dataContent.next();
    }

    @Override
    public void close() throws IOException {
        source.getSource(Reader.class).close();
    }
}
