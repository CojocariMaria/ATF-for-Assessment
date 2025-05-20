package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.AccountDeletedPage;
import io.cucumber.java.en.And;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AccountDeletedPageSteps {

   private final Page page;
   private CommonBase commonBase;
   private AccountDeletedPage accountDeletedPage;

   public AccountDeletedPageSteps(CommonBase commonBase){
        this.page = commonBase.getPage();
        this.accountDeletedPage = new AccountDeletedPage(page);
       }

    @And("check that 'ACCOUNT DELETED!' is visible")
    public void checkAccountDeletedIsVisible() {
       accountDeletedPage.checkAccountDeletedMessage();

    }

}
