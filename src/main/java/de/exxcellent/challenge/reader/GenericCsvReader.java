package de.exxcellent.challenge.reader;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NonNull;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class GenericCsvReader<D> implements IDataProvider<D> {


    private Iterator<D> dataContent;

    private ObjectReader reader;

    public GenericCsvReader() {
        this.dataContent = Collections.emptyIterator();
    }

    public void configureReader(@NonNull final Reader reader, @NonNull final Class<D> pojoClass) {
        final CsvMapper mapper = new CsvMapper();
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        final CsvSchema schema = mapper.schemaFor(pojoClass).withHeader();

        try {
            dataContent = mapper
                    .readerFor(pojoClass)
                    .with(schema)
                    .readValues(reader);
        } catch (final IOException ioe) {
            dataContent = null;
        }
    }

    @Override
    public boolean requestData() {
        return Objects.nonNull(dataContent);
    }

    @Override
    public boolean hasNext() {
        return dataContent.hasNext();
    }

    @Override
    public D next() {
        return dataContent.next();
    }
}
