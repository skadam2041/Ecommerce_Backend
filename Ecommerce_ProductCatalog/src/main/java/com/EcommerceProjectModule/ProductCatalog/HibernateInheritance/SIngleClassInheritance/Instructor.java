package com.EcommerceProjectModule.ProductCatalog.HibernateInheritance.SIngleClassInheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "sc_instructor")
@DiscriminatorValue("3")
public class Instructor extends User{
    private String company;
}
