package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO;

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