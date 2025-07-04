package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.context.ContextKey;
import com.myWork.Assessment.tests.context.ScenarioContext;
import com.myWork.Assessment.tests.ui.pages.SignupPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.Data;
import net.datafaker.Faker;

import java.util.Map;

import static org.testng.Assert.assertTrue;


@Data
public class SignupPageSteps {
    private final Page page;
    private SignupPage signupPage;
    private CommonBase commonBase;

    private static Faker faker = new Faker();
    ScenarioContext scenarioContext = ScenarioContext.getInstance();

    public SignupPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.signupPage = new SignupPage(page);
    }

    @And("Check that signup page is visible")
    public void checkThatSignupPageIsVisible() {
        assertTrue(signupPage.isSignupPageVisible(), "Signup page is not visible");

    }

    @And("User creates password")
    public void userCreatesPassword() {
        String password = faker.internet().password();
        scenarioContext.set(ContextKey.PASSWORD, password);
        signupPage.fillPassword(password);
    }

    @And("User fills account information:")
    public void userFillsAccountInformation(DataTable dataTable) {

        signupPage.selectRandomTitle();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        signupPage.SelectDateOfBirth(data);
        signupPage.selectNewsletter();
        signupPage.selectOffers();
    }

    @And("User fills the address information")
    public void userFillsTheAddressInformation() {
        String firstName = scenarioContext.get(ContextKey.USERNAME, String.class);
        String lastName = faker.name().lastName();
        String company = faker.company().name();
        String address1 = faker.address().fullAddress();
        String address2 = faker.address().secondaryAddress();
        String country = "India";
        String state = faker.address().state();
        String city = faker.address().city();
        String zipCode = faker.address().zipCode();
        String mobileNumber = faker.phoneNumber().phoneNumber();
        signupPage.enterAddressInfo(firstName, lastName, company, address1, address2, country, state, city, zipCode, mobileNumber);
    }


    @And("Click the [Create Account] button")
    public void clickTheCreateAccountButton() {
        signupPage.createAccountButton();
    }


}
