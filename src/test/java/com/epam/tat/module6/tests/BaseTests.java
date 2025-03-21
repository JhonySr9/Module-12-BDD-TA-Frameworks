package com.epam.tat.module6.tests;

import com.epam.tat.module6.config.ConfigTestVariables;
import com.epam.tat.module6.config.InitTestInstances;
import com.epam.tat.module6.driver.DriverInitialization;
import com.epam.tat.module6.model.LoggingCustomerDecorator;
import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.time.Duration;

import static com.epam.tat.module6.config.ConfigTestVariables.*;
import static com.epam.tat.module6.config.InitTestInstances.*;

@Listeners({TestListener.class})
public class BaseTests {

    // General Configuration
    public WebDriver driver;
    protected InitTestInstances testInstances;
    protected final ConfigTestVariables variables = getVariables();

    // Instances
    protected Logger log;
    protected LoggingCustomerDecorator customer;
    protected Time time;
    protected Random random;

    // Pages
    protected HomePage homePage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected OrderPage orderPage;

    /**
     * Sets up global system properties before running the test suite.
     * It initializes "browser" property.
     */
    @BeforeSuite (groups = {"setting"})
    public void setProperties(){
        System.setProperty("env", variables.ENVIRONMENT);
        System.setProperty("browser", variables.BROWSER);
        System.setProperty("headless", variables.HEADLESS);
    }

    @BeforeMethod (groups = "setting")
    public void setUp() {
        driver = DriverInitialization.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(variables.HOMEPAGE_URL);

        testInstances = getInstances(driver);
        log = testInstances.log;
        customer = testInstances.customer;
        time = testInstances.time;
        random = testInstances.random;

        testInstances.log.info("----------------");
    }

    @AfterMethod (groups = "setting")
    public void tearDown() {
        testInstances.log.info("----------------");

        DriverInitialization.closeDriver();
        InitTestInstances.removeInstances();
        ConfigTestVariables.clearVariables();
    }

}
