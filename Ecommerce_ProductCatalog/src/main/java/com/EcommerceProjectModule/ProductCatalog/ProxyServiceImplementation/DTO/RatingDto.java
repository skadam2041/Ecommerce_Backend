package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingDto {
    private double rate;
    private double count;
}