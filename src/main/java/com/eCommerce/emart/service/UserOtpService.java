package com.eCommerce.emart.service;

import com.eCommerce.emart.model.entity.UserOtp;

/**
 * Created by Mayur on 09/05/20.
 */
public interface UserOtpService {
  UserOtp getOtpByEmail(String email);
  boolean saveOtpAndUser(String otp, String email);
}
