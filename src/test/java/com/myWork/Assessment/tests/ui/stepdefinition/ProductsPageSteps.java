package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.ui.pages.HomePage;
import com.myWork.Assessment.tests.ui.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.Data;

import static org.testng.Assert.assertTrue;

@Data
public class ProductsPageSteps {
    private final Page page;
    private ProductsPage productsPage;
    private CommonBase commonBase;
    private HomePage homePage;

    public ProductsPageSteps(CommonBase commonBase) {
        this.page = commonBase.getPage();
        this.productsPage = new ProductsPage(page);
        this.homePage = new HomePage(page);

    }


    @And("products page is displayed successfully")
    public void productsPageIsDisplayedSuccessfully() {
        System.out.println("Checking if Products Page is loaded...");
        System.out.println("Current URL: " + page.url());
        assertTrue(productsPage.isProductsPageVisible(), "Products page is not visible");

    }


    @When("user clicks on View Product of first product")
    public void userClickOnViewProductOfFirstProduct() {
        productsPage.clickOnViewProduct();
    }

    @And("user navigates the product and clicks [Add to cart] button")
    public void userNavigatesTheProductAndClicksAddToCartButton() {
        productsPage.clickAddToCartById(0);

    }

    @And("clicks on [Continue Shopping] button")
    public void clicksOnContinueShoppingButton() {
        productsPage.clickContinueShoppingButton();}



    @And("click [Cart] button")
    public void clickViewCartButton() {
      homePage.clickCartButton();
    }


}
