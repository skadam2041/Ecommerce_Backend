package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.DAOTest;


import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.RatingRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class RatingRepoTest {
    @Autowired
    RatingRepository ratingRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void addRatingandProductandCategoryTest(){
        Rating rating = new Rating();
        rating.setId(1001l);
        rating.setRate(5);
        rating.setCount(5);


        Product product = new Product();
        product.setId(1001l);
        product.setPrice(10000);
        product.setTitle("Laptop");
        product.setDescription("Laptop for office use");
        product.setImage("www.abc.com");
        product.setRating(rating);
        product.setNumberOfUnits(10);

        Category category = new Category();
        category.setId(1001l);
        category.setName("Laptops and computers");
        category.setDescription("Laptop/computer products for office use");

        product.setCategory(category);

        rating.setProduct(product);

        Rating rating1 =  ratingRepository.save(rating);

        System.out.println(rating1.getProduct().getDescription());
        System.out.println(rating1.getProduct().getCategory().getName());
    }

}
