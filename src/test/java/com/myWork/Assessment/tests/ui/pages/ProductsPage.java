package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the Products page, which lists all available products for the user.
 * <p>
 * Supports actions like viewing a specific product, adding a product to the cart by index,
 * and continuing shopping after a modal interaction.
 */
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

    /**
     * Verifies whether the Products page is currently visible in the browser.
     *
     * @return true if the page heading "All Products" is visible, false otherwise
     */
    public boolean isProductsPageVisible() {
        logger.info("Checking if Products Page is loaded...");
        logger.debug("Current URL: {}", page.url());
        try {
            page.locator("h2.title:has-text('All Products')").waitFor();
            return page.locator("h2.title:has-text('All Products')").isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks on the "View Product" link for the first product on the page.
     */
    public void clickOnViewProduct() {
        firstProduct.click();
        logger.info("Product was selected");
    }

    /**
     * Clicks the "Add to cart" button for the product at the specified index.
     * <p>
     * This method performs hover and wait actions to ensure the button is visible before clicking.
     *
     * @param index the zero-based index of the product in the product list
     */
    public void clickAddToCartById(int index) {
        logger.info("Adding product to the cart");
        Locator productCard = page.locator(".product-image-wrapper").nth(index);
        productCard.hover();
        productCard.waitFor();
        page.locator("a.add-to-cart:has-text('Add to cart')").nth(index).click();

    }

    /**
     * Clicks the "Continue Shopping" button in the cart modal popup.
     */
    public void clickContinueShoppingButton() {
        logger.info("Continue shopping link is clicked");
        continueShoppingButton.click();
    }


}
