package de.exxcellent.challenge.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class ClassLoaderReaderTest {

    private ClassLoaderReader source;

    @BeforeEach
    void setUp() {
        source = new ClassLoaderReader("/test");
    }

    @Test
    @DisplayName("Valid source type")
    void provideSourceType() {
        assertThat(source.provideSourceType(Reader.class))
                .isTrue();
    }

    @Test
    @DisplayName("Base source type")
    void provideBaseSourceType() {
        assertThat(source.provideSourceType(Readable.class))
                .isTrue();
    }

    @Test
    @DisplayName("Invalid source type")
    void notProvideSourceType() {
        assertThat(source.provideSourceType(InputStream.class))
                .isFalse();
    }

    @Test
    @DisplayName("Valid source")
    void getSource() {
        try (final Reader reader = source.getSource(Reader.class)) {
            assertThat(reader)
                    .isNotNull();
        } catch (final IOException ioe) {
            fail("Unexpected exception is thrown", ioe);
        }
    }
}