package com.epam.tat.module6.tests;
import com.epam.tat.module6.model.DefaultCustomer;
import com.epam.tat.module6.model.LoggingCustomerDecorator;
import com.epam.tat.module6.model.RandomCustomer;
import com.epam.tat.module6.pages.OrderPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PurchaseTests extends BaseTests {

    @Test (groups = "smoke")
    public void completePurchaseWithDefaultDataTest() {

        customer = (LoggingCustomerDecorator) new DefaultCustomer().createCustomer();

        // From the HomePage, select a product from the store.
        homePage = testInstances.homePage;
        homePage.selectAProductWithNumber(variables.FIRST_PRODUCT_NUMBER);
        log.info("Selects a Product.");

        // Save the name of the Product and the Total amount value.
        productPage = testInstances.productPage;
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
        cartPage = testInstances.cartPage;
        assertTrue(cartPage.isTheProductInCart(productName));
        log.info("Checks the product is present in the Cart.");

        // Save the Total amount value.
        String totalAmount = cartPage.getTotalAmount();
        log.info("Obtains total amount from Cart.");

        // Click the "Place Order" button.
        cartPage.clickPlaceOrderButton();
        log.info("Clicks on 'place order' button.");

        // Fill the form with valid information.
        orderPage = testInstances.orderPage;
        orderPage = new OrderPage(driver);
        orderPage.order_addName(customer.getName());
        orderPage.order_addCountry(customer.getCountry());
        orderPage.order_addCity(customer.getCity());
        orderPage.order_addCreditCard(customer.getCreditCard());
        orderPage.order_addMonth(time.getActualMonth());
        orderPage.order_addYear(time.getActualYear());
        log.info("Adds information to the order from the CustomerData.");

        // Click on "Purchase".
        orderPage.clickOnPurchaseButton();

        // Verify that a confirmation message appears with correct information.
        assertTrue(orderPage.isValueCorrect(customer.getName(), "Name"));
        log.info("Checks the name in the order is correct.");
        assertTrue(orderPage.isValueCorrect(customer.getCreditCard(), "Card Number"));
        log.info("Checks the Card Number in the order is correct.");
        assertTrue(orderPage.isValueCorrect(totalAmount, "Amount"));
        log.info("Checks the Amount in the order is correct.");
    }

    @Test (groups = "regression")
    public void completePurchaseWithRandomDataTest() {

        customer = (LoggingCustomerDecorator) new RandomCustomer().createCustomer();

        // From the HomePage, select a product from the store.
        homePage = testInstances.homePage;
        homePage.selectAProductWithNumber(variables.FIRST_PRODUCT_NUMBER);
        log.info("Selects a Product.");

        // Save the name of the Product and the Total amount value.
        productPage = testInstances.productPage;
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
        cartPage = testInstances.cartPage;
        assertTrue(cartPage.isTheProductInCart(productName));
        log.info("Checks the product is present in the Cart.");

        // Save the Total amount value.
        String totalAmount = cartPage.getTotalAmount();
        log.info("Obtains total amount from Cart.");

        // Click the "Place Order" button.
        cartPage.clickPlaceOrderButton();
        log.info("Clicks on 'place order' button.");

        // Fill the form with valid information.
        orderPage = testInstances.orderPage;
        orderPage.order_addName(customer.getName());
        orderPage.order_addCountry(customer.getCountry());
        orderPage.order_addCity(customer.getCity());
        orderPage.order_addCreditCard(customer.getCreditCard());
        orderPage.order_addMonth(time.getActualMonth());
        orderPage.order_addYear(time.getActualYear());
        log.info("Adds information to the order from the CustomerData.");

        // Click on "Purchase".
        orderPage.clickOnPurchaseButton();

        // Verify that a confirmation message appears with correct information.
        assertTrue(orderPage.isValueCorrect(customer.getName(), "Name"));
        log.info("Checks the name in the order is correct.");
        assertTrue(orderPage.isValueCorrect(customer.getCreditCard(), "Card Number"));
        log.info("Checks the Card Number in the order is correct.");
        assertTrue(orderPage.isValueCorrect(totalAmount, "Amount"));
        log.info("Checks the Amount ($" + totalAmount +") in the order is correct.");
    }

    @Test (groups = "regression")
    public void removeProductInPurchaseFlowTest() {

        // From the HomePage, select a product from the store.
        homePage = testInstances.homePage;
        homePage.selectAProductWithNumber(variables.FIRST_PRODUCT_NUMBER);
        log.info("Selects a Product.");

        // Click the "Add to cart" button.
        productPage = testInstances.productPage;
        productPage.clickAddToCart();
        log.info("Clicks on 'Add to Cart'.");

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();
        log.info("Closes Alert from Product added.");

        // Return to the HomePage by clicking the "Product Store" link.
        productPage.clicktoMoveToHomeSection();
        log.info("Moves to Homepage.");

        // Select a second product from the store.
        homePage.selectAProductWithNumber(variables.SECOND_PRODUCT_NUMBER);
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
        cartPage = testInstances.cartPage;
        String totalAmount = cartPage.getTotalAmount();
        log.info("Obtains total amount from Cart.");

        // Verify that both products are listed with the correct prices.
        assertTrue(cartPage.isTotalAmountCorrect(totalAmount));
        log.info("Checks that total amount is correct: $" + totalAmount);

        // Remove one of the products from the cart by clicking "delete".
        cartPage.deleteOneProduct(variables.SECOND_PRODUCT_NUMBER);
        log.info("Removes one product from Cart.");

        // Check there is only one product.
        assertEquals(cartPage.getNumberOfProductsInCart(), 1);
        log.info("Checks there is only one product left.");

        // Verify that the total price updates correctly.
        String totalAmountAfterDelete = cartPage.getTotalAmount();
        assertEquals(totalAmountAfterDelete, cartPage.getProductPrice(variables.FIRST_PRODUCT_NUMBER));
        log.info("Checks that total amount correct without the deleted product: $" + totalAmountAfterDelete);
    }
}
