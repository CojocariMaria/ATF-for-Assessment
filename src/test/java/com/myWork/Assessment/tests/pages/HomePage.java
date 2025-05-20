package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.myWork.Assessment.tests.hooks.PlaywrightFactory;
import com.myWork.Assessment.tests.utils.ContextKey;
import com.myWork.Assessment.tests.utils.ScenarioContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertTrue;

@Slf4j
@Data
public class HomePage {
   private Page page;
    private final Locator signupLoginLink;
    private final Locator loggedInUser;
    private final Locator deleteAccountButton;
    private final Locator logoutButton;
    private final Locator productsLink;
    private final Locator cartLink;

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    ScenarioContext scenarioContext = ScenarioContext.getInstance();



    public HomePage(Page page) {
        this.page = page;
        this.signupLoginLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login"));
        this.loggedInUser = page.getByText("Logged in as");
        this.deleteAccountButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Delete Account"));
        this.logoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));
        this.productsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Products"));
        this.cartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
    }

    public boolean isHomePageVisible() {
        logger.info("Checking if Home Page is loaded...");
        System.out.println("Current URL: " + page.url());
        try {
            return page.locator("[id='slider']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignupLogin() {

        signupLoginLink.click();
    }

    public boolean isLoggedInAs(String expectedName) {
        String text = page.locator("a:has-text('Logged in as')").textContent().trim();
        return text.contains(expectedName);
    }

    public void assertLoggedInUserVisible() {
        String expectedName = scenarioContext.get(ContextKey.USERNAME,String.class);
        logger.info("Checking expected username: '{}' ", expectedName);
        assertTrue(isLoggedInAs(expectedName), "Logged in username not visible or incorrect");
    }

    public void clickDeleteAccountButton() {
        deleteAccountButton.click();
    }

    public void clickLogoutButton() {
logger.info("Click on Logout button");
        logoutButton.click();
    }

    public void clickProductsButton() {

        logger.info("Click on Product button");
        productsLink.click();
    }

    public void clickCartButton(){

        logger.info("Click on Cart button");
        cartLink.click();
    }

}
