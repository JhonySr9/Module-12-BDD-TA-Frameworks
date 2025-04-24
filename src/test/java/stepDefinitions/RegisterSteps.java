package stepDefinitions;

import com.epam.tat.module6.utils.Random;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static stepDefinitions.Hooks.*;

public class RegisterSteps {

    String randomUsername;
    String randomPassword;

    @When("I enter a new username and password")
    public void i_enter_a_new_username_and_password() {
        // From the HomePage, click on "Sign up" in the top right corner.
        homePage.goToSignUpSection();
        log.info("Moves to SignUp section.");

        // Enter a new username and password.
        random = new Random();
        randomUsername = random.getARandomUsername();
        randomPassword = random.getARandomPassword();
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);
        log.info("Enters new CustomerData credentials to register.feature.");

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();
        log.info("Clicks on 'Sign up' button.");
    }
    @When("I verify the sign up was successful")
    public void i_verify_the_sign_up_was_successful() {
        // Verify that a pop-up alert displays "Sign up successful".
        assertEquals(homePage.getSignUpAlertText(), "Sign up successful.");
        log.info("Checks that Sign up was successful.");

        // Close the alert.
        homePage.acceptSignUpAlert();
        log.info("Closes Alert from Product added.");
    }
    @When("I enter the same user and password")
    public void i_enter_the_same_user_and_password() {
        // Click on "Sign up" again.
        homePage.goToSignUpSection();
        log.info("Moves to SignUp section again.");

        // Enter the same username and any password.
        homePage.signUp_addUserName(randomUsername);
        homePage.signUp_addPassword(randomPassword);
        log.info("Uses the same credentials for last register.feature.");

        // Click the "Sign up" button.
        homePage.signUp_enterSignUp();
        log.info("Clicks on 'Sign up' button.");
    }
    @Then("I receive a message indicating the username is already used")
    public void i_receive_a_message_indicating_the_username_is_already_used() {
        // Verify that an error message appears, indicating that the username is already in use.
        assertEquals(homePage.getSignUpAlertText(), "This user already exist.");
        log.info("Checks the message 'This user already exists is received.'");
    }
}
