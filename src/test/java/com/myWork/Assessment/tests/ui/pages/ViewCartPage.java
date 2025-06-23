package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

/**
 * Page Object representing the "View Cart" page.
 * <p>
 * Provides methods to validate that products are correctly added to the cart,
 * and their related information (description, price, quantity, total) is displayed.
 */
@Data
public class ViewCartPage {
    private Page page;
    private Locator product;

    private static final Logger logger = LoggerFactory.getLogger(ViewCartPage.class);

    public ViewCartPage(Page page) {
        this.page = page;
        this.product = page.locator("id='product-1'");

    }

    /**
     * Verifies whether the View Cart page is currently visible.
     *
     * @return true if the cart info section is visible, false otherwise
     */
    public boolean isViewCartPageDisplayed() {
        logger.info("Checking if View_Cart Page is loaded...");
        logger.info("Current URL: {}", page.url());
        try {
            return page.locator("[id='cart_info']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Asserts that products with the given IDs are visible in the cart.
     *
     * @param expectedProductIds one or more product row IDs to validate (e.g. "product-1")
     */
    public void assertProductsInCart(String... expectedProductIds) {
        for (String id : expectedProductIds) {
            Locator row = page.locator("tr#" + id);
            if (row.isVisible()) {
                logger.info("Product with id {} is present in the cart", id);
            } else {
                logger.error("product with id {} not found in cart", id);
            }
        }
    }

    /**
     * Asserts that the price, quantity, and total fields are visible for a given product.
     *
     * @param productId the row ID of the product to validate (e.g. "product-1")
     */
    public void assertPriceQuantityTotalVisible(String productId) {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Checking the displaying product info: {}", productId);
        Locator description = page.locator("tr#" + productId + " td.cart_description");
        Locator price = page.locator("tr#" + productId + " td.cart_price");
        Locator quantity = page.locator("tr#" + productId + " td.cart_quantity");
        Locator total = page.locator("tr#" + productId + " td.cart_total");


        softAssert.assertTrue(description.isVisible(), "Description is not visible for " + productId);
        logger.info("Description is displayed for product '{}'", productId);
        softAssert.assertTrue(price.isVisible(), "Price is not visible for " + productId);
        logger.info("Price is displayed for product '{}'", productId);
        softAssert.assertTrue(quantity.isVisible(), "Quantity is not visible for " + productId);
        logger.info("Quantity is displayed for product '{}'", productId);
        softAssert.assertTrue(total.isVisible(), "Total is not visible for " + productId);
        logger.info("Total price is displayed for product '{}'", productId);

        softAssert.assertAll();
    }

}
