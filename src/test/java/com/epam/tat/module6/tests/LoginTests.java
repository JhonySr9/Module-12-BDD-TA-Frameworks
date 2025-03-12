package com.epam.tat.module6.tests;
import com.epam.tat.module6.pages.HomePage;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @Test (groups = "smoke")
    public void existingUserLoginTest() {

        // From the HomePage, click on "Log in" in the top right corner.
        homePage = new HomePage(driver);
        homePage.goToLoginSection();
        log.info("Enters the login section.");

        // Enter valid credentials.
        homePage.login_addUsername(VALID_USERNAME);
        homePage.login_addPassword(VALID_PASSWORD);
        log.info("Enters credentials.");

        // Click on "Log in".
        homePage.login_enterLogin();
        log.info("Clicks on LoginButton.");

        // Verify that the username appears in the top right corner as "Welcome <UserName>".
        String userNameDisplayed = homePage.getUsernameDisplayed();
        assertEquals(userNameDisplayed, "Welcome " + VALID_USERNAME);
        log.info("Asserts name displayed is valid.");
    }
}
