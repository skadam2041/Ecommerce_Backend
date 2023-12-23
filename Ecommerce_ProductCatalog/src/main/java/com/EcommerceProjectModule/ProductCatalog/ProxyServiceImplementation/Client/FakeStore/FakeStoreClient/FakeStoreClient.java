package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Client.FakeStore.FakeStoreClient;

import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;

import java.util.List;

public interface FakeStoreClient {
    public List<Product> getAllProducts();
    public Product getSingleProduct(Long productId);

    public Product addNewProduct(Product product);

    public Product updateProduct(Long productId, Product product);

    public Product deleteProduct(Long productId);

    public List<String> getAllCategory();

    public  List<Product>  getInCategory(String categoryName);
}
