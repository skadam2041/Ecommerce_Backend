package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.SortparamDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(long productId);

    Product addNewProduct(Product product);


    Product updateProduct(Long productId, Product product);

    String deleteProduct(long productId);


    List<Product> findAllProductsByTitle(String query, int pageNumber, int pageSize, List<SortparamDTO> sortparamDTOSList);
}
