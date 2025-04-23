package stepDefinitions;

import com.epam.tat.module6.config.ConfigTestVariables;
import com.epam.tat.module6.config.InitTestInstances;
import com.epam.tat.module6.driver.DriverInitialization;
import com.epam.tat.module6.model.LoggingCustomerDecorator;
import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.Random;
import com.epam.tat.module6.utils.Time;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

import static com.epam.tat.module6.config.ConfigTestVariables.getVariables;
import static com.epam.tat.module6.config.InitTestInstances.getInstances;

public class Hooks {

    // General Configuration
    public static WebDriver driver;
    public static InitTestInstances testInstances;
    public static ConfigTestVariables variables = getVariables();

    // Instances
    public static Logger log;
    public static LoggingCustomerDecorator customer;
    public static Time time;
    public static Random random;

    // Pages
    public static HomePage homePage;
    public static ProductPage productPage;
    public static CartPage cartPage;
    public static OrderPage orderPage;

    @Before
    public void setUp() {
        System.setProperty("env", variables.ENVIRONMENT);
        System.setProperty("browser", variables.BROWSER);
        System.setProperty("headless", variables.HEADLESS);

        driver = DriverInitialization.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(variables.HOMEPAGE_URL);

        testInstances = getInstances(driver);
        log = testInstances.log;
        customer = testInstances.customer;
        time = testInstances.time;
        random = testInstances.random;

        homePage = testInstances.homePage;
        productPage = testInstances.productPage;
        cartPage = testInstances.cartPage;
        orderPage = testInstances.orderPage;

        testInstances.log.info("----------------");
    }

    @After
    public void tearDown() {
        testInstances.log.info("----------------");
        DriverInitialization.closeDriver();
        InitTestInstances.removeInstances();
        ConfigTestVariables.clearVariables();
    }

}
