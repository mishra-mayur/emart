package com.eCommerce.emart.serviceImpl;

import com.eCommerce.emart.model.entity.User;
import com.eCommerce.emart.model.vo.MyUserDetails;
import com.eCommerce.emart.repository.UserRepository;
import com.eCommerce.emart.service.MailSenderService;
import com.eCommerce.emart.service.UserOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Mayur on 09/05/20.
 */
@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;

  private MailSenderService mailSenderService;

  private UserOtpService userOtpService;

  @Autowired
  public UserService(UserRepository userRepository, MailSenderService mailSenderService,
      UserOtpService userOtpService) {
    this.userRepository = userRepository;
    this.mailSenderService = mailSenderService;
    this.userOtpService = userOtpService;
  }

  Random random = new Random();

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserEmail(s);
    user.orElseThrow(() -> new UsernameNotFoundException("No user found with name : {}" + s));
    MyUserDetails userDetails = new MyUserDetails(
        new org.springframework.security.core.userdetails.User(user.get().getUserEmail(),
            user.get().getPassword(), user.get().isActive(), true, true, true, new ArrayList<>()));
    return userDetails;
  }

  @Transactional
  public User saveUser(String userEmail, String password) {
    String otp = String.format("%04d", random.nextInt(10000));
    userOtpService.saveOtpAndUser(otp,userEmail);
    mailSenderService.sendMail(userEmail,otp);
    return userRepository
        .save(User.builder().active(false).password(password).userEmail(userEmail).build());
  }

  @Transactional
  public void activateUser(String email) {
    userRepository.activateUser(email);
  }

  @Transactional
  public void deactivateUserAccount(String userEmail) {
    userRepository.deactivateUserAccount(userEmail);
  }
}
