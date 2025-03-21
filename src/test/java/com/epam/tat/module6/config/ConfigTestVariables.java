package com.epam.tat.module6.config;

public class ConfigTestVariables {

    private static final ThreadLocal<ConfigTestVariables> instance = ThreadLocal.withInitial(ConfigTestVariables::new);

    // Variable to select the Environment
    public final String ENVIRONMENT;
    public final String HOMEPAGE_URL;
    public final String BROWSER;
    public final String HEADLESS;
    public final String VALID_USERNAME;
    public final String VALID_PASSWORD;
    public final int FIRST_PRODUCT_NUMBER;
    public final int SECOND_PRODUCT_NUMBER;

    public ConfigTestVariables() {
        this.ENVIRONMENT = "dev"; // dev, staging;
        this.HOMEPAGE_URL = PropertiesLoader.getProperty("BASE_URL");
        this.BROWSER = PropertiesLoader.getProperty("BROWSER");
        this.HEADLESS = PropertiesLoader.getProperty("HEADLESS");
        this.VALID_USERNAME = PropertiesLoader.getProperty("TEST_USER");
        this.VALID_PASSWORD = PropertiesLoader.getProperty("TEST_PASSWORD");
        this.FIRST_PRODUCT_NUMBER = 1;
        this.SECOND_PRODUCT_NUMBER = 2;
    }

    public static ConfigTestVariables getVariables() {
        return instance.get();
    }

    public static void clearVariables() {
        instance.remove();
    }

}
