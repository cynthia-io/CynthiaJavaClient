package io.cynthia.client.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class ResourceUtils {

    public static String readResourceFile(@NonNull final String fileName) throws IOException {
        try (final InputStream inputStream = ResourceUtils.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
