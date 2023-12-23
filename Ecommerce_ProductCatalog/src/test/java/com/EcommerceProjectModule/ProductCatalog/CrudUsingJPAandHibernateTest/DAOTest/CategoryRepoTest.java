package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.DAOTest;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.CategoryRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository categoryRepository;



    @Transactional
    @Rollback(value = false)
    @Test
    public void saveCategoryAndProdutAndratingTest() {
        Category category1 = new Category();
        category1.setName("Electronics");
        category1.setDescription("Electronics Object");
        category1.setId(310l);

        Product product1 = new Product();
        product1.setId(3010l);
        product1.setTitle("refrigerator");
        product1.setPrice(20000);
        product1.setDescription("refrigerator for storing purpose");
        product1.setImage("www.ancd.com");
        product1.setCategory(category1);
        Rating rating1 = new Rating();
        rating1.setId(30111l);
        rating1.setCount(3);
        rating1.setRate(4.5);
        product1.setRating(rating1);


        Product product2 = new Product();
        product2.setId(302l);
        product2.setTitle("fan");
        product2.setPrice(20000);
        product2.setDescription("fan for cooling purpose");
        product2.setCategory(category1);
        product2.setImage("www.ancde.com");

        Rating rating2 = new Rating();
        rating2.setId(302l);
        rating2.setCount(4);
        rating2.setRate(4.7);
        product2.setRating(rating2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        category1.setProductList(productList);

        categoryRepository.save(category1);

//        Category dbCategory = categoryRepository.findById(300l).get();
//
//        System.out.println(dbCategory.getName());
//        List<Product> dbProductList = dbCategory.getProductList();
//        for(Product dbproduct :dbProductList ){
//            System.out.println(dbproduct.getTitle());
//            System.out.println(dbproduct.getRating().getRate());
//        }
//
//        System.out.println("debug");

    }


}
