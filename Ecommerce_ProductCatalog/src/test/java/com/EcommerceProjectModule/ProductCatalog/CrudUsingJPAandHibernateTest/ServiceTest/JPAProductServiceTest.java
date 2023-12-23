package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.ServiceTest;


import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.ProductRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.JPAProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class JPAProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    JPAProductService jpaProductService;

    @Test
    public void Test_WhenGetAllProductsIsCalled_ReturnAllProducts() {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3  =   new Product();

        List<Product> ls = new ArrayList<Product>();
        ls.add(p1);
        ls.add(p2);
        ls.add(p3);

        when(productRepository.findAll()).thenReturn(ls);

        List<Product> products = jpaProductService.getAllProducts();

        assert products.size() == 3;


    }


}
