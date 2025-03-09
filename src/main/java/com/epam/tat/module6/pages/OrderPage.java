package com.epam.tat.module6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends BasePage{

    @FindBy(id = "name")
    WebElement nameTextBar;

    @FindBy (id = "country")
    WebElement countryTextBar;

    @FindBy (id = "city")
    WebElement cityTextBar;

    @FindBy (id = "card")
    WebElement creditCardTextBar;

    @FindBy (id = "month")
    WebElement monthTextBar;

    @FindBy (id = "year")
    WebElement yearTextBar;

    @FindBy (xpath = "//button[normalize-space()='Purchase']")
    WebElement purchaseButton;

    @FindBy (css = ".lead.text-muted")
    WebElement purchaseResult;

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnPurchaseButton() {
        click(purchaseButton);
    }

    public void order_addName(String name) {
        addText(nameTextBar, name);
    }

    public void order_addCountry(String country) {
        addText(countryTextBar, country);
    }

    public void order_addCity(String city) {
        addText(cityTextBar, city);
    }

    public void order_addCreditCard(String card) {
        addText(creditCardTextBar, card);
    }

    public void order_addMonth(String month) {
        addText(monthTextBar, month);
    }

    public void order_addYear(String year) {
        addText(yearTextBar, year);
    }

    public boolean isValueCorrect(String expectedValue, String parameter) {
        String result = getText(purchaseResult);
        return result.contains(parameter + ": " + expectedValue);
    }

}
