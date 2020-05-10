package com.eCommerce.emart.repository;

import com.eCommerce.emart.model.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Integer> {
  List<UserCart> findByUserEmail(String userEmail);
  boolean deleteByUserEmailAndAndProductId(String userEmail, int productId);
}
