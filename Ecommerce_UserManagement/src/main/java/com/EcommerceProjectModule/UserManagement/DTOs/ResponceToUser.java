package com.EcommerceProjectModule.UserManagement.DTOs;


import com.EcommerceProjectModule.UserManagement.Models.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponceToUser {

    private String message;
    private String status;
    private String token;
    private String username;
    private String email;
    private List<Role> roles;



}
