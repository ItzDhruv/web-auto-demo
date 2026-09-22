package org.example.configreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    // Load config.properties once when the class is loaded
    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Unable to find config.properties in classpath"
                );
            }

            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties", e
            );
        }
    }


    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
