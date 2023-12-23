package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Controllers;


import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.JPAProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/JPA/products")
public class JPAProductController {
    private JPAProductService jpaProductService;
    public  JPAProductController(JPAProductService jpaProductService) {
          this.jpaProductService = jpaProductService;
    }

    @GetMapping()
    public List<ProductDTO> getAllProduct(){
        List<Product> products = jpaProductService.getAllProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = convertToProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") long id){
        Product product = jpaProductService.getSingleProduct(id);
        ProductDTO productDTO = convertToProductDTO(product);
        return productDTO;
    }

    @PostMapping()
    public ProductDTO addProduct(@RequestBody ProductDTO ProductDTO){
        Product product = convertToProduct(ProductDTO);
        Product dbProduct = jpaProductService.addNewProduct(product);
        ProductDTO dbProductDTO = convertToProductDTO(dbProduct);
        return dbProductDTO;
    }


    @PutMapping ("/{id}")
    public ProductDTO updateProduct(@PathVariable("id")  long  id,@RequestBody ProductDTO ProductDTO) {
        Product product = convertToProduct(ProductDTO);
        Product dbProduct = jpaProductService.updateProduct(id,product);
        ProductDTO dbProductDTO = convertToProductDTO(dbProduct);
        return dbProductDTO;
    }


    @DeleteMapping("/{id}")
    public  String deleteProduct(@PathVariable("id")  long  id) {
        String responce = jpaProductService.deleteProduct(id);
        return  responce;
    }








//    //helper methods
    public ProductDTO convertToProductDTO(Product product){
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

    public Product convertToProduct(ProductDTO productDTO) {
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
        rating.setRate(productDTO.getRating().getRate());
        rating.setCount(productDTO.getRating().getCount());
        product.setRating(rating);
        return product;
    }
}
