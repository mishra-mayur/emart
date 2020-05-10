package com.eCommerce.emart.service;

import com.eCommerce.emart.model.entity.Order;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
public interface OrderService {
  List<Order> getAllOrders(String userEmail);
  boolean confirmOrder(Order order);
  boolean cancelOrder(int id);
}
