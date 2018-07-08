package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.data.WeatherData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.mock;

class GenericCsvReaderTest {

    @TestFactory
    @DisplayName("Null behavior")
    Collection<DynamicTest> readFromNullChecks() {
        final GenericCsvReader<Object> reader = new GenericCsvReader<>();
        return Arrays.asList(dynamicTest("InputStream is null", () ->
                assertThatNullPointerException()
                        .isThrownBy(() -> reader.configureReader(null, Object.class))
        ), dynamicTest("Class is null", () ->
                assertThatNullPointerException()
                        .isThrownBy(() -> reader.configureReader(mock(Reader.class), null))
        ));
    }

    StringReader prepareWeatherData() {
        return new StringReader(
                "Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP\n" +
                        "1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5\n" +
                        "2,79,63,71,46.5,0,330,8.7,340,23,3.3,70,28,1004.5");
    }

    @Test
    @DisplayName("Read WeatherData")
    void readWeatherData() {

        try (final Reader reader = prepareWeatherData()) {
            final GenericCsvReader<WeatherData> csvReader = new GenericCsvReader<>();
            csvReader.configureReader(reader, WeatherData.class);
            assertSoftly(s -> {
                s.assertThat(csvReader.requestData())
                        .as("Request success")
                        .isTrue();
                s.assertThat(csvReader.hasNext())
                        .as("Reader contains data")
                        .isTrue();
            });
        } catch (final IOException ioe) {
            fail("Unable to read from StringReader", ioe);
        }
    }

    @Test
    @DisplayName("Check CSV Parser")
    void checkWeatherData() {

        try (final Reader reader = prepareWeatherData()) {
            final GenericCsvReader<WeatherData> csvReader = new GenericCsvReader<>();
            csvReader.configureReader(reader, WeatherData.class);
            final WeatherData first = csvReader.next();
            assertSoftly(s -> {
                s.assertThat(first.getDay())
                        .isEqualTo("1");
                s.assertThat(first.getMinTemp())
                        .isEqualTo(59);
                s.assertThat(first.getMaxTemp())
                        .isEqualTo(88);
            });
        } catch (final IOException ioe) {
            fail("Unable to read from StringReader", ioe);
        }
    }
}
