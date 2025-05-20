package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.myWork.Assessment.tests.hooks.CommonBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountCreatedPage {
    private Page page;
    private final Locator continueButton;

    private static final Logger logger = LoggerFactory.getLogger(AccountCreatedPage.class);

    public AccountCreatedPage(Page page) {
        this.page = page;
        this.continueButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue"));
    }

    public boolean isAccountCreatedPageVisible() {
        logger.debug("Checking if Home Page is loaded...");
        logger.info("Current URL: " + page.url());
        try {
            page.waitForSelector("[data-qa='account-created']", new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator("[data-qa='account-created']").isVisible();
        } catch (Exception e) {
            System.out.println("Account Created element not found: " + e.getMessage());
            return false;
        }
    }

    public String getAccountCreatedMessage() {

        return page.locator("div.col-sm-9.col-sm-offset-1 p").first().textContent().trim();


    }

    public void clickOnContinueButton() {

        continueButton.click();

        logger.info("Continue button was clicked");
    }

}
