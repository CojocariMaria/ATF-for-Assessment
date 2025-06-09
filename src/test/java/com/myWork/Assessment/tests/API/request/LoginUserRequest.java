package com.myWork.Assessment.tests.API.request;

import lombok.Data;
/**
 * Data Transfer Object (DTO) representing the login request body for authentication.
 * <p>
 * This class is used to send user credentials (email and password)
 * in POST requests to the login endpoint of the API.
 */
@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
