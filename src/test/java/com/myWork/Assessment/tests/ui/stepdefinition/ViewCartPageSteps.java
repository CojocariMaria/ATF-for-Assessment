package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.ui.pages.ViewCartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.Data;

@Data
public class ViewCartPageSteps {
    private Page page;
    private ViewCartPage viewCartPage;
    private CommonBase commonBase;


    public ViewCartPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.viewCartPage = new ViewCartPage(page);
    }


    @Then("View_Cart page is displayed")
    public boolean isViewCartPaseDisplayed() {
        return viewCartPage.isViewCartPageDisplayed();
    }


    @And("products are added to Cart")
    public void productsAreAddedToCart() {
        viewCartPage.assertProductsInCart("product-1");
    }

    @And("price, quantity and total price are visible")
    public void priceQuantityAndTotalPriceAreVisible() {
        viewCartPage.assertPriceQuantityTotalVisible("product-1");

    }
}
