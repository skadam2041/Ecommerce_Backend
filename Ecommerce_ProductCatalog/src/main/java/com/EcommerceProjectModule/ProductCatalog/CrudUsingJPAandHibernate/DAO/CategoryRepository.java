package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.DAO;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
