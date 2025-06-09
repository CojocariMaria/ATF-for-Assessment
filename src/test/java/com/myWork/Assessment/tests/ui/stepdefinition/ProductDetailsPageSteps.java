package com.myWork.Assessment.tests.ui.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.base.CommonBase;
import com.myWork.Assessment.tests.ui.pages.ProductDetailsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Map;

public class ProductDetailsPageSteps {
    private Page page;
    private final ProductDetailsPage productDetailsPage;
    CommonBase commonBase;

    public ProductDetailsPageSteps(CommonBase commonBase){
        this.page = commonBase.getPage();
        this.productDetailsPage = new ProductDetailsPage(page);
    }

    @Then("user is landed to product detail page")
    public boolean userIsLandedToProductDetailPage() {
      return productDetailsPage.isProductDetailsPageVisible();
    }

    @And("product information is visible")
    public Map<String, String> productInformationIsVisible(){
        return productDetailsPage.getProductDetails();
    }
}
