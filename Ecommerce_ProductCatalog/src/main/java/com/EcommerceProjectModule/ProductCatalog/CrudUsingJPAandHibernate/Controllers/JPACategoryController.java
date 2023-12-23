package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Controllers;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services.JPACategoryService;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.CategoryDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.ProductDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.RatingDto;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/JPA/category")
public class JPACategoryController {

    @Autowired
    private JPACategoryService jpaCategoryService;
    @GetMapping("/{id}")
    public CategoryDTO getCategoryByID(@PathVariable("id") long id){
        Category category = jpaCategoryService.getCategoryById(id);
        CategoryDTO categoryDTO = convertToCategoryDTO(category);
        return categoryDTO;
    }


    @GetMapping()
    public List<CategoryDTO> getAllCategory(){
        List<Category> categories = jpaCategoryService.getAllCategory();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category: categories){
            CategoryDTO categoryDTO = convertToCategoryDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") long id){
        jpaCategoryService.deleteCategory(id);
        return "Category Deleted";
    }

    @DeleteMapping()
    public String deleteCategory(){
        String response =  jpaCategoryService.deleteAllCategories();
        return response;
    }

    @PostMapping()
    public Category createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = convertToCategory(categoryDTO);
        Category dbCategory = jpaCategoryService.addCategory(category);
        return dbCategory;
    }


    @PutMapping("/{id}")
    public Category updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") long id){
        Category category = convertToCategory(categoryDTO);
        Category dbCategory = jpaCategoryService.updateCategory(category,id);
        return dbCategory;
    }

    private Category convertToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        List<ProductDTO> productDTOList = categoryDTO.getProductDTOList();
        List<Product> productList = new ArrayList<>();
        for(ProductDTO productDTO: productDTOList){
            Product product = convertToProduct(productDTO);
            productList.add(product);
        }
        category.setProductList(productList);
        return category;
    }


    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = category.getProductList();
        for(Product product: productList){
            ProductDTO productDTO = convertToProductDTO(product);
            productDTOList.add(productDTO);
        }
        categoryDTO.setProductDTOList(productDTOList);

        return categoryDTO;
    }



    //helper methods
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
        rating.setRate(productDTO.getRating().getRate());
        rating.setCount(productDTO.getRating().getCount());
        product.setRating(rating);
        return product;
    }
}
