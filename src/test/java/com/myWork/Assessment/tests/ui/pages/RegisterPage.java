package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the Registration and Login page.
 * <p>
 * Supports actions such as user signup, login, and login error validation.
 * Provides access to both registration and login form elements.
 */
@Data
public class RegisterPage {
    private Page page;
    private final Locator signupName;
    private final Locator signupEmail;
    private final Locator signupButton;
    private final Locator email;
    private final Locator password;
    private final Locator loginButton;
    private final Locator errorMessage;

    private static final Logger logger = LoggerFactory.getLogger(RegisterPage.class);


    public RegisterPage(Page page) {
        this.page = page;
        this.signupName = page.locator("[data-qa='signup-name']");
        this.signupEmail = page.locator("[data-qa='signup-email']");
        this.signupButton = page.locator("[data-qa='signup-button']");
        this.email = page.locator("[data-qa='login-email']");
        this.password = page.locator("[data-qa='login-password']");
        this.loginButton = page.locator("[data-qa='login-button']");
        this.errorMessage = page.locator("p:has-text('Your email or password is incorrect!')");
    }

    /**
     * Checks if the registration/login page is visible by verifying the presence of the form element.
     *
     * @return true if the page is visible, false otherwise
     */
    public boolean isRegisterPageVisible() {
        logger.info("Checking if Register Page is loaded...");
        logger.info("Current URL: {}", page.url());
        try {
            return page.locator("[id='form']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Logs in using the provided email and password.
     *
     * @param email    the user's email address
     * @param password the user's password
     */
    public void login(String email, String password) {

        this.email.fill(email);
        logger.debug("Fill randomly generated email: {}", email);
        this.password.fill(password);
        logger.debug("Fill random password: {}", password);
        loginButton.click();
        logger.info("Click on [Login] button");

    }

    /**
     * Submits the signup form with the given name and email.
     * <p>
     * Throws a runtime exception if any field is null or empty.
     *
     * @param userName the user's name
     * @param email    the user's email
     */
    public void signup(String userName, String email) {

        this.signupName.fill(userName);
        this.signupEmail.fill(email);
        logger.debug(" Name '{}' and Email '{}' were filled in and sighup button was clicked", userName, email);
        if (userName == null || email == null || userName.isEmpty() || email.isEmpty()) {

            throw new RuntimeException("Signup Name or Email is null or empty!");
        }
        signupButton.click();

    }

    /**
     * Checks if the login error message is visible on the page.
     *
     * @return true if the login error message is displayed, false otherwise
     */
    public boolean isLoginErrorVisible() {

        String errorMessage = "p:has-text('Your email or password is incorrect!')";

        try {
            logger.debug("Waiting for the login error message to appear...");
            page.waitForSelector(errorMessage, new Page.WaitForSelectorOptions().setTimeout(3000));

            boolean visible = page.locator(errorMessage).isVisible();
            logger.debug("The login error message is displayed: {}", visible);
            return visible;

        } catch (Exception e) {
            logger.error(" Login error message not found: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the text content of the login error message.
     *
     * @return the trimmed error message text
     */
    public String getLoginErrorText() {
        String text = errorMessage.textContent().trim();
        logger.debug("Error message is displayed: '{}'", text);
        return text;

    }


}