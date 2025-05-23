package com.myWork.Assessment.tests.stepdefinition;

import com.microsoft.playwright.Page;
import com.myWork.Assessment.tests.hooks.CommonBase;
import com.myWork.Assessment.tests.pages.ProductDetailsPage;
import com.myWork.Assessment.tests.pages.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Map;

public class ProductDetailsPageSteps {
    private Page page;
    private ProductDetailsPage productDetailsPage;
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
