package com.eCommerce.emart.repository;

import com.eCommerce.emart.enums.Category;
import com.eCommerce.emart.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  String EXPRESSION = "expression";
  String NAME = "name";
  String STOCK = "stock";

  Product findByName(String name);
  Product findById(int id);
  List<Product> findByCategory(Category category);

  @Query("SELECT P from Product P where P.name LIKE %:expression%")
  List<Product> findByExpression(@Param(EXPRESSION) String expression);

  @Modifying
  @Query("update Product P set P.stock = :stock where P.name = :name")
  int updateProductStock(@Param(NAME) String name,@Param(STOCK) int stock);
}
