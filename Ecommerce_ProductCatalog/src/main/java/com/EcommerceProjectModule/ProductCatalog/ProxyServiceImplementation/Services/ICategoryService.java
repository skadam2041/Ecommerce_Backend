package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Services;

import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;

import java.util.List;

public interface ICategoryService {


    public List<String> getAllCategory();

    public  List<Product>  getInCategory(String categoryName);
}
