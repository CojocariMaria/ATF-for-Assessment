package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;

import com.myWork.Assessment.tests.context.ContextKey;
import com.myWork.Assessment.tests.context.ScenarioContext;
import com.myWork.Assessment.tests.ui.pages.RegisterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Data
public class RegisterPageSteps {
    private final Page page;
    private RegisterPage registerPage;
    private CommonBase commonBase;
    private static final Logger logger = LoggerFactory.getLogger(RegisterPageSteps.class);
    private static Faker faker = new Faker();
    ScenarioContext scenarioContext = ScenarioContext.getInstance();


    public RegisterPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.registerPage = new RegisterPage(page);

    }

    @And("Signup_Login page is displayed")
    public void signupLoginPageIsDisplayed() {
        logger.info("Signup_Login page is displayed");
        assertTrue(registerPage.isRegisterPageVisible(), "Register page is not visible");
    }

    @When("User enters registration details and clicks [Signup] Button")
    public void userEntersRegistrationDetailsAndClicksSignupButton() {
        String userName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        scenarioContext.set(ContextKey.USERNAME, userName);
        scenarioContext.set(ContextKey.EMAIL, email);
        logger.info("User filled in registration details and clicks [Signup]");
        registerPage.signup(userName, email);

    }


    @And("User enters valid details and clicks [Login] button")
    public void userEntersDetailsAndClicksLoginButton() {
        String email = scenarioContext.get(ContextKey.EMAIL, String.class);
        String password = scenarioContext.get(ContextKey.PASSWORD, String.class);
        logger.info("User entered valid details and clicks [Login]");
        registerPage.login(email, password);
    }

    @When("User enters incorrect details and clicks [Login] button")
    public void userEntersIncorrectDetailsAndClicksLoginButton() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        logger.info("User entered incorrect details and clicks [Login]");
        registerPage.login(email, password);
    }

    @Then("error message is visible")
    public void errorMessageIsVisible(DataTable dataTable) {
        String message = dataTable.asList().get(0);
        assertTrue(registerPage.isLoginErrorVisible(), "Login error not visible");
        assertEquals(registerPage.getLoginErrorText(), "Your email or password is incorrect!", message);
        logger.info("Error message is visible");

    }
}
