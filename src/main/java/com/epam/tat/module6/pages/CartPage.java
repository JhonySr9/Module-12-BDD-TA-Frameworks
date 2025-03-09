package com.epam.tat.module6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    public CartPage (WebDriver driver) {
        super(driver);
    }

    @FindBy (css = ".btn-success")
    WebElement placeOrderButton;

    @FindBy (id = "totalp")
    WebElement totalAmount;


    public void clickPlaceOrderButton() {
        click(placeOrderButton);
    }
    public void deleteOneProduct(int productNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[normalize-space()='Delete'])["+ productNumber +"]")))
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//a[normalize-space()='Delete'])["+ productNumber +"]")));
    }

    public String getTotalAmount() {
        return getText(totalAmount);
    }

    public boolean isTheProductInCart(String addedProduct) {
        return findProductByName(addedProduct).isDisplayed();
    }
    public Boolean isTotalAmountCorrect(String expectedTotalAmount) {
        List<Integer> productPrices = getProductPrices();
        int totalAmount = calculateTotalAmount(productPrices);
        return expectedTotalAmount.equals(String.valueOf(totalAmount));
    }

    public String getProductPrice (int productNumber) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[3])[" + productNumber + "]"))).getText();
    }

    public int getNumberOfProductsInCart() {
        return getTotalOfProductsInCar().size();
    }

    private WebElement findProductByName(String name) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='"+ name +"']")));
    }

    private List<Integer> getProductPrices() {
        List<WebElement> products = getTotalOfProductsInCar();
        List<Integer> productPrices = new ArrayList<>();

        for (WebElement element : products) {
            productPrices.add(Integer.parseInt(element.getText()));
        }

        return productPrices;
    }

    private int calculateTotalAmount(List<Integer> prices) {
        int totalAmount = 0;
        for (Integer price : prices) {
            totalAmount += price;
        }
        return totalAmount;
    }

    private List<WebElement> getTotalOfProductsInCar() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//td[3])")));
    }
}
