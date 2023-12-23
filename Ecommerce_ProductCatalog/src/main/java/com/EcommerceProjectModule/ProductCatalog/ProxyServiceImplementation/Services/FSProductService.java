package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Services;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.ProductRepository;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Client.FakeStore.FakeStoreClient.FakeStoreClient;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FSProductService implements IProductService {


    private FakeStoreClient fakeStoreProductClient;
    private final ProductRepository productRepository;

    @Autowired
    public FSProductService(FakeStoreClient fakeStoreProductClient,
                            ProductRepository productRepository) {
        this.fakeStoreProductClient = fakeStoreProductClient;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts()    {
        List<Product> products = fakeStoreProductClient.getAllProducts();
        //save in DB
        //convert to productDTO

        return  products;
    }

    @Override
    public Product getSingleProduct(long productId) {

        Product product = fakeStoreProductClient.getSingleProduct(productId);
        //save in DB

        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        Product dbproduct = fakeStoreProductClient.addNewProduct(product);
        //save in DB
        return  dbproduct;
    }



    @Override
    public Product updateProduct(Long productId, Product product) {
        Product dbproduct = fakeStoreProductClient.updateProduct(productId, product);
        return dbproduct;
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product dbproduct = fakeStoreProductClient.deleteProduct(productId);
        return dbproduct;
    }




}
