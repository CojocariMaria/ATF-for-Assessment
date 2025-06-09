package com.myWork.Assessment.tests.API.request;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the request body for user account creation.
 * <p>
 * This class is used to serialize data for POST requests to the user creation endpoint.
 * All fields correspond to form or JSON fields required by the API.
 */
@Data
public class CreateUserRequest {

        private String name;
        private String email;
        private String password;
        private String title;
        private String birthDay;
        private String birthMonth;
        private String birthYear;
        private String firstName;
        private String lastName;
        private String company;
        private String address1;
        private String address2;
        private String country;
        private String state;
        private String city;
        private String zipcode;
        private String mobileNumber;
    }





