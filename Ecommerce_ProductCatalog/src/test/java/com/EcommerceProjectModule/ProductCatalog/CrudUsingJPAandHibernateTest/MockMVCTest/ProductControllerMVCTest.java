package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernateTest.MockMVCTest;


import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Controllers.JPAProductController;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.JPAProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JPAProductController.class)
public class ProductControllerMVCTest {

    @MockBean
    private JPAProductService jpaProductService;

    @Autowired
    private JPAProductController jpaProductController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void test_WhenGetAllProductCalled_ThenReturnProductList() throws Exception {

        Product p1 =  new Product();
        Category category = new Category();
        p1.setCategory(category);
        Rating rating = new Rating();
        p1.setRating(rating);
        List<Product> ls = new ArrayList<>();
        ls.add(p1);


        when(jpaProductService.getAllProducts()).thenReturn(ls);

        ProductDTO productDTO = jpaProductController.convertToProductDTO(ls.get(0));

        List<ProductDTO> ls1 = new ArrayList<>();
        ls1.add(productDTO);


        mockMvc.perform(get("/JPA/products")).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(ls1)));

    }


    @Test
    public  void Test_WhenAddNewProductCalled_ThenReturnAddedProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(202l);
        productDTO.setTitle("laptop");
        RatingDto ratingDto = new RatingDto();
        productDTO.setCategory("electonics");
        productDTO.setRating(ratingDto);

        ProductDTO dbproductDTO = new ProductDTO();
        dbproductDTO.setId(202l);
        dbproductDTO.setTitle("laptop");
        RatingDto dbratingDto = new RatingDto();
        dbproductDTO.setCategory("electonics");
        dbproductDTO.setRating(dbratingDto);

        Product product = jpaProductController.convertToProduct(productDTO);


        when(jpaProductService.addNewProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/JPA/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(dbproductDTO)));

        System.out.println("debug");
    }
}
