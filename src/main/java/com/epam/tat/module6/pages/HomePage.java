package com.epam.tat.module6.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy (id = "login2")
    WebElement loginLink;

    @FindBy (id = "signin2")
    WebElement signUpLink;

    @FindBy (id = "loginusername")
    WebElement login_usernameTextBar;

    @FindBy (id = "loginpassword")
    WebElement login_passwordTextBar;

    @FindBy (xpath = "//button[text()='Log in']")
    WebElement login_submitLoginButton;

    @FindBy (id = "sign-username")
    WebElement signUp_usernameTextBar;

    @FindBy (id = "sign-password")
    WebElement signUp_passwordTextBar;

    @FindBy (xpath = "//button[text()='Sign up']")
    WebElement signUp_submitSignUpButton;

    @FindBy (id = "nameofuser")
    WebElement userNameMessage;

    @FindBy (id = "signInModal")
    WebElement signInModal;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginSection() {
        click(loginLink);
    }

    public void goToSignUpSection() {
        wait.until(ExpectedConditions.invisibilityOf(signInModal));
        click(signUpLink);
    }

    public void login_addUsername(String username) {
        addText(login_usernameTextBar, username);
    }

    public void login_addPassword(String password) {
        addText(login_passwordTextBar, password);
    }

    public void login_enterLogin() {
        click(login_submitLoginButton);
    }

    public void signUp_addUserName(String username) {
        addText(signUp_usernameTextBar, username);
    }

    public void signUp_addPassword(String password) {
        addText(signUp_passwordTextBar, password);
    }

    public void signUp_enterSignUp() {
        click(signUp_submitSignUpButton);
    }

    public void acceptSignUpAlert() {
        alert_acceptAlert();
    }

    public String getUsernameDisplayed() {
        return getText(userNameMessage);
    }

    public String getSignUpAlertText() {
        return alert_getAlertText();
    }

    public void selectAProductWithNumber(int number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href$='" + number + "']"))).click();
    }


}
