package com.eCommerce.emart.model.entity;

import com.eCommerce.emart.enums.OrderStatus;
import com.eCommerce.emart.util.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Mayur on 09/05/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = Order.ORDERS)
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final String ORDERS = "orders";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String userEmail;

  @Convert(converter = HashMapConverter.class)
  private Map<String, Integer> productQuantityMap;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
}
