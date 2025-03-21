package com.epam.tat.module6.tests;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @Test (groups = "smoke")
    public void existingUserLoginTest() {

        // From the HomePage, click on "Log in" in the top right corner.
        homePage = testInstances.homePage;
        homePage.goToLoginSection();
        log.info("Enters the login section.");

        // Enter valid credentials.
        homePage.login_addUsername(variables.VALID_USERNAME);
        homePage.login_addPassword(variables.VALID_PASSWORD);
        log.info("Enters credentials.");

        // Click on "Log in".
        homePage.login_enterLogin();
        log.info("Clicks on LoginButton.");

        // Verify that the username appears in the top right corner as "Welcome <UserName>".
        String userNameDisplayed = homePage.getUsernameDisplayed();
        assertEquals(userNameDisplayed, "Welcome " + variables.VALID_USERNAME);
        log.info("Asserts name displayed is valid.");
    }

    @Test (groups = "regression")
    public void incorrectPasswordTest() {

        // From the HomePage, click on "Log in" in the top right corner.
        homePage = testInstances.homePage;
        homePage.goToLoginSection();
        log.info("Enters the login section.");

        // Enter valid username, but a wrong password.
        homePage.login_addUsername(variables.VALID_USERNAME);
        homePage.login_addPassword(random.getARandomPassword());
        log.info("Enters credentials wrong credentials.");

        // Click on "Log in".
        homePage.login_enterLogin();
        log.info("Clicks on LoginButton.");

        // Verify that the message received is from "wrong password".
        String wrongPasswordMessage = homePage.getWrongPasswordAlertText();
        assertEquals(wrongPasswordMessage, "Wrong password.");
        log.info("Asserts the system shows a message of wrong password");
    }
}
