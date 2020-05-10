package com.eCommerce.emart.serviceImpl;

import com.eCommerce.emart.model.entity.UserCart;
import com.eCommerce.emart.repository.UserCartRepository;
import com.eCommerce.emart.service.ProductService;
import com.eCommerce.emart.service.UserCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
@Slf4j
public class UserCartServiceImpl implements UserCartService {

  private UserCartRepository userCartRepository;

  private ProductService productService;

  @Autowired
  public UserCartServiceImpl(UserCartRepository userCartRepository, ProductService productService) {
    this.userCartRepository = userCartRepository;
    this.productService = productService;
  }

  @Override
  @Transactional
  public Map<String, Integer> getUserCart(String userEmail) {
    List<UserCart> userCarts = null;
    try {
      userCarts = userCartRepository.findByUserEmail(userEmail);
    } catch (Exception e) {
      log.error("Unable to fetch card details for user with email : {}", userEmail);
    }
    Map<String, Integer> productQuantityMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(userCarts)) {
      userCarts.forEach(userCart -> {
        String productName = null;
        try {
          productName = productService.getProductNameById(userCart.getProductId());
        } catch (Exception e) {
          log.error("Unable to fetch product name for product Id : {}", userCart.getProductId());
        }
        productQuantityMap.put(productName, userCart.getQuantity());
      });
    }
    return productQuantityMap;
  }

  @Override
  @Transactional
  public boolean addToCart(String userEmail, int productId) {
    List<UserCart> userCarts = null;
    try {
      userCarts = userCartRepository.findByUserEmail(userEmail);
    } catch (Exception e) {
      log.error("Unable to fetch cart details for user with email : {}", userEmail);
    }
    AtomicBoolean updated = new AtomicBoolean(false);
    userCarts.forEach(userCart -> {
      if (userCart.getProductId() == productId) {
        userCart.setQuantity(userCart.getQuantity() + 1);
        updated.set(true);
      }
    });
    if (!updated.get()) {
      try {
        userCartRepository
            .save(UserCart.builder().productId(productId).quantity(1).userEmail(userEmail).build());
        return true;
      } catch (Exception e) {
        log.error("Error in adding to card. Product Id : {}", productId);
      }
    }
    return false;
  }

  @Override
  @Transactional
  public boolean removeFromCart(String userEmail, int productId) {
    userCartRepository.deleteByUserEmailAndAndProductId(userEmail, productId);
    return true;
  }
}
