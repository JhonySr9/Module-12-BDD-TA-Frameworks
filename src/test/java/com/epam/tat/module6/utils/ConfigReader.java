package com.epam.tat.module6.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.epam.tat.module6.tests.BaseTests.ENVIRONMENT;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            String env = System.getProperty("env", ENVIRONMENT).toLowerCase();
            String filePath = "src/test/resources/" + env + ".properties";

            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("The properties file couldn't be loaded");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
