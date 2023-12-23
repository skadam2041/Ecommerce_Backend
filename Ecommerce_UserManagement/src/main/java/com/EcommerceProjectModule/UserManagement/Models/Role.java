package com.EcommerceProjectModule.UserManagement.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

import java.util.List;


@Getter
@Setter
@ToString
@Entity(name = "roles")
public class Role extends BaseModel{
    private  String roleName;

    private String description;


    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> users = new ArrayList<>();


}
