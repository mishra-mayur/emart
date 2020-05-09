package com.eCommerce.emart.controller;

import com.eCommerce.emart.model.request.AuthenticationRequest;
import com.eCommerce.emart.model.response.AuthenticationResponse;
import com.eCommerce.emart.service.UserService;
import com.eCommerce.emart.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mayur on 09/05/20.
 */
@RestController
public class HelloController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("/hello")
  public String hello() {
    return "Hello";
  }

  @PostMapping(value = "/authenticate")
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
}
