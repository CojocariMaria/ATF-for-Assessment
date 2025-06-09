package com.myWork.Assessment.tests.API.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Response DTO representing the API response for user detail retrieval.
 * <p>
 * Maps the full user profile and the associated HTTP response code.
 * Fields that are not present in the JSON will be ignored due to {@code @JsonIgnoreProperties}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDetailResponse {
    private int responseCode;
    private User user;

    /**
     * Nested DTO representing user attributes returned in the response.
     */
    @Data
    public static class User {
        private int id;
        private String name;
        private String email;
        private String title;
        private String birth_day;
        private String birth_month;
        private String birth_year;
        private String first_name;
        private String last_name;
        private String company;
        private String address1;
        private String address2;
        private String country;
        private String state;
        private String city;
        private String zipcode;
    }


}
