package com.eCommerce.emart.controller;

import com.eCommerce.emart.enums.OrderStatus;
import com.eCommerce.emart.model.entity.Order;
import com.eCommerce.emart.model.request.OrderRequest;
import com.eCommerce.emart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by Mayur on 10/05/20.
 */
@RestController
@RequestMapping(value = BookingController.BOOKING)
public class BookingController {

  public static final String BOOKING = "/booking";
  private static final String GET_ALL_ORDERS = "/getAllOrders";
  private static final String PLACE_ORDER = "/placeOrder";
  private static final String CANCEL_ORDER_ID = "/cancelOrder/{id}";
  private static final String ID = "id";

  private OrderService orderService;

  @Autowired
  public BookingController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(value = GET_ALL_ORDERS)
  public ResponseEntity<?> getAllOrders(@RequestBody String userEmail) {
    List<Order> orders = orderService.getAllOrders(userEmail);
    if (Objects.nonNull(orders)) {
      return ResponseEntity.ok(orders);
    } else {
      return ResponseEntity.ok("No Orders Found !!!");
    }
  }

  @PostMapping(value = PLACE_ORDER)
  public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
    Order order = Order.builder().userEmail(orderRequest.getUserEmail())
        .productQuantityMap(orderRequest.getProductQuantityMap())
        .orderStatus(OrderStatus.SUCCESSFUL).build();
    boolean success = orderService.confirmOrder(order);
    return success ?
        ResponseEntity.ok("Order Placed successfully") :
        ResponseEntity.ok("Unable to place this order, Please retry!!!");
  }

  @GetMapping(value = CANCEL_ORDER_ID)
  public ResponseEntity<?> cancelOrder(@PathVariable(ID) int id) {
    boolean success = orderService.cancelOrder(id);
    return success ?
        ResponseEntity.ok("Order cancelled successfully") :
        ResponseEntity.ok("Unable to cancel this Order");
  }

}
