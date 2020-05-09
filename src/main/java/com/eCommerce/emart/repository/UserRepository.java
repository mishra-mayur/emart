package com.eCommerce.emart.repository;

import com.eCommerce.emart.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserEmail(String userEmail);
}
