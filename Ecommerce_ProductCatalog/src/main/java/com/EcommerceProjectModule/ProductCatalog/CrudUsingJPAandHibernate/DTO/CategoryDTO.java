package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private long id; //primary key int
    private String name;
    private String description;
    private List<ProductDTO> productDTOList;
}
