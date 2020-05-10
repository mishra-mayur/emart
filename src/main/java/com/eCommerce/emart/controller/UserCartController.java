package com.eCommerce.emart.controller;

import com.eCommerce.emart.model.request.CartRequest;
import com.eCommerce.emart.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Mayur on 10/05/20.
 */
@RestController
@RequestMapping(value = UserCartController.CART)
public class UserCartController {

  public static final String CART = "/cart";
  private static final String GET_CART_DETAILS = "/getCartDetails";
  private static final String ADD_TO_CART = "/addToCart";
  private static final String REMOVE_FROM_CART = "/removeFromCart";

  private UserCartService userCartService;

  @Autowired
  public UserCartController(UserCartService userCartService) {
    this.userCartService = userCartService;
  }

  @PostMapping(value = GET_CART_DETAILS)
  public ResponseEntity<?> getUserCartDetails(@RequestBody String userEmail) {
    Map<String, Integer> productQuantityMap = userCartService.getUserCart(userEmail);
    if (Objects.nonNull(productQuantityMap)) {
      if (!CollectionUtils.isEmpty(productQuantityMap)) {
        return ResponseEntity.ok(productQuantityMap);
      } else {
        return ResponseEntity.ok("Empty Cart Found for user email : " + userEmail);
      }
    } else {
      return ResponseEntity.ok("Unable to fetch Cart Details for user email : " + userEmail);
    }
  }

  @PostMapping(value = ADD_TO_CART)
  public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
    boolean success =
        userCartService.addToCart(cartRequest.getUserEmail(), cartRequest.getProductId());
    return success ?
        ResponseEntity.ok("Added to cart") :
        ResponseEntity.ok("Error in adding to cart");
  }

  @PostMapping(value = REMOVE_FROM_CART)
  public ResponseEntity<?> removeFromCart(@RequestBody CartRequest cartRequest) {
    boolean success =
        userCartService.removeFromCart(cartRequest.getUserEmail(), cartRequest.getProductId());
    return success ?
        ResponseEntity.ok("Removed from cart") :
        ResponseEntity.ok("Error in removing from cart");
  }

}
