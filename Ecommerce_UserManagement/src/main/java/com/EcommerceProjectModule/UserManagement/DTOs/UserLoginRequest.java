package com.EcommerceProjectModule.UserManagement.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequest {
    private String username;
    private String password;
}
