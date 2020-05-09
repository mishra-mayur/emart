package com.eCommerce.emart.service;

import com.eCommerce.emart.model.entity.User;
import com.eCommerce.emart.model.vo.MyUserDetails;
import com.eCommerce.emart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserEmail(s);
    user.orElseThrow(() -> new UsernameNotFoundException("No user found with name : {}" + s));
    MyUserDetails userDetails = new MyUserDetails(
        new org.springframework.security.core.userdetails.User(user.get().getUserEmail(),
            user.get().getPassword(), user.get().isActive(), true, true, true, new ArrayList<>()));
    return userDetails;
  }
}
