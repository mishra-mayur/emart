package com.eCommerce.emart.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = UserCart.USER_CART)
public class UserCart implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final String USER_CART = "user_cart";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String userEmail;

  private int productId;

  private int quantity;
}
