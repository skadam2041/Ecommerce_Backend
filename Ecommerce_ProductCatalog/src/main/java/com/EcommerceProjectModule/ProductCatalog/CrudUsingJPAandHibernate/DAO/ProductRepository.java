package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title, Pageable pageable);

    List<Product> findByTitleEquals(String query);

//    @Query(name = "Select  p.Title from Product p where id = :id")
//    String[] getProductTitleById(@Param("id") Long id);

//    @Query(name = "Select  p.Title from Product p where id = ?1")
//    String[] getProductTitleById( Long id);

}
