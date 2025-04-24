package stepDefinitions;

import io.cucumber.java.en.*;

import static org.testng.Assert.*;
import static stepDefinitions.Hooks.*;

public class LoginSteps {

    @Given("I am in the homepage")
    public void i_am_in_the_homepage() {
        homePage = testInstances.homePage;
    }
    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String userName, String password) {
        // From the HomePage, click on "Log in" in the top right corner.
        homePage.goToLoginSection();
        log.info("Enters the login section.");

        // Enter valid credentials.
        homePage.login_addUsername(userName);
        homePage.login_addPassword(password);
        log.info("Enters credentials.");
    }
    @When("I click on Login Button")
    public void i_click_on_login_button() {
        // Click on "Log in".
        homePage.login_enterLogin();
        log.info("Clicks on LoginButton.");
    }

    @When("^I enter a valid username \"([^\"]*)\" with a random password$")
    public void i_enter_a_valid_username_with_a_random_password(String userName) {
        // From the HomePage, click on "Log in" in the top right corner.
        homePage.goToLoginSection();
        log.info("Enters the login section.");

        // Enter valid username, but a wrong password.
        homePage.login_addUsername(userName);
        homePage.login_addPassword(random.getARandomPassword());
        log.info("Enters credentials wrong credentials.");
    }

    @Then("^Welcome message with my username \"([^\"]*)\" should be displayed$")
    public void welcome_message_with_my_username_should_be_displayed(String userName) {
        String userNameDisplayed = homePage.getUsernameDisplayed();
        assertEquals(userNameDisplayed, "Welcome " + userName);
        log.info("Asserts name displayed is valid.");
    }

    @Then("Error message of incorrect password is shown")
    public void error_message_of_incorrect_password_is_shown() {
        // Verify that the message received is from "wrong password".
        String wrongPasswordMessage = homePage.getWrongPasswordAlertText();
        assertEquals(wrongPasswordMessage, "Wrong password.");
        log.info("Asserts the system shows a message of wrong password");
    }
}
