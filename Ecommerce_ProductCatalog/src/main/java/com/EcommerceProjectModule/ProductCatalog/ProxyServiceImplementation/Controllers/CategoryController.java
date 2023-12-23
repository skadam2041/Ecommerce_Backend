package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Controllers;

import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Rating;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/products/category/{categoryName}")
    public List<ProductDTO> getInCategory(@PathVariable("categoryName") String categoryName){
        List<Product> products = categoryService.getInCategory(categoryName);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products) {
            ProductDTO productDTO = convertToProductDTO(product);
            productDTOS.add(productDTO);
        }

        return productDTOS;
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        List<String> categories = categoryService.getAllCategory();
        return categories;
    }



    //helper methods
    private ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRate(product.getRating().getRate());
        ratingDto.setCount(product.getRating().getCount());
        productDTO.setRating(ratingDto);
        return productDTO;
    }

    private Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        Rating rating = new Rating();
//        rating.setRate(productDTO.getRating().getRate());
//        rating.setCount(productDTO.getRating().getCount());
        product.setRating(rating);
        return product;
    }

}
