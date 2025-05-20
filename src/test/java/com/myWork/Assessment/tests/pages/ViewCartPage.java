package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;


import static org.testng.Assert.assertTrue;

@Data
public class ViewCartPage {
    private Page page;
    private Locator product1;

    private static final Logger logger = LoggerFactory.getLogger(ViewCartPage.class);

    public ViewCartPage(Page page) {
        this.page = page;
        this.product1 = page.locator("id='product-1'");

    }

    public boolean isViewCartPageDisplayed() {
        logger.info("Checking if View_Cart Page is loaded...");
        logger.info("Current URL: " + page.url());
        try {
            return page.locator("[id='cart_info']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void assertProductsInCart(String... expectedProductIds) {
        for (String id : expectedProductIds) {
            Locator row = page.locator("tr#" + id);
            assertTrue(row.isVisible(), "product with id " + id + " not found in cart");
        }
    }

    public void assertPriceQuantityTotalVisible(String productId) {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Checking the displaying product info: {}", productId);
        Locator description = page.locator("tr#" + productId + " td.cart_description");
        Locator price = page.locator("tr#" + productId + " td.cart_price");
        Locator quantity = page.locator("tr#" + productId + " td.cart_quantity");
        Locator total = page.locator("tr#" + productId + " td.cart_total");


        softAssert.assertTrue(description.isVisible(), "Description is not visible for "+ productId);
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
