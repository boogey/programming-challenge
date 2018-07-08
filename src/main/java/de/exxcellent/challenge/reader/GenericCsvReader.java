package de.exxcellent.challenge.reader;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NonNull;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class GenericCsvReader<D> {


    public Iterator<D> readFrom(@NonNull final Reader reader, @NonNull final Class<D> pojoClass) throws IOException {
        final CsvMapper mapper = new CsvMapper();
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        final CsvSchema schema = mapper.schemaFor(pojoClass).withHeader();
        return mapper
                .readerFor(pojoClass)
                .with(schema)
                .readValues(reader);
    }
}
