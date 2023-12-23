package com.EcommerceProjectModule.UserManagement.DTOs;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLogoutRequesst {
    private String token;
    private Long userId;
}
