package com.eCommerce.emart.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mayur on 09/05/20.
 */
@Getter
@Setter
@ToString
public class MyUserDetails implements UserDetails {
  private String userEmail;
  private String password;
  private boolean active;

  public MyUserDetails(User user) {
    this.userEmail = user.getUsername();
    this.password = user.getPassword();
    this.active = user.isEnabled();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public String getUsername() {
    return this.userEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }
}
