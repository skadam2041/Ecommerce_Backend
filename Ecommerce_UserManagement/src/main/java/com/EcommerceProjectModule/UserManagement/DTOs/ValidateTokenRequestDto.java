package com.EcommerceProjectModule.UserManagement.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValidateTokenRequestDto {
    private String token;
    private String username;
}
