@ui
Feature: Register User


  Scenario: User registration, login, and account deletion flow
    Given home page is displayed
    And user clicks on [Signup_Login] button
    And  Signup_Login page is displayed
    When User enters registration details and clicks [Signup] Button
    And  Check that signup page is visible
    And User creates password
    And User fills account information:
      | day   | 14   |
      | month | June |
      | year  | 1985 |
    And User fills the address information
    And Click the [Create Account] button
    And Check that 'ACCOUNT CREATED!' is visible and contains the following message
      | Congratulations! Your new account has been successfully created! |
    And Click on the [Continue] button
    And User is redirected to Home page
    And user clicks [Logout] button
    And Signup_Login page is displayed
    And User enters valid details and clicks [Login] button
    Then User is redirected to Home page
    And Click on [Delete Account] button
    And check that 'ACCOUNT DELETED!' is visible


  Scenario: Login User with incorrect email and password
    Given home page is displayed
    And user clicks on [Signup_Login] button
    And Signup_Login page is displayed
    When User enters incorrect details and clicks [Login] button
    Then error message is visible
      | Your email or password is incorrect! |




