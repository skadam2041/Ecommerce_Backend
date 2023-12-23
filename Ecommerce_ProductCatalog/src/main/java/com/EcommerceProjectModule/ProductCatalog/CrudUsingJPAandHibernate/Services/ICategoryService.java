package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Services;

import com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(long id);
    String deleteCategory(long id);

    String deleteAllCategories();

    List<Category> getAllCategory();
    Category addCategory(Category category);

    Category updateCategory(Category  category, long id);




}
