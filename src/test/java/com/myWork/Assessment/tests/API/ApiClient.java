package com.myWork.Assessment.tests.API;

import com.myWork.Assessment.tests.API.request.CreateUserRequest;
import com.myWork.Assessment.tests.API.request.LoginUserRequest;
import com.myWork.Assessment.tests.utils.ConfigLoader;
import io.restassured.response.Response;
import org.apache.commons.codec.StringEncoder;

import static io.restassured.RestAssured.*;

public class ApiClient {
    String url = ConfigLoader.getProperty("base.url");
    String email = ConfigLoader.getProperty("email");

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
