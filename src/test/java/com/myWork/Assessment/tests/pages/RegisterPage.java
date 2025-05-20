package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertTrue;

@Data
public class RegisterPage {
    private Page page;
    private CommonBase commonBase;
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

    public boolean isRegisterPageVisible() {
        logger.info("Checking if Register Page is loaded...");
        logger.info("Current URL: " + page.url());
        try {
            return page.locator("[id='form']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }


    public void login(String email, String password) {

        logger.debug(" email '{}' and password '{}' were filled in and login button was clicked", email,password);

        this.email.fill(email);
        this.password.fill(password);
        loginButton.click();

    }

    public void signup(String userName, String email) {

        logger.debug(" Name '{}' and Email '{}' were filled in and sighup button was clicked",userName,email);
        if (userName == null || email == null || userName.isEmpty() || email.isEmpty()) {

            throw new RuntimeException("Signup Name or Email is null or empty!");
        }
        this.signupName.fill(userName);
        this.signupEmail.fill(email);
        System.out.println("Signup name: " + userName);
        System.out.println("Signup email: " + email);
        signupButton.click();

    }

    public boolean isLoginErrorVisible() {
        try {
            page.waitForSelector("p:has-text('Your email or password is incorrect!')",
                    new Page.WaitForSelectorOptions().setTimeout(3000));
            return page.locator("p:has-text('Your email or password is incorrect!')").isVisible();
        } catch (Exception e) {
            logger.error("Login error not found: " + e.getMessage());
            return false;
        }
    }

    public String getLoginErrorText() {
        return errorMessage.textContent().trim();
    }


}