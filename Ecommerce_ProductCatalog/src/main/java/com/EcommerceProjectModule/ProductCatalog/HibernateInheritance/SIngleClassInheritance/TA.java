package com.EcommerceProjectModule.ProductCatalog.HibernateInheritance.SIngleClassInheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "sc_ta")
@DiscriminatorValue("1")
public class TA extends User {
    private double rating;
}
