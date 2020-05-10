package com.eCommerce.emart.repository;

import com.eCommerce.emart.model.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface UserValidationRepository extends JpaRepository<UserOtp, Integer> {
  UserOtp findByUserEmail(String userEmail);
}
