package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDeletedPage {
    private Page page;
    private CommonBase commonBase;
    private final Locator continueButton;


    private static final Logger logger = LoggerFactory.getLogger(AccountDeletedPage.class);
    public AccountDeletedPage(Page page){
        this.page= page;
        this.continueButton = page.locator("[data-qa='continue-button']");
    }

    public boolean isAccountDeletedPageVisible(){

        try {
            page.waitForSelector("[data-qa='account-deleted']", new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator("[data-qa='account-deleted']").isVisible();
        } catch (Exception e) {
            logger.error("Account Deleted element not found: {}", e.getMessage());
            return false;
        }
    }

    public void checkAccountDeletedMessage(){
        page.locator("[data-qa='account-deleted']").isVisible();
    }

}
