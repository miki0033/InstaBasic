package com.instabasic.backend.model;
<<<<<<< HEAD

import com.instabasic.backend.model.interface_enum.ERole;

=======

import com.instabasic.backend.model.enum.ERole;
>>>>>>> 17db6e6b085ece59beb06f2326b00f59cc2fff53
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;
}