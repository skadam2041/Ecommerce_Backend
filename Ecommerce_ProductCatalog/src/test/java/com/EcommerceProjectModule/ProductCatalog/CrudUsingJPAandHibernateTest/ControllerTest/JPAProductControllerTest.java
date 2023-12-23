package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.ControllerTest;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Controllers.JPAProductController;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.JPAProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class JPAProductControllerTest {

    @MockBean
    private JPAProductService productService;

    @Autowired
    private JPAProductController ProductController;


    @Test
    public void Test_whenGetAllProductIsCalled_thenReturnvalidProduct() {

        //
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

        //
        when(productService.getSingleProduct(any(Long.class))).thenReturn(product);

        ProductDTO productDTO = ProductController.getProductById(200l);

        assertNotNull(productDTO);
        assertEquals(productDTO.getTitle(), product.getTitle());

    }
}
