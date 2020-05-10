package com.eCommerce.emart.repository;

import com.eCommerce.emart.enums.OrderStatus;
import com.eCommerce.emart.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mayur on 09/05/20.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  String ID = "id";
  String CANCELLED = "cancelled";

  List<Order> findByUserEmail(String userEmail);
  Order findById(int orderId);

  @Modifying
  @Query("UPDATE Order O SET O.orderStatus=:cancelled WHERE O.id=:id")
  void updateOrderStatus(@Param(ID) int id,@Param(CANCELLED) OrderStatus cancelled);
}
