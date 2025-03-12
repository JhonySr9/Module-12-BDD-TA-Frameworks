package com.epam.tat.module6.tests;

import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.model.Customer;
import com.epam.tat.module6.driver.DriverInitialization;
import com.epam.tat.module6.utils.ConfigReader;
import com.epam.tat.module6.utils.Random;
import com.epam.tat.module6.utils.TestListener;
import com.epam.tat.module6.utils.Time;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;

@Listeners({TestListener.class})
public class BaseTests {

    public WebDriver driver;

    // Variable to select the Environment
    public static String ENVIRONMENT = "dev"; // dev, staging
    public String HOMEPAGE_URL = ConfigReader.getProperty("BASE_URL");
    public String BROWSER = ConfigReader.getProperty("BROWSER");
    public String HEADLESS = ConfigReader.getProperty("HEADLESS");
    public String VALID_USERNAME = ConfigReader.getProperty("TEST_USER");
    public String VALID_PASSWORD = ConfigReader.getProperty("TEST_PASSWORD");
    public int FIRST_PRODUCT_NUMBER = 1;
    public int SECOND_PRODUCT_NUMBER = 2;

    // Pages
    public HomePage homePage;
    public ProductPage productPage;
    public CartPage cartPage;
    public OrderPage orderPage;

    // Utils
    public Time time;
    public Customer validCustomer;
    public Random random;

    public final Logger log = LogManager.getRootLogger();

    /**
     * Sets up global system properties before running the test suite.
     * It initializes "browser" property.
     */
    @BeforeSuite (groups = {"setting"})
    public void setProperties(){
        System.setProperty("env", ENVIRONMENT);
        System.setProperty("browser", BROWSER);
        System.setProperty("headless", HEADLESS);
    }

    @BeforeMethod (groups = "setting")
    public void setUp(ITestResult result) {
        driver = DriverInitialization.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
        log.info("----------------");
        log.info("Starting test: '" + result.getMethod().getMethodName() + "'");
    }

    @AfterMethod (groups = "setting")
    public void tearDown(ITestResult result) {
        DriverInitialization.closeDriver();
        log.info("Test: '" + result.getMethod().getMethodName() + "', finished successfully.");
        log.info("----------------");
    }

}
