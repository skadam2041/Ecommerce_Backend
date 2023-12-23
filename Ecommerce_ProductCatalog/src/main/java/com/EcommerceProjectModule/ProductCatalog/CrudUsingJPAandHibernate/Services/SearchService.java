package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.ProductRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.SortparamDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
;

    @Autowired
    private ProductRepository  productRepository;

    @Autowired
    private JPAProductService jpaProductService;


    public List<Product> searchProducts(String query, int pageNumber, int pageSize, List<SortparamDTO> sortParamsList) {
        return  jpaProductService.findAllProductsByTitle(query,pageNumber,pageSize,sortParamsList);
    }
}
