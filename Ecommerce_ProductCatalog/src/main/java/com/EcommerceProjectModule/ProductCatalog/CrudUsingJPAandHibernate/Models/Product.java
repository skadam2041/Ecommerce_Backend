package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@ToString
@Entity(name = "jpa_product")
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Category category;
    private String image;
    private  int numberOfUnits;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Rating rating;
}
