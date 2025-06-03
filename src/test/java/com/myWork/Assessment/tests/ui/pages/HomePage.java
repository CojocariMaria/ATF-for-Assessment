package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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
        logger.debug("Checking if Home Page is loaded...");
        logger.debug("Current URL: {}" , page.url());
        try {
            return page.locator("[id='slider']").isVisible();
        } catch (Exception e) {
            logger.error("An error occurred while verifying the visibility of the #slider element: {}", e.getMessage());
            return false;
        }
    }

    public void clickSignupLogin() {
        logger.info("Click on [Signup / Login] button");
        signupLoginLink.click();
    }

    public void clickDeleteAccountButton() {
        logger.info("Click on [Delete Account] button");
        deleteAccountButton.click();
    }

    public void clickLogoutButton() {
        logger.info("Click on [Logout] button");
        logoutButton.click();
    }

    public void clickProductsButton() {
        productsLink.click();
    }

    public void clickCartButton() {

        logger.info("Click on [Cart] button");
        cartLink.click();
    }

}
