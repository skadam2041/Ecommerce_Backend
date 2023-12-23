package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Controllers;


import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.ResponceTokenDTO;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Models.Rating;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Security.JwtValidator;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Services.FSProductService;
import io.micrometer.common.lang.Nullable;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private FSProductService productService;

    @Autowired
    private JwtValidator jwtValidator;


    public ProductController(FSProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
     public ProductDTO getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String AuthToken,  @PathVariable("id") long id){
        System.out.println("Received AuthToken: " + AuthToken);
        if(AuthToken == null) {
            System.out.println("Auth token is null");
            throw new RuntimeException("Auth token is  null");
        }

        ResponceTokenDTO responceTokenDTO = jwtValidator.validateToken(AuthToken);

        if(responceTokenDTO.getStatus().equals("Active")){
            System.out.println("User is active");
            Product product =  productService.getSingleProduct(id);
            ProductDTO productDTO = convertToProductDTO(product);
            return productDTO;
        }else throw new RuntimeException("User is not active");

    }

    @GetMapping()
    public List<ProductDTO> getAllProductDTOs(){
        List<Product> products =  productService.getAllProducts();

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products) {
            ProductDTO productDTO = convertToProductDTO(product);
            productDTOS.add(productDTO);
        }

        return productDTOS;
    }
    @PostMapping()
    public ProductDTO addNewProduct(@RequestBody ProductDTO productDTO){
        Product product = convertToProduct(productDTO);
        Product  DBproduct = productService.addNewProduct(product);
        ProductDTO dbproductDTO = convertToProductDTO(DBproduct);
        return dbproductDTO;
    }



    @PatchMapping("/{id}")
    public ProductDTO  updateProduct( @PathVariable("id") long id, @RequestBody ProductDTO productDTO){
        Product product = convertToProduct(productDTO);
        Product DBproduct = productService.updateProduct(id,product);
        ProductDTO DBproductDTO = convertToProductDTO(DBproduct);
        return DBproductDTO;
    }

    @DeleteMapping("/{id}")
    public ProductDTO deleteProduct(@PathVariable("id") long id){
        Product BDproduct = productService.deleteProduct(id);
        ProductDTO DBproductDTO = convertToProductDTO(BDproduct);
        return DBproductDTO;
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
