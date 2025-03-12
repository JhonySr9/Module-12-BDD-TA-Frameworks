package com.epam.tat.module6.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public final Logger log = LogManager.getRootLogger();

    protected BasePage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }


    // METHODS

    protected void click(WebElement webElement) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();

        } catch (TimeoutException | NoSuchElementException e) {
            log.error("Element " + webElement + " was not found.");
            throw new RuntimeException("Test has been stopped.", e);
        }
    }

    protected void addText(WebElement webElement, String text) {

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement)).clear();
            webElement.sendKeys(text);

        } catch (TimeoutException | NoSuchElementException e) {
            log.error("Element " + webElement + " was not found.");
            throw new RuntimeException("Test has been stopped.", e);
        }
    }

    protected String getText(WebElement webElement) {

        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement)).getText();

        } catch (TimeoutException | NoSuchElementException e) {
            log.error("Element " + webElement + " was not found.");
            throw new RuntimeException("Test has been stopped.", e);
        }
    }

    protected void alert_acceptAlert() {


        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

        } catch (NoAlertPresentException | NoSuchElementException e) {
            log.error("No alert was found.");
            throw new RuntimeException("Test has been stopped.", e);
        }
    }

    protected String alert_getAlertText() {


        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();

        } catch (NoAlertPresentException | NoSuchElementException e) {
            log.error("No alert was found.");
            throw new RuntimeException("Test has been stopped.", e);
        }
    }
}
