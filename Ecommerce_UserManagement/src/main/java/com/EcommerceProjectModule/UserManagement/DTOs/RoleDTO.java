package com.EcommerceProjectModule.UserManagement.DTOs;

import com.EcommerceProjectModule.UserManagement.Models.BaseModel;
import com.EcommerceProjectModule.UserManagement.Models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RoleDTO {
    private  String roleName;

    private String description;


}