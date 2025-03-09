package com.epam.tat.module6.tests;
import com.epam.tat.module6.pages.HomePage;
import com.epam.tat.module6.utils.Random;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RegisterTests extends BaseTests {

    @Test
    public void userCannotRegisterWithAlreadyRegisteredUser() {

        // From the HomePage, click on "Sign up" in the top right corner.
        homePage = new HomePage(driver);
        homePage.goToSignUpSection();

        // Enter a new username and password.
        random = new Random();
        String randomUsername = random.getARandomUsername();
        String randomPassword = random.getARandomPassword();
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();

        // Verify that a pop-up alert displays "Sign up successful".
        assertEquals(homePage.getSignUpAlertText(), "Sign up successful.");

        // Close the alert.
        homePage.acceptSignUpAlert();

        // Click on "Sign up" again.
        homePage.goToSignUpSection();

        // Enter the same username and any password.
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();

        // Verify that an error message appears, indicating that the username is already in use.
        assertEquals(homePage.getSignUpAlertText(), "This user already exist.");
    }
}
