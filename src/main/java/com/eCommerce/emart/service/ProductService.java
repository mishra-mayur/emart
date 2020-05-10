package com.eCommerce.emart.service;

import com.eCommerce.emart.enums.Category;
import com.eCommerce.emart.model.entity.Product;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
public interface ProductService {
  Product getProductByName(String name);
  List<Product> getProductsByCategory(Category category);
  List<Product> getProductsPartialSearch(String expression);
  boolean updateProductStock(String name, int value);
  String getProductNameById(int productId);
}
