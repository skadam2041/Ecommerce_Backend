package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class SearchDTO {
    private String query;
    private  int  pagenumber;
    private int pagesize;
    private List<SortparamDTO> sortparamDTOList;
}
