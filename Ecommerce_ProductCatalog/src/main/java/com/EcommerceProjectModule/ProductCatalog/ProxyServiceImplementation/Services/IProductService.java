package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Services;

import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(long productId);

    Product addNewProduct(Product product);


    Product updateProduct(Long productId, Product product);

    Product deleteProduct(Long productId);

}
