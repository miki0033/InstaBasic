package com.instabasic.backend.common.util.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instabasic.backend.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;
  private String profilename;

  private String email;

  @JsonIgnore
  private String password;
  @JsonIgnore
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id,
      String username,
      String email,
      String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    /*
     * List<GrantedAuthority> authorities = user.getRoles().stream()
     * .map(role -> new SimpleGrantedAuthority(role.getName().name()))
     * .collect(Collectors.toList());
     */
    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  public String getProfilename() {
    return profilename;
  }

  public void setProfilename(String profilename) {
    this.profilename = profilename;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
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
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    UserDetailsImpl user = (UserDetailsImpl) o;

    return Objects.equals(id, user.id);
  }
}
