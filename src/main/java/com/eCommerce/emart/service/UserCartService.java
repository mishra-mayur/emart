package com.eCommerce.emart.service;

import java.util.Map;

/**
 * Created by Mayur on 09/05/20.
 */
public interface UserCartService {
  Map<String, Integer> getUserCart(String userEmail);
  boolean addToCart(String userEmail, int productId);
  boolean removeFromCart(String userEmail, int productId);
}
