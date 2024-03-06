package com.instabasic.backend.payload.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  // private List<String> roles;

  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private String avatarUrl;

  public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
    // this.roles = roles;
  }

  public JwtResponse(String token, Long profileid, String username, String email,
      String firstName, String lastName, LocalDate birthday, String avatarUrl) {
    this.token = token;
    this.id = profileid;
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.avatarUrl = avatarUrl;
  }

}
