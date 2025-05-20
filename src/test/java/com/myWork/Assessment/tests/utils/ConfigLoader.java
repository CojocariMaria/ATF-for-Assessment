package com.myWork.Assessment.tests.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

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

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }


}
