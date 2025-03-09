package com.epam.tat.module6.tests;

import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.Customer;
import com.epam.tat.module6.utils.DriverInitialization;
import com.epam.tat.module6.utils.Random;
import com.epam.tat.module6.utils.Time;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTests {

    WebDriver driver;
    String HOMEPAGE_URL = "https://www.demoblaze.com/";

    String VALID_USERNAME = "testJ12345";
    String VALID_PASSWORD = "Test@123";
    int FIRST_PRODUCT_NUMBER = 1;
    int SECOND_PRODUCT_NUMBER = 2;

    // Pages
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    OrderPage orderPage;

    // Utils
    Time time;
    Customer validCustomer;
    Random random;

    /**
     * Sets up global system properties before running the test suite.
     * It initializes "browser" property.
     */
    @BeforeSuite (groups = {"setting"})
    public void setProperties(){
        System.setProperty("browser", "chrome");
    }

    @BeforeMethod (groups = "setting")
    public void setUp() {
        driver = DriverInitialization.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
    }

    @AfterMethod (groups = "setting")
    public void tearDown() {
        DriverInitialization.closeDriver();
    }

}
