package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the "Account Created" confirmation page.
 * <p>
 * Provides methods to verify the page is visible, extract success messages,
 * and proceed to the next screen via the "Continue" button.
 */
public class AccountCreatedPage {
    private Page page;
    private final Locator continueButton;
    private static final Logger logger = LoggerFactory.getLogger(AccountCreatedPage.class);

    public AccountCreatedPage(Page page) {
        this.page = page;
        this.continueButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue"));
    }

    /**
     * Checks if the account created confirmation block is visible.
     *
     * @return true if the confirmation element is found and visible, false otherwise
     */

    public boolean isAccountCreatedPageVisible() {
        logger.debug("Checking if Home Page is loaded...");
        logger.info("Current URL: {}", page.url());
        try {
            page.waitForSelector("[data-qa='account-created']", new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator("[data-qa='account-created']").isVisible();
        } catch (Exception e) {
            logger.error("Account Created element not found: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the success message displayed after account creation.
     *
     * @return the message text content
     */
    public String getAccountCreatedMessage() {

        String message = page.locator("div.col-sm-9.col-sm-offset-1 p").first().textContent().trim();
        logger.info("Account created page message is displayed: {}", message);
        return message;

    }

    /**
     * Clicks the "Continue" button to proceed from the account created screen.
     */
    public void clickOnContinueButton() {

        continueButton.click();
        logger.info("Continue button was clicked");
    }

}
