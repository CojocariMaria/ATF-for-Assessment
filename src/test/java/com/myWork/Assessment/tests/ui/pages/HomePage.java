package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the Home Page of the web application.
 * <p>
 * Provides access to key UI elements such as navigation links, user info,
 * and supports actions like logout, deleting account, navigating to products or cart.
 */

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
    /**
     * Verifies whether the Home Page is visible by checking the presence of the slider component.
     *
     * @return true if the slider element is visible, false otherwise
     */
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
    /**
     * Clicks the "Signup / Login" button.
     */
    public void clickSignupLogin() {
        logger.info("Click on [Signup / Login] button");
        signupLoginLink.click();
    }
    /**
     * Clicks the "Delete Account" button.
     */
    public void clickDeleteAccountButton() {
        logger.info("Click on [Delete Account] button");
        deleteAccountButton.click();
    }
    /**
     * Clicks the "Logout" button.
     */
    public void clickLogoutButton() {
        logger.info("Click on [Logout] button");
        logoutButton.click();
    }
    /**
     * Clicks the "Products" navigation link.
     */
    public void clickProductsButton() {
        productsLink.click();
    }
    /**
     * Clicks the "Cart" navigation link.
     */
    public void clickCartButton() {

        logger.info("Click on [Cart] button");
        cartLink.click();
    }

}
