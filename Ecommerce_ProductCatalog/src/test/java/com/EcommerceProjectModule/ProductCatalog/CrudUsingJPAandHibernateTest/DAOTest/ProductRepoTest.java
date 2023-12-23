package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.DAOTest;


import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.ProductRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveProductAndCategoryAndRatingTest() {
        Product product = new Product();
        product.setId(200l);
        product.setTitle("Samsung Galaxy S10");
        product.setPrice(50000);
        product.setImage("www.abc.com");
        product.setDescription("Samsung mobile phone");
        Rating rating = new Rating();
        rating.setId(200l);
        rating.setCount(5);
        rating.setRate(4.5);
        product.setRating(rating);
        Category category = new Category();
        category.setId(200l);
        category.setName("SmartPhone");
        product.setCategory(category);

        productRepository.save(product);

        Optional<Product> productOptional = productRepository.findById(200l);
        Product dbProduct = productOptional.get();

        System.out.println(dbProduct.getTitle());
        System.out.println(dbProduct.getCategory().getName());
        System.out.println(dbProduct.getRating().getRate());
        System.out.println("debug");


//        String[] productTitleList = productRepository.getProductTitleById(200l);
//        for (  String productTitle:productTitleList) {
//            System.out.println(productTitle);
//        }


    }

}
