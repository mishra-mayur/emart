package com.eCommerce.emart.controller;

import com.eCommerce.emart.model.entity.User;
import com.eCommerce.emart.model.request.AuthenticationRequest;
import com.eCommerce.emart.model.response.AuthenticationResponse;
import com.eCommerce.emart.service.UserOtpService;
import com.eCommerce.emart.serviceImpl.UserService;
import com.eCommerce.emart.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by Mayur on 09/05/20.
 */
@RestController
@Slf4j
@RequestMapping(value = UserController.USER)
public class UserController {

  public static final String USER = "/public/user";
  private static final String AUTHENTICATE = "/authenticate";
  private static final String VERIFY_OTP = "/verifyOtp";
  private static final String SIGN_UP = "/signUp";
  private static final String DEACTIVATE = "/deactivate";

  private AuthenticationManager authenticationManager;

  private UserService userService;

  private UserOtpService userOtpService;

  private JwtUtil jwtUtil;

  @Autowired
  public UserController(AuthenticationManager authenticationManager, UserService userService,
      UserOtpService userOtpService, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.userOtpService = userOtpService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping(value = AUTHENTICATE)
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(),
              authenticationRequest.getPassword()));
    } catch (AuthenticationException e) {
      throw new Exception("Incorrect Username and password : ", e);
    }
    final UserDetails userDetails =
        userService.loadUserByUsername(authenticationRequest.getUserEmail());
    final String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @GetMapping(value = VERIFY_OTP)
  public ResponseEntity<?> verifyOtp(@RequestParam("email") String email,
      @RequestParam("otp") String otp) {
    log.info("request for checking otp verification");
    if (userOtpService.getOtpByEmail(email).getOtp().equals(otp)) {
      userService.activateUser(email);
      return ResponseEntity.ok("Successfully activated the acount");
    } else
      return ResponseEntity.ok("You have entered wrong OTP please try again");
  }

  @PostMapping(value = SIGN_UP)
  public ResponseEntity<?> signUp(@RequestBody AuthenticationRequest authenticationRequest) {
    User user = userService
        .saveUser(authenticationRequest.getUserEmail(), authenticationRequest.getPassword());
    if (Objects.nonNull(user)) {
      return ResponseEntity.ok("User registration processed check your mail to verify ");
    }
    return ResponseEntity.ok("Unable to register please retry !!!");
  }

  @PostMapping(value = DEACTIVATE)
  public ResponseEntity<?> deactivate(@RequestBody AuthenticationRequest authenticationRequest) {
    if (userService.loadUserByUsername(authenticationRequest.getUserEmail()).getPassword()
        .equals(authenticationRequest.getPassword())) {
      userService.deactivateUserAccount(authenticationRequest.getUserEmail());
      return ResponseEntity.ok("User account deactivated successfully");
    }
    return ResponseEntity.ok("Unable to deactivate please retry !!!");
  }
}
