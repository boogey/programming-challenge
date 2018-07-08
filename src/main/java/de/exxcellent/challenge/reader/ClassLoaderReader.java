package de.exxcellent.challenge.reader;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

/**
 * IDataSource implementation that provide resources from {@link ClassLoader#getSystemClassLoader()}
 */
public class ClassLoaderReader implements IDataSource {

    private final String classLoaderPath;

    /**
     * Creates an new instance of {@link ClassLoaderReader}.
     *
     * @param classLoaderPath as {@link String} value that should be contain inside this class loader.
     */
    public ClassLoaderReader(@NonNull final String classLoaderPath) {
        this.classLoaderPath = Validate.notBlank(classLoaderPath);
    }

    @Override
    public boolean provideSourceType(final Class<?> sourceType) {
        return ClassUtils.isAssignable(Reader.class, sourceType);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the source was not found inside the {@link ClassLoader#getSystemClassLoader()}, this method will be raise
     * an {@link NullPointerException}
     * </p>
     */
    @Override
    public <S> S getSource(@NonNull final Class<S> expectedSource) throws IOException {
        Preconditions.checkArgument(provideSourceType(expectedSource), "%s is not supported", expectedSource);
        final InputStream is = ClassLoader.getSystemResourceAsStream(classLoaderPath);

        if (Objects.isNull(is)) {
            throw new IOException(String.format("Unable to find %s inside system class loader", classLoaderPath));
        }
        return expectedSource.cast(new InputStreamReader(is));
    }
}
