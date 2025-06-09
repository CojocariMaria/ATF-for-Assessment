package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * Page Object representing the Product Details page.
 * <p>
 * Provides functionality to verify page visibility and extract product-related information
 * such as name, category, price, availability, brand, etc.
 */
public class ProductDetailsPage {
    private Page page;
    private final Locator productInformation;

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsPage.class);

    public ProductDetailsPage(Page page) {
        this.page = page;
        this.productInformation = page.locator("div.product-information");
    }
    /**
     * Checks if the Product Details page is currently visible in the browser.
     *
     * @return true if the page is loaded and main container is visible, false otherwise
     */
    public boolean isProductDetailsPageVisible() {
        logger.info("Checking if Product Details Page is loaded...");
        logger.info("Current URL: {}", page.url());
        try {
            return page.locator("section.container").isVisible();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Extracts product details from the product information section of the page.
     * <p>
     * Retrieves name, category, price, quantity, availability, condition, and brand.
     *
     * @return a map of product attributes where keys are attribute names and values are their corresponding text values
     */
    public Map<String, String> getProductDetails() {
        logger.info("Extraction of product details has begun...");
        Map<String, String> details = new HashMap<>();
        try {
            details.put("name", productInformation.locator("h2").textContent().trim());
            details.put("category", productInformation.locator("p:has-text('Category')").textContent().replace("Category:", "").trim());
            details.put("price", productInformation.locator("span:has-text('Rs.')").first().textContent().trim());
            details.put("quantity", productInformation.locator("label:has-text('Quantity:')").textContent().trim());
            details.put("availability", productInformation.locator("p:has-text('Availability')").textContent().replace("Availability:", "").trim());
            details.put("condition", productInformation.locator("p:has-text('Condition')").textContent().replace("Condition:", "").trim());
            details.put("brand", productInformation.locator("p:has-text('Brand')").textContent().replace("Brand:", "").trim());
            logger.debug("Product details: {}", details);
        } catch (Exception e) {

            logger.error(" Error when extracting product details: {}", e.getMessage());
        }

        return details;
    }


}
