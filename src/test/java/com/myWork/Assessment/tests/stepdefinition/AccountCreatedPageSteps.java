package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.AccountCreatedPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AccountCreatedPageSteps {

    private final Page page;
    private CommonBase commonBase;
    private AccountCreatedPage accountCreatedPage;

    public AccountCreatedPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.accountCreatedPage = new AccountCreatedPage(page);

    }


    @Then("Check that 'ACCOUNT CREATED!' is visible and contains the following message")
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
