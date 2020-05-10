package com.eCommerce.emart.repository;

import com.eCommerce.emart.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  String EMAIL = "email";
  String USER_EMAIL = "userEmail";

  Optional<User> findByUserEmail(String userEmail);

  @Modifying
  @Query("UPDATE User U SET U.active=true WHERE U.userEmail=:email")
  void activateUser(@Param(EMAIL) String email);

  @Modifying
  @Query("UPDATE User U SET U.active=false WHERE U.userEmail=:userEmail")
  void deactivateUserAccount(@Param(USER_EMAIL)String userEmail);
}
