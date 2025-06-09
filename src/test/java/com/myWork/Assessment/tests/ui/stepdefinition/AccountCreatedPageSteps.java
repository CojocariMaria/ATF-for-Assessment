package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.ui.pages.AccountCreatedPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AccountCreatedPageSteps {

    private final AccountCreatedPage accountCreatedPage;

    public AccountCreatedPageSteps(CommonBase commonBase) {
        Page page = commonBase.getPage();
        this.accountCreatedPage = new AccountCreatedPage(page);

    }


    @And("Check that 'ACCOUNT CREATED!' is visible and contains the following message")
    public void checkAccountCreatedIsVisible(DataTable dataTable) {

        String message = dataTable.asList().get(0);
        assertTrue(accountCreatedPage.isAccountCreatedPageVisible(), "Header not visible");
        assertEquals(accountCreatedPage.getAccountCreatedMessage(), "Congratulations! Your new account has been successfully created!", message);

    }


    @And("Click on the [Continue] button")
    public void clickOnTheContinueButton() {
        accountCreatedPage.clickOnContinueButton();
    }
}
