package com.instabasic.backend.payload.response;

import org.springframework.security.core.userdetails.UserDetails;

import com.instabasic.backend.model.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

  private String token;
  private String type = "Bearer";

  private UserDetails user;
  private Profile profile;

  public JwtResponse(String token, UserDetails user, Profile profile) {
    this.token = token;
    this.user = user;
    this.profile = profile;

  }

}
