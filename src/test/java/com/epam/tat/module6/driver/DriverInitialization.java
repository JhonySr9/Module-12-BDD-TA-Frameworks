package com.epam.tat.module6.driver;

import com.epam.tat.module6.utils.DefaultElementHighlighter;
import com.epam.tat.module6.utils.HighlightListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverInitialization {

    private static WebDriver driver;

    /**
     * Retrieves and configures the WebDriver instance based on the specified browser.
     * If no browser is specified or recognized, defaults to ChromeDriver.
     * Applies the Singleton Pattern.
     *
     * @return WebDriver instance configured based on the browser property.
     */
    public static synchronized WebDriver getDriver(){

        if (driver == null) {
            switch (System.getProperty("browser").toLowerCase()) {
                case "chrome": {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "safari": {
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                }
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + System.getProperty("browser"));
            }
        }
        HighlightListener highlightListener = new HighlightListener(new DefaultElementHighlighter());
        return new EventFiringDecorator<>(highlightListener).decorate(driver);
    }

    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null; // Reset driver instance after quitting.
        }
    }
}
