package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailsPage {
    private Page page;
    private final Locator productInformation;

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsPage.class);
    public ProductDetailsPage(Page page){
        this.page = page;
        this.productInformation = page.locator("div.product-information");
    }

    public boolean isProductDetailsPageVisible() {
        logger.info("Checking if Product Details Page is loaded...");
        logger.info("Current URL: " + page.url());
        try {
            return page.locator("section.container").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, String> getProductDetails() {
        Map<String, String> details = new HashMap<>();

        details.put("name", productInformation.locator("h2").textContent().trim());
        details.put("category", productInformation.locator("p:has-text('Category')").textContent().replace("Category:", "").trim());
        details.put("price", productInformation.locator("span:has-text('Rs.')").first().textContent().trim());
        details.put("quantity",productInformation.locator("label:has-text('Quantity:')").textContent().trim());
        details.put("availability", productInformation.locator("p:has-text('Availability')").textContent().replace("Availability:", "").trim());
        details.put("condition", productInformation.locator("p:has-text('Condition')").textContent().replace("Condition:", "").trim());
        details.put("brand", productInformation.locator("p:has-text('Brand')").textContent().replace("Brand:", "").trim());

        return details;
    }


}
