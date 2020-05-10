package com.eCommerce.emart.serviceImpl;

import com.eCommerce.emart.enums.OrderStatus;
import com.eCommerce.emart.model.entity.Order;
import com.eCommerce.emart.repository.OrderRepository;
import com.eCommerce.emart.service.OrderService;
import com.eCommerce.emart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  private ProductService productService;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
    this.orderRepository = orderRepository;
    this.productService = productService;
  }

  @Override
  @Transactional
  public List<Order> getAllOrders(String userEmail) {
    List<Order> orders = null;
    try {
      orders = orderRepository.findByUserEmail(userEmail);
    } catch (Exception e) {
      log.error("Error in fetching Order Details for email Id : {}", userEmail);
    }
    return orders;
  }

  @Override
  @Transactional
  public boolean confirmOrder(Order order) {
    AtomicBoolean updated = new AtomicBoolean(false);
    order.getProductQuantityMap().forEach((product, quantity) -> {
      synchronized (product) {//synchronized for each product to avoid multiple order of same item
        int stock = 0;
        try {
          stock = productService.getProductByName(product).getStock();
        } catch (Exception e) {
          log.error("Error in fetching stock for product : {}", product);
        }
        if (stock < quantity) {
          log.error("Quantity ordered exceeds the goods stock. Product Name : {}", product);
          throw new RuntimeException(
              "Quantity ordered exceeds the goods stock. Terminating transaction !");
        }
        try {
          productService.updateProductStock(product, -quantity);
          updated.set(true);
        } catch (Exception e) {
          log.error("Error in updating stock for product : {}", product);
        }
      }
    });
    if (updated.get()) {
      orderRepository.save(order);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public boolean cancelOrder(int id) {
    //increase product stock
    Order order = orderRepository.findById(id);
    if (Objects.nonNull(order)) {
      try {
        order.getProductQuantityMap().forEach((product,quantity) -> {
          productService.updateProductStock(product,quantity);
        });
        orderRepository.updateOrderStatus(id, OrderStatus.CANCELLED);
        return true;
      } catch (Exception e) {
        log.error("Error in cancelling order for Order ID : {}",id);
      }
    }
    return false;
  }
}
