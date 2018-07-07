package de.exxcellent.challenge.reader;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.exxcellent.challenge.data.WeatherData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherDataReaderTest {

    @Test
    @DisplayName("Read CSV file")
    void tryToReadCsv() throws IOException {
        final CsvMapper mapper = new CsvMapper();
        final ObjectReader reader = mapper.readerFor(WeatherData.class).with(CsvSchema.emptySchema().withHeader());
        final WeatherData data = reader.readValue(
                ClassLoader.getSystemResourceAsStream("de/exxcellent/challenge/weather.csv"));

        assertThat(data)
                .as("not Null")
                .isNotNull();
    }
}
