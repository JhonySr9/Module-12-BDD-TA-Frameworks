package com.epam.tat.module6.tests;

import com.epam.tat.module6.model.CreateCustomer;
import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.Time;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PurchaseTests extends BaseTests{

    @Test (groups = "smoke")
    public void completePurchaseTest() {

        validCustomer = CreateCustomer.defaultCustomer();
        time = new Time();

        // From the HomePage, select a product from the store.
        homePage = new HomePage(driver);
        homePage.selectAProductWithNumber(FIRST_PRODUCT_NUMBER);
        log.info("Selects a Product.");

        // Save the name of the Product and the Total amount value.
        productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        log.info("Obtains Product name.");

        // Click the "Add to cart" button.
        productPage.clickAddToCart();
        log.info("Clicks on 'Add to Cart'.");

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();
        log.info("Closes Alert from Product added.");

        // Navigate to the cart from the top menu by clicking "Cart".
        productPage.clickToMoveToCartSection();
        log.info("Moves to CartPage.");

        // Verify that the added product appears in the cart list.
        cartPage = new CartPage(driver);
        assertTrue(cartPage.isTheProductInCart(productName));
        log.info("Checks the product is present in the Cart.");

        // Save the Total amount value.
        String totalAmount = cartPage.getTotalAmount();
        log.info("Obtains total amount from Cart.");

        // Click the "Place Order" button.
        cartPage.clickPlaceOrderButton();
        log.info("Clicks on 'place order' button.");

        // Fill the form with valid information.
        orderPage = new OrderPage(driver);
        orderPage.order_addName(validCustomer.getName());
        orderPage.order_addCountry(validCustomer.getCountry());
        orderPage.order_addCity(validCustomer.getCity());
        orderPage.order_addCreditCard(validCustomer.getCreditCard());
        orderPage.order_addMonth(time.getActualMonth());
        orderPage.order_addYear(time.getActualYear());
        log.info("Adds information to the order from the Customer.");

        // Click on "Purchase".
        orderPage.clickOnPurchaseButton();

        // Verify that a confirmation message appears with correct information.
        assertTrue(orderPage.isValueCorrect(validCustomer.getName(), "Name"));
        log.info("Checks the name ("+ validCustomer.getName() +") in the order is correct.");
        assertTrue(orderPage.isValueCorrect(validCustomer.getCreditCard(), "Card Number"));
        log.info("Checks the Card Number ("+ validCustomer.getCreditCard() +") in the order is correct.");
        assertTrue(orderPage.isValueCorrect(totalAmount, "Amount"));
        log.info("Checks the Amount ($" + totalAmount +") in the order is correct.");
    }

    @Test (groups = "regression")
    public void removeProductInPurchaseFlowTest() {
        // From the HomePage, select a product from the store.
        homePage = new HomePage(driver);
        homePage.selectAProductWithNumber(FIRST_PRODUCT_NUMBER);
        log.info("Selects a Product.");

        // Click the "Add to cart" button.
        productPage = new ProductPage(driver);
        productPage.clickAddToCart();
        log.info("Clicks on 'Add to Cart'.");

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();
        log.info("Closes Alert from Product added.");

        // Return to the HomePage by clicking the "Product Store" link.
        productPage.clicktoMoveToHomeSection();
        log.info("Moves to Homepage.");

        // Select a second product from the store.
        homePage.selectAProductWithNumber(SECOND_PRODUCT_NUMBER);
        log.info("Selects a second product.");

        // Click the "Add to cart" button.
        productPage.clickAddToCart();
        log.info("Clicks on 'Add to Cart'.");

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();
        log.info("Clicks on 'Add to Cart'.");

        // Navigate to the cart from the top menu by clicking "Cart".
        productPage.clickToMoveToCartSection();
        log.info("Moves to CartPage.");

        // Save the Total amount value.
        cartPage = new CartPage(driver);
        String totalAmount = cartPage.getTotalAmount();
        log.info("Obtains total amount from Cart.");

        // Verify that both products are listed with the correct prices.
        assertTrue(cartPage.isTotalAmountCorrect(totalAmount));
        log.info("Checks that total amount is correct: $" + totalAmount);

        // Remove one of the products from the cart by clicking "delete".
        cartPage.deleteOneProduct(SECOND_PRODUCT_NUMBER);
        log.info("Removes one product from Cart.");

        // Check there is only one product.
        assertEquals(cartPage.getNumberOfProductsInCart(), 1);
        log.info("Checks there is only one product left.");

        // Verify that the total price updates correctly.
        String totalAmountAfterDelete = cartPage.getTotalAmount();
        assertEquals(totalAmountAfterDelete, cartPage.getProductPrice(FIRST_PRODUCT_NUMBER));
        log.info("Checks that total amount correct without the deleted product: $" + totalAmountAfterDelete);
    }
}
