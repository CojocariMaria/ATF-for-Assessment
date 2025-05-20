package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.RegisterPage;
import com.myWork.Assessment.tests.utils.ContextKey;
import com.myWork.Assessment.tests.utils.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Data
public class RegisterPageSteps {
    private final Page page;
    private RegisterPage registerPage;
    private CommonBase commonBase;
    private ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(RegisterPageSteps.class);


    public RegisterPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.registerPage = new RegisterPage(page);

    }

    @And("Signup_Login page is displayed")
    public void signupLoginPageIsDisplayed() {
        assertTrue(registerPage.isRegisterPageVisible(), "Register page is not visible");
        ;

    }

    @And("User enters registration details and clicks [Signup] Button")
    public void userEntersRegistrationDetailsAndClicksSignupButton(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String userName = data.get(0).get("userName");
        String email = data.get(0).get("email");
        scenarioContext.set(ContextKey.USERNAME,userName);
        scenarioContext.set(ContextKey.EMAIL,email);

        registerPage.signup(userName, email);

    }


    @When("User enters valid details and clicks [Login] button")
    public void userEntersDetailsAndClicksLoginButton(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String email = data.get(0).get("email");
        String password = data.get(0).get("password");
        scenarioContext.set(ContextKey.EMAIL,email);
        scenarioContext.set(ContextKey.PASSWORD,password);
        registerPage.login(email, password);
    }

    @When("User enters incorrect details and clicks [Login] button")
    public void userEntersIncorrectDetailsAndClicksLoginButton(DataTable dataTable) {
        List<Map<String, String>> incorrectData = dataTable.asMaps(String.class, String.class);
        String email = incorrectData.get(0).get("email");
        String password = incorrectData.get(0).get("password");
        registerPage.login(email, password);
    }

    @Then("error message is visible")
    public void errorMessageIsVisible(DataTable dataTable) {
        String message = dataTable.asList().get(0);
        assertTrue(registerPage.isLoginErrorVisible(), "Login error not visible");
        assertEquals(registerPage.getLoginErrorText(), "Your email or password is incorrect!", message);

    }
}
