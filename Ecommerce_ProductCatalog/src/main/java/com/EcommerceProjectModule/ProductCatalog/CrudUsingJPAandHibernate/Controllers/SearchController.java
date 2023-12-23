package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Controllers;



import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.SearchDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
     private SearchService searchService;

    @PostMapping
    public List<ProductDTO> searchProducts(@RequestBody SearchDTO searchDTO){
        List<Product> productList = searchService.searchProducts(searchDTO.getQuery(),searchDTO.getPagenumber(),searchDTO.getPagesize(),searchDTO.getSortparamDTOList());
        List<ProductDTO> productDTOSList = new ArrayList<>();
        for(Product product : productList){
            ProductDTO productDTO = convertToProductDTO(product);
            productDTOSList.add(productDTO);
        }
        return productDTOSList;
    }

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
}
