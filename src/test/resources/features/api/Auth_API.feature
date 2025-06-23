Feature: User registration/login via API
  This feature covers scenarios related to user creation and login.
  It includes positive and negative tests to verify response codes  and response messages

  @api
  Scenario: Create a new user account with valid data
    Given valid registration data is provided
    When POST request to create a new user is sent to the API endpoint
    Then HTTP status code is 200
    And response code is 201
    And response contains "User created!"


  Scenario:Verify Login with valid details
    Given valid credentials are provided
    When POST request to verify existing user is sent to the API endpoint
    Then HTTP status code is 200
    And response code is 200
    And response contains "User exists!"

  Scenario: Verify Login with invalid details
    Given invalid credentials are provided
    When POST request to verify existing user is sent to the API endpoint
    Then HTTP status code is 200
    And response code is 404
    And response contains "User not found!"

    Scenario: GET user account detail by email
      Given email is provided
      When request is sent to GET user account details
      Then HTTP status code is 200
      And response contains user details



