package com.eCommerce.emart.serviceImpl;

import com.eCommerce.emart.model.entity.UserOtp;
import com.eCommerce.emart.repository.UserValidationRepository;
import com.eCommerce.emart.service.UserOtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
@Slf4j
public class UserOtpServiceImpl implements UserOtpService {

  private UserValidationRepository userValidationRepository;

  @Autowired
  public UserOtpServiceImpl(UserValidationRepository userValidationRepository) {
    this.userValidationRepository = userValidationRepository;
  }

  @Override
  @Transactional
  public UserOtp getOtpByEmail(String email) {
    UserOtp userOtp = null;
    try {
      userOtp = userValidationRepository.findByUserEmail(email);
    } catch (Exception e) {
      log.error("Error in fetching otp for email : {}", email);
    }
    if (Objects.nonNull(userOtp)) {
      return userOtp;
    }
    return null;
  }

  @Override
  @Transactional
  public boolean saveOtpAndUser(String otp, String email) {
    return Objects.nonNull(
        userValidationRepository.save(UserOtp.builder().otp(otp).userEmail(email).build()));
  }
}
