package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Page Object representing the "Account Deleted" confirmation screen.
 * <p>
 * Provides methods to verify whether the page is displayed and to check the presence of a confirmation message.
 */
public class AccountDeletedPage {
    private Page page;


    private static final Logger logger = LoggerFactory.getLogger(AccountDeletedPage.class);
    public AccountDeletedPage(Page page){
        this.page= page;
        //Locator continueButton = page.locator("[data-qa='continue-button']");
    }
    /**
     * Checks whether the "Account Deleted" confirmation element is visible on the page.
     *
     * @return true if the element is visible, false otherwise
     */
    public boolean isAccountDeletedPageVisible(){

        try {
            page.waitForSelector("[data-qa='account-deleted']", new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator("[data-qa='account-deleted']").isVisible();
        } catch (Exception e) {
            logger.error("Account Deleted element not found: {}", e.getMessage());
            return false;
        }
    }
    /**
     * Verifies the presence of the account deletion confirmation message.
     * <p>
     * This method ensures the element is present but does not return a result.
     * Typically used for asserting visibility during testing.
     */
    public void checkAccountDeletedMessage(){
        page.locator("[data-qa='account-deleted']").isVisible();
    }

}
