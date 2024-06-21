package com.exam.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profile;
    private Long userRoleId;


    
}
