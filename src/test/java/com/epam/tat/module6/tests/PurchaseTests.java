package com.epam.tat.module6.tests;

import com.epam.tat.module6.pages.CartPage;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.pages.OrderPage;
import com.epam.tat.module6.pages.ProductPage;
import com.epam.tat.module6.utils.Customer;
import com.epam.tat.module6.utils.Time;
import org.testng.annotations.Test;

import static com.epam.tat.module6.utils.Customer.*;
import static org.testng.Assert.*;

public class PurchaseTests extends BaseTests{

    @Test (groups = "smoke")
    public void completePurchaseTest() {

        validCustomer = new Customer(createDefaultCustomer().getName(), createDefaultCustomer().getCountry(), createDefaultCustomer().getCity(), createDefaultCustomer().getCreditCard());
        time = new Time();

        // From the HomePage, select a product from the store.
        homePage = new HomePage(driver);
        homePage.selectAProductWithNumber(FIRST_PRODUCT_NUMBER);

        // Save the name of the Product and the Total amount value.
        productPage = new ProductPage(driver);
        String productName = productPage.getProductName();

        // Click the "Add to cart" button.
        productPage.clickAddToCart();

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();

        // Navigate to the cart from the top menu by clicking "Cart".
        productPage.clickToMoveToCartSection();

        // Verify that the added product appears in the cart list.
        cartPage = new CartPage(driver);
        assertTrue(cartPage.isTheProductInCart(productName));

        // Save the Total amount value.
        String totalAmount = cartPage.getTotalAmount();

        // Click the "Place Order" button.
        cartPage.clickPlaceOrderButton();

        // Fill the form with valid information.
        orderPage = new OrderPage(driver);
        orderPage.order_addName(validCustomer.getName());
        orderPage.order_addCountry(validCustomer.getCountry());
        orderPage.order_addCity(validCustomer.getCity());
        orderPage.order_addCreditCard(validCustomer.getCreditCard());
        orderPage.order_addMonth(time.getActualMonth());
        orderPage.order_addYear(time.getActualYear());

        // Click on "Purchase".
        orderPage.clickOnPurchaseButton();

        // Verify that a confirmation message appears with correct information.
        assertTrue(orderPage.isValueCorrect(validCustomer.getName(), "Name"));
        assertTrue(orderPage.isValueCorrect(validCustomer.getCreditCard(), "Card Number"));
        assertTrue(orderPage.isValueCorrect(totalAmount, "Amount"));
    }

    @Test (groups = "sanity")
    public void removeProductInPurchaseFlowTest() {
        // From the HomePage, select a product from the store.
        homePage = new HomePage(driver);
        homePage.selectAProductWithNumber(FIRST_PRODUCT_NUMBER);

        // Click the "Add to cart" button.
        productPage = new ProductPage(driver);
        productPage.clickAddToCart();

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();

        // Return to the HomePage by clicking the "Product Store" link.
        productPage.clicktoMoveToHomeSection();

        // Select a second product from the store.
        homePage.selectAProductWithNumber(SECOND_PRODUCT_NUMBER);

        // Click the "Add to cart" button.
        productPage.clickAddToCart();

        // Confirm the pop-up alert.
        productPage.acceptProductAddedAlert();

        // Navigate to the cart from the top menu by clicking "Cart".
        productPage.clickToMoveToCartSection();

        // Save the Total amount value.
        cartPage = new CartPage(driver);
        String totalAmount = cartPage.getTotalAmount();

        // Verify that both products are listed with the correct prices.
        assertTrue(cartPage.isTotalAmountCorrect(totalAmount));

        // Remove one of the products from the cart by clicking "delete".
        cartPage.deleteOneProduct(SECOND_PRODUCT_NUMBER);

        // Check there is only one product.
        assertEquals(cartPage.getNumberOfProductsInCart(), 1);

        // Verify that the total price updates correctly.
        String totalAmountAfterDelete = cartPage.getTotalAmount();
        assertEquals(totalAmountAfterDelete, cartPage.getProductPrice(FIRST_PRODUCT_NUMBER));
    }
}
