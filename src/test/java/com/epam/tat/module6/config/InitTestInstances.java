package com.epam.tat.module6.config;

import com.epam.tat.module6.model.LoggingCustomerDecorator;
import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.Random;
import com.epam.tat.module6.utils.Time;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class InitTestInstances {

    private static final ThreadLocal<InitTestInstances> instances = new ThreadLocal<>();

    // Pages
    public final HomePage homePage;
    public final ProductPage productPage;
    public final CartPage cartPage;
    public final OrderPage orderPage;

    // Utils
    public final Time time;
    public final Random random;
    public final Logger log;
    public LoggingCustomerDecorator customer;

    public InitTestInstances(WebDriver driver) {
        this.homePage = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
        this.orderPage = new OrderPage(driver);
        this.time = new Time();
        this.random = new Random();
        this.log = LogManager.getRootLogger();
        this.customer = new LoggingCustomerDecorator(null);
    }

    public static InitTestInstances getInstances(WebDriver driver) {
        if (instances.get() == null) {
            instances.set(new InitTestInstances(driver));
        }
        return instances.get();
    }

    public static void removeInstances() {
        instances.remove();
    }
}
