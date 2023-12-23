package com.EcommerceProjectModule.UserManagement.DTOs;

import com.EcommerceProjectModule.UserManagement.Models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserSignupRequest {
    private String username;
    private String password;
    private String email;
    private String role;
}