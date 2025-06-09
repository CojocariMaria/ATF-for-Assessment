package com.myWork.Assessment.tests.API;

import com.myWork.Assessment.tests.API.request.CreateUserRequest;
import com.myWork.Assessment.tests.API.request.LoginUserRequest;
import com.myWork.Assessment.tests.config.ConfigLoader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/**
 * API client for interacting with user-related endpoints of the test application.
 * <p>
 * Uses RestAssured for sending HTTP requests such as account creation, login, and user detail retrieval.
 */
public class ApiClient {
    String url = ConfigLoader.getProperty("base.url");
    String email = ConfigLoader.getProperty("email");

    /**
     * Sends a GET request to retrieve user details by email.
     *
     * @return the {@link Response} containing user information
     */
    public Response getUserDetails() {
        return given()
                .baseUri(url)
                .queryParam("email",email)
                .log().all()
                .when()
                .get("api/getUserDetailByEmail")
                .then()
                .extract().response();
    }

    /**
     * Sends a POST request to create a new user account using form parameters.
     *
     * @param createUser the user creation request data
     * @return the {@link Response} returned by the API
     */

    public Response createAccount(CreateUserRequest createUser) {
        return given()
                .baseUri(url)
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", createUser.getName())
                .formParam("email", createUser.getEmail())
                .formParam("password", createUser.getPassword())
                .formParam("title", createUser.getTitle())
                .formParam("birth_day", createUser.getBirthDay())
                .formParam("birth_month", createUser.getBirthMonth())
                .formParam("birth_year", createUser.getBirthYear())
                .formParam("firstname", createUser.getFirstName())
                .formParam("lastname", createUser.getLastName())
                .formParam("company", createUser.getCompany())
                .formParam("address1", createUser.getAddress1())
                .formParam("address2", createUser.getAddress2())
                .formParam("country", createUser.getCountry())
                .formParam("state", createUser.getState())
                .formParam("city", createUser.getCity())
                .formParam("zipcode", createUser.getZipcode())
                .formParam("mobile_number", createUser.getMobileNumber())
                .log().uri()
                .log().headers()
                .when()
                .post("api/createAccount")
                .then()
                .extract()
                .response();
    }

    /**
     * Sends a POST request to log in the user using email and password.
     *
     * @param loginUserRequest the login request data
     * @return the {@link Response} from the login API
     */
    public Response loginUser(LoginUserRequest loginUserRequest) {
        return given()
                .baseUri(url)
                .contentType("application/x-www-form-urlencoded") //
                .formParam("email", loginUserRequest.getEmail())
                .formParam("password", loginUserRequest.getPassword())
                .log().uri()
                .log().headers()
                .when()
                .post("api/verifyLogin")
                .then()
                .log().all()
                .extract().response();
    }



}
