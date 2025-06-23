package com.myWork.Assessment.tests.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class responsible for loading configuration values from the {@code config.properties} file.
 * <p>
 * The file is expected to be located in the classpath (typically under {@code resources/}).
 * Provides static access to property values as {@code String} or {@code boolean}.
 */
public class ConfigLoader {

    private static final Properties properties = new Properties();


    // Static initializer block: loads properties at class loading time
    static {
        try (InputStream input = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Cannot find config.properties in classpath");
            }

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error when uploading config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    /**
     * Retrieves the value of a property by key and parses it as a boolean.
     *
     * @param key the name of the property
     * @return {@code true} if the property value is "true" (case-insensitive), otherwise {@code false}
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
