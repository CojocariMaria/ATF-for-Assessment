
Feature: Register User


  Scenario: User signup into the website
    Given home page is loaded
    When Click on [Signup_Login] button
    And  Signup_Login page is displayed
    And User enters registration details and clicks [Signup] Button
      | signupName  |  signupEmail      |
      | Maria       | Maria302@gmail.com|
    And  Check that signup page is visible
    And User fills account information:
      | password        | Welcome25!  |
      | day             | 14          |
      | month           | June        |
      | year            | 1985        |
    And User fills the address information:
      | firstName      | FirstName    |
      | lastName       | LastName     |
      | company        | Testing      |
      | address1       | Address 47   |
      | address2       | Address 74   |
      | country        | Canada       |
      | state          | Toronto      |
      | city           | Toronto      |
      | zipCode        | 4040302      |
      | mobileNumber   | +416 589 5896|
    And Click the [Create Account] button
    Then Check that 'ACCOUNT CREATED!' is visible and contains the following message
      | Congratulations! Your new account has been successfully created!|
    And Click on the [Continue] button
    And check if Logged in as "Maria" is visible at the top menu

  Scenario: Logout user
    Given home page is loaded
    And Click on [Signup_Login] button
    And Signup_Login page is displayed
    When User enters valid details and clicks [Login] button
      | email                   |  password       |
      | Maria302@gmail.com      |  Welcome25!     |
    Then User is redirected to Home page
    And check if Logged in as "Maria" is visible at the top menu
    And user clicks [Logout] button
    And Signup_Login page is displayed

  Scenario: User login into the website with correct email and password and delete account
    Given home page is loaded
    And Click on [Signup_Login] button
    And Signup_Login page is displayed
    When User enters valid details and clicks [Login] button
      | email                   |  password       |
      | Maria302@gmail.com      |  Welcome25!     |
    Then User is redirected to Home page
    And check if Logged in as "Maria" is visible at the top menu
    And Click on [Delete Account] button
    And check that 'ACCOUNT DELETED!' is visible


    Scenario: Login User with incorrect email and password

      Given home page is loaded
      And Click on [Signup_Login] button
      And Signup_Login page is displayed
      When User enters incorrect details and clicks [Login] button
        | email                   |  password       |
        | MariaMaria302@gmail.com |      12         |
      Then error message is visible
      | Your email or password is incorrect!|




