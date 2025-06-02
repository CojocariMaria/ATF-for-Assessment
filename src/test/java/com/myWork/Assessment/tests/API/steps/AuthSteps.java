package com.myWork.Assessment.tests.API.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myWork.Assessment.tests.API.ApiClient;
import com.myWork.Assessment.tests.API.request.CreateUserRequest;
import com.myWork.Assessment.tests.API.request.LoginUserRequest;
import com.myWork.Assessment.tests.API.response.UserDetailResponse;
import com.myWork.Assessment.tests.utils.ConfigLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.*;

public class AuthSteps {

    private final ApiClient apiClient = new ApiClient();
    private final Faker faker = new Faker();
    private CreateUserRequest createUser;
    private Response response;
    private static final Logger logger = LoggerFactory.getLogger(AuthSteps.class);
    private LoginUserRequest loginUserRequest = new LoginUserRequest();;
    String email = ConfigLoader.getProperty("email");
    String password = ConfigLoader.getProperty("password");


    @Given("valid registration data is provided")
    public void validRegistrationDataIsProvided() {
        createUser = new CreateUserRequest();

        createUser.setName(faker.name().firstName());
        createUser.setEmail(faker.internet().emailAddress());
        createUser.setPassword(faker.internet().password(8, 16));
        createUser.setTitle(faker.options().option("Mr", "Mrs"));
        createUser.setBirthDay(String.valueOf(faker.number().numberBetween(1, 28)));
        createUser.setBirthMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        createUser.setBirthYear(String.valueOf(faker.number().numberBetween(1900, 2005)));
        createUser.setFirstName(faker.name().firstName());
        createUser.setLastName(faker.name().lastName());
        createUser.setCompany(faker.company().name());
        createUser.setAddress1(faker.address().fullAddress());
        createUser.setAddress2(faker.address().secondaryAddress());
        createUser.setCountry("Canada");
        createUser.setState(faker.address().state());
        createUser.setCity(faker.address().city());
        createUser.setZipcode(faker.address().zipCode());
        createUser.setMobileNumber(faker.phoneNumber().phoneNumber());

        logger.info("Name: {}, Email: {}, Title: {}", createUser.getName(), createUser.getEmail(), createUser.getTitle());
        logger.info("Password: ********** (hidden for security)");
        logger.info("Birth date: {}-{}-{}", createUser.getBirthDay(), createUser.getBirthMonth(), createUser.getBirthYear());
        logger.info("Full Name: {} {}, Company: {}", createUser.getFirstName(), createUser.getLastName(), createUser.getCompany());
        logger.info("Address: {}, {}, {}, {}, {}, {}",
                createUser.getAddress1(),
                createUser.getAddress2(),
                createUser.getCountry(),
                createUser.getState(),
                createUser.getCity(),
                createUser.getZipcode());
        logger.info(" Mobile number: {}", createUser.getMobileNumber());

    }

    @When("POST request to create a new user is sent to the API endpoint")
    public void postRequestToCreateANewUserIsSentToTheAPIEndpoint() {
        response = apiClient.createAccount(createUser);
        logger.info("Sent POST request to /api/createAccount");

    }
    @Then("HTTP status code is {int}")
    public void httpStatusCodeIs(int expectedHttpStatusCode) {
        assertEquals(response.getStatusCode(), expectedHttpStatusCode);

    }

    @Then("response code is {int}")
    public void responseStatusCodeIs(int expectedCode) {
        int actual = response.jsonPath().getInt("responseCode");
        logger.info("API responseCode (from JSON): {}", actual);
        assertEquals(expectedCode,actual, "Mismatch in API response code inside JSON body");

    }

    @And("response contains {string}")
    public void responseContains(String expectedMessage) {
        String responseBody = response.getBody().asString();
        logger.info("Response body:\n{}", responseBody);
        assertTrue(responseBody.contains(expectedMessage),"Response body does not contain expected text!"
               );

    }

    @Given("valid credentials are provided")
    public void validCredentialsAreProvided() {


        loginUserRequest.setEmail(email);
        loginUserRequest.setPassword(password);
        logger.info("Valid credentials are provided");
        logger.info("Email: {}", email);
        logger.info("Password: ******** (hidden)");

    }
    @Given("invalid credentials are provided")
    public void invalidCredentialsAreProvided() {
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(faker.internet().emailAddress());
        loginUserRequest.setPassword(faker.internet().password(8,16));
        logger.info("Invalid credentials are provided");
        logger.info("Email : {}", email);
        logger.info("Password : ******** (hidden)");
    }

    @When("POST request to verify existing user is sent to the API endpoint")
    public void postRequestToVerifyExistingUserIsSentToTheAPIEndpoint() {
        response = apiClient.loginUser(loginUserRequest);
        logger.info("Sent POST request to /api/verifyLogin");
    }


    @Given("email is provided")
    public void emailIsProvided() {
        logger.info("Email: {} is provided ", email);
        loginUserRequest.setEmail(email);

    }

    @When("request is sent to GET user account details")
    public void requestIsSentToGETUserAccountDetails() {
        response = apiClient.getUserDetails();
        logger.info("Sent GET request to api/getUserDetailByEmail");

    }

    @And("response contains user details")
    public void responseContainsUserDetails() throws JsonProcessingException {
        logger.info("Checking that response contains valid user details...");

        String rawBody = response.getBody().asString();
        logger.debug("Raw response:\n{}", rawBody);

        ObjectMapper mapper = new ObjectMapper();
        UserDetailResponse userResponse = mapper.readValue(rawBody, UserDetailResponse.class);

        assertEquals(userResponse.getResponseCode(), 200);
        assertNotNull(userResponse.getUser());

       logger.info("User details validated successfully.");
    }


}
