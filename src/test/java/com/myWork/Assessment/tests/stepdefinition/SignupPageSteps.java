package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.SignupPage;
import com.myWork.Assessment.tests.utils.ContextKey;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.Data;

import java.util.Map;

import static org.testng.Assert.assertTrue;


@Data
public class SignupPageSteps {
    private final Page page;
    private SignupPage signupPage;
    private CommonBase commonBase;

    public SignupPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.signupPage = new SignupPage(page);
    }

    @And("Check that signup page is visible")
    public void checkThatSignupPageIsVisible() {
        assertTrue(signupPage.isSignupPageVisible(), "Signup page is not visible");
    }


    @And("User fills account information:")
    public void userFillsAccountInformation(DataTable dataTable) {
        signupPage.selectRandomTitle();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        signupPage.enterAccountInfo(data);
        signupPage.selectNewsletter();
        signupPage.selectOffers();
    }


    @And("User fills the address information:")
    public void userFillsTheAddressInformation(DataTable dataTable) {
        Map<String, String> addressData = dataTable.asMap(String.class, String.class);
        signupPage.enterAddressInfo(addressData);
    }

    @And("Click the [Create Account] button")
    public void clickTheCreateAccountButton() {
        signupPage.createAccountButton();
    }
}
