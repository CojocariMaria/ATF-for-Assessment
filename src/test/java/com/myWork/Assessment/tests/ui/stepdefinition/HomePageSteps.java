package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.ui.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertTrue;

@Data
public class HomePageSteps {
    private final Page page;
    private HomePage homePage;
    private CommonBase commonBase;

    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);


    public HomePageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.homePage = new HomePage(page);
    }

    @Given("home page is displayed")
    public void homePageIsDisplayed() {

        logger.info("Checking if Home Page is displayed...");
        logger.debug("Current URL: {}", page.url());
        assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

    }

    @And("user clicks on [Signup_Login] button")
    public void userClicksOnSignup_LoginButton() {
        logger.info("User clicks on [Signup / Login]");
        homePage.clickSignupLogin();
    }

    @Then("User is redirected to Home page")
    public void userIsRedirectedToHomePage() {
        logger.info("User is redirected to Home page");
        assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
    }

    @And("Click on [Delete Account] button")
    public void clickOnDeleteAccountButton() {
        logger.info("User clicks on [Delete Account]");
        homePage.clickDeleteAccountButton();
    }

    @And("user clicks [Logout] button")
    public void userClicksLogoutButton() {
        logger.info("User clicks on [Logout]");
        homePage.clickLogoutButton();
    }

    @And("user clicks on [Products] button")
    public void userClicksOnProductsButton() {
        logger.info("User clicks on [Products]");

        homePage.clickProductsButton();

    }


}
