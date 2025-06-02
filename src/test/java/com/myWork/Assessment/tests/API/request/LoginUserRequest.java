package com.myWork.Assessment.tests.API.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
