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

        // Enter valid credentials.
        homePage.login_addUsername(VALID_USERNAME);
        homePage.login_addPassword(VALID_PASSWORD);

        // Click on "Log in".
        homePage.login_enterLogin();

        // Verify that the username appears in the top right corner as "Welcome <UserName>".
        String userNameDisplayed = homePage.getUsernameDisplayed();
        assertEquals(userNameDisplayed, "Welcome " + VALID_USERNAME);
    }
}
