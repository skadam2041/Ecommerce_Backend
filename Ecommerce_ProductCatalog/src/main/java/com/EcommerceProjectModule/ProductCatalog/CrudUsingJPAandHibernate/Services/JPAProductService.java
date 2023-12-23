package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO.ProductRepository;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DTO.SortparamDTO;
import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    public JPAProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public JPAProductService() {

    }

    @Override
    public List<Product> getAllProducts()    {
        List<Product> products = this.productRepository.findAll();
        return products;
    }

    @Override
    public Product getSingleProduct(long productId) {
        Product product = this.productRepository.findById(productId).get();
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        Product savedProduct = this.productRepository.save(product);
        return savedProduct;
    }



    @Override
    public Product updateProduct(Long productId, Product product) {
        this.productRepository.deleteById(productId);
        Product savedProduct = this.productRepository.save(product);
        return savedProduct;

    }

    @Override
    public String deleteProduct(long productId) {
        this.productRepository.deleteById(productId);

        return "Product Deleted Successfully";
    }

    @Override
    public List<Product> findAllProductsByTitle(String query, int pageNumber, int pageSize, List<SortparamDTO> sortParamList){
        // hard coded sorting/order by
//        Sort sort = Sort.by("title").ascending()
//                .and(Sort.by("price").ascending());
//

        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParamList.get(0).getParamName());
        } else {
            sort = Sort.by(sortParamList.get(0).getParamName()).descending();
        }

        for(int i = 1; i< sortParamList.size(); i++) {
            if(sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName())
                        .descending());
            }
        }
        return this.productRepository.findByTitle(query, PageRequest.of(pageNumber,pageSize,sort));
    }


}
