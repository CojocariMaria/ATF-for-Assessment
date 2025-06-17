package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.ui.pages.AccountDeletedPage;
import io.cucumber.java.en.And;

import static org.testng.Assert.assertTrue;

public class AccountDeletedPageSteps {

    private final AccountDeletedPage accountDeletedPage;

    public AccountDeletedPageSteps(CommonBase commonBase) {
        Page page = commonBase.getPage();
        this.accountDeletedPage = new AccountDeletedPage(page);
    }

    @And("check that 'ACCOUNT DELETED!' is visible")
    public void checkAccountDeletedIsVisible() {
        assertTrue(accountDeletedPage.isAccountDeletedPageVisible(), "Header not visible");
        accountDeletedPage.checkAccountDeletedMessage();

    }

}
