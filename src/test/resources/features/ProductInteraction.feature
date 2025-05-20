
  Feature: Product catalog and cart interactions


    Scenario: Verify All Products and product detail page
      Given home page is loaded
      And user clicks on [Products] button
      And products page is displayed successfully
      When user clicks on View Product of first product
      Then user is landed to product detail page
      And product information is visible

      Scenario: Add product in Cart
        Given home page is loaded
        And user clicks on [Products] button
        And products page is displayed successfully
        And user navigates the product and clicks [Add to cart] button
        And clicks on [Continue Shopping] button
        And click [Cart] button
        Then View_Cart page is displayed
        And products are added to Cart
        And price, quantity and total price are visible












