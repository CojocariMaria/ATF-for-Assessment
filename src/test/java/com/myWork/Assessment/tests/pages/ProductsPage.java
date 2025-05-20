package com.myWork.Assessment.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@Data
public class ProductsPage {
    private Page page;
    private final Locator firstProduct;
    private final Locator continueShoppingButton;

private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);
    public ProductsPage(Page page) {
        this.page = page;
        this.firstProduct = page.locator("a[href='/product_details/1']");
        this.continueShoppingButton = page.locator("#cartModal > div > div > div.modal-footer > button");

    }


    public boolean isProductsPageVisible() {
        logger.info("Checking if Products Page is loaded...");
        logger.info("Current URL: " + page.url());
        try {
            return page.locator("h2.title:has-text('All Products')").isVisible();
        } catch (Exception e) {
            return false;
        }
    }


    public void clickOnViewProduct() {
        firstProduct.click();
    }


    public void clickAddToCartById(int index) {
        logger.debug("Adding product to the cart");
        Locator productCard = page.locator(".product-image-wrapper").nth(index);
        productCard.hover();
        productCard.waitFor();
        page.locator("a.add-to-cart:has-text('Add to cart')").nth(index).click();

    }


    public void clickContinueShoppingButton(){
        logger.info("Continue shopping link is clicking");
        continueShoppingButton.click();

    }


}
