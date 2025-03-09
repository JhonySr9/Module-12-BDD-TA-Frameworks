package com.epam.tat.module6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Add to cart']")
    WebElement addCartButton;

    @FindBy (id = "cartur")
    WebElement cartSectionLink;

    @FindBy (className = "name")
    WebElement productName;

    @FindBy (id = "nava")
    WebElement productStoreSectionLink;

    public void clickAddToCart(){
        click(addCartButton);
    }

    public void acceptProductAddedAlert() {
        alert_acceptAlert();
    }

    public void clickToMoveToCartSection() {
        click(cartSectionLink);
    }

    public String getProductName() {
        return getText(productName);
    }

    public void clicktoMoveToHomeSection() {
        click(productStoreSectionLink);
    }

}
