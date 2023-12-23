package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image;
    private Rating rating;

}
