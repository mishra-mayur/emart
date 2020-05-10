package com.eCommerce.emart.serviceImpl;

import com.eCommerce.emart.enums.Category;
import com.eCommerce.emart.model.entity.Product;
import com.eCommerce.emart.repository.ProductRepository;
import com.eCommerce.emart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  @Transactional
  public Product getProductByName(String name) {
    Product products = null;
    try {
      products = productRepository.findByName(name);
    } catch (Exception e) {
      log.error("Error in fetching product with product name : {}", name);
    }
    return products;
  }

  @Override
  @Transactional
  public List<Product> getProductsByCategory(Category category) {
    List<Product> products = null;
    try {
      products = productRepository.findByCategory(category);
    } catch (Exception e) {
      log.error("Error in fetching product with product category : {}", category);
    }
    return products;
  }

  @Override
  @Transactional
  public List<Product> getProductsPartialSearch(String expression) {
    List<Product> products = null;
    try {
      products = productRepository.findByExpression(expression);
    } catch (Exception e) {
      log.error("Error in fetching product with product expression : {}", expression);
    }
    return products;
  }

  @Override
  @Transactional
  public boolean updateProductStock(String name, int value) {
    //positive val to increase stock, negative val to reduce stock
    boolean result = false;
    try {
      int stock = productRepository.findByName(name).getStock();
      result = productRepository.updateProductStock(name, stock + value) == 1;
    } catch (Exception e) {
      log.error("Error in updating stock for product with product name : {}", name);
    }
    return result;
  }

  @Override
  @Transactional
  public String getProductNameById(int productId) {
    Product product = null;
    try {
      product = productRepository.findById(productId);
    } catch (Exception e) {
      log.error("Error in fetching product with product Id : {}", productId);
    }
    return product.getName();
  }
}
