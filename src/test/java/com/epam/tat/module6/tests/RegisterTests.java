package com.epam.tat.module6.tests;
import com.epam.tat.module6.utils.Random;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RegisterTests extends BaseTests {

    @Test
    public void userCannotRegisterWithAlreadyRegisteredUser() {

        // From the HomePage, click on "Sign up" in the top right corner.
        homePage = testInstances.homePage;
        homePage.goToSignUpSection();
        log.info("Moves to SignUp section.");

        // Enter a new username and password.
        random = new Random();
        String randomUsername = random.getARandomUsername();
        String randomPassword = random.getARandomPassword();
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);
        log.info("Enters new CustomerData credentials to register.");

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();
        log.info("Clicks on 'Sign up' button.");

        // Verify that a pop-up alert displays "Sign up successful".
        assertEquals(homePage.getSignUpAlertText(), "Sign up successful.");
        log.info("Checks that Sign up was successful.");

        // Close the alert.
        homePage.acceptSignUpAlert();
        log.info("Closes Alert from Product added.");

        // Click on "Sign up" again.
        homePage.goToSignUpSection();
        log.info("Moves to SignUp section again.");

        // Enter the same username and any password.
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);
        log.info("Uses the same credentials for last register.");

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();
        log.info("Clicks on 'Sign up' button.");

        // Verify that an error message appears, indicating that the username is already in use.
        assertEquals(homePage.getSignUpAlertText(), "This user already exist.");
        log.info("Checks the message 'This user already exists is received.'");
    }
}
