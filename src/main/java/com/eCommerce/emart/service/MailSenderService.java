package com.eCommerce.emart.service;

/**
 * Created by Mayur on 09/05/20.
 */
public interface MailSenderService {
  String sendMail(String emailId, String otp);
}
