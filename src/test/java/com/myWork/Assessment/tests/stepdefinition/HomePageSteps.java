package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.HomePage;
import com.myWork.Assessment.tests.utils.ContextKey;
import com.myWork.Assessment.tests.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import lombok.Setter;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Data
public class HomePageSteps {
    private final Page page;
    private HomePage homePage;
    private CommonBase commonBase;


    public HomePageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.homePage = new HomePage(page);
    }

    @Given("home page is loaded")
    public void homePageIsLoaded() {

        System.out.println("Checking if Home Page is loaded...");
        System.out.println("Current URL: " + page.url());
        assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

    }

    @When("Click on [Signup_Login] button")
    public void clickOnSignupLoginButton() {
        homePage.clickSignupLogin();
    }

    @Then("User is redirected to Home page")
    public void userIsRedirectedToHomePage() {
        assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
    }

    @And("Click on [Delete Account] button")
    public void clickOnDeleteAccountButton() {
        homePage.clickDeleteAccountButton();
    }

    @And("user clicks [Logout] button")
    public void userClicksLogoutButton() {
        homePage.clickLogoutButton();
    }

    @And("user clicks on [Products] button")
    public void userClicksOnProductsButton() {

        homePage.clickProductsButton();

    }
}
