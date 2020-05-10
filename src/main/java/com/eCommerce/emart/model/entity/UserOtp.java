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
@Table(name = UserOtp.USER_OTP)
public class UserOtp implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final String USER_OTP = "user_otp";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String userEmail;

  private String otp;
}
