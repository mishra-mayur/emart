package com.eCommerce.emart.controller;

import com.eCommerce.emart.enums.Category;
import com.eCommerce.emart.model.entity.Product;
import com.eCommerce.emart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mayur on 10/05/20.
 */
@RestController
@RequestMapping(value = ProductDetailsController.PRODUCTS)
public class ProductDetailsController {

  public static final String PRODUCTS = "/public/products";
  private static final String CATEGORY_SEARCH = "/category/{category}";
  private static final String NAME_SEARCH = "/name/{name}";
  private static final String PARTIAL_SEARCH = "/expression/{expression}";
  private static final String CATEGORY = "category";
  private static final String NAME = "name";
  private static final String EXPRESSION = "expression";


  private ProductService productService;

  @Autowired
  public ProductDetailsController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = CATEGORY_SEARCH)
  public ResponseEntity<?> getAllProductsByCategory(@PathVariable(CATEGORY) Category category) {
    List<Product> products = productService.getProductsByCategory(category);
    if (Objects.nonNull(products)) {
      if (!CollectionUtils.isEmpty(products)) {
        return ResponseEntity.ok(products);
      }
      return ResponseEntity.ok("No product found for Category : " + category);
    } else {
      return ResponseEntity.ok("Unable to fetch product for Category : " + category);
    }
  }

  @GetMapping(value = NAME_SEARCH)
  public ResponseEntity<?> getProductByName(@PathVariable(NAME) String name) {
    Product product = productService.getProductByName(name);
    if (Objects.nonNull(product)) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.ok("No product found with Name : " + name);
    }
  }

  @GetMapping(value = PARTIAL_SEARCH)
  public ResponseEntity<?> getProductByExpression(@PathVariable(EXPRESSION) String expression) {
    List<Product> products = productService.getProductsPartialSearch(expression);
    if (Objects.nonNull(products)) {
      if (!CollectionUtils.isEmpty(products)) {
        return ResponseEntity.ok(products);
      }
      return ResponseEntity.ok("No product found for expression : " + expression);
    } else {
      return ResponseEntity.ok("Unable to fetch product for expression : " + expression);
    }
  }

}
