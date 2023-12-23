package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortparamDTO {
    private String paramName;
    private String sortType; //ASC or DESC

}
