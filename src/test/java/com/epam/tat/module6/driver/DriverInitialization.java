package com.epam.tat.module6.driver;

import com.epam.tat.module6.utils.HighlightListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverInitialization extends HighlightListener {

    private static WebDriver driver;

    /**
     * Retrieves and configures the WebDriver instance based on the specified browser.
     * If no browser is specified or recognized, defaults to ChromeDriver.
     * Initializes and maximizes the browser window.
     *
     * @return WebDriver instance configured based on the browser property.
     */
    public static WebDriver getDriver(){

        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "chrome": {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver = new EventFiringDecorator<>(new HighlightListener()).decorate(driver);
                    break;
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver = new EventFiringDecorator<>(new HighlightListener()).decorate(driver);
                    break;
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver = new EventFiringDecorator<>(new HighlightListener()).decorate(driver);
                    break;
                }
            }
        }
        return driver;
    }


    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null; // Reset driver instance after quitting.
        }
    }
}
