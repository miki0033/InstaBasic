package com.istabasic.backend.model;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Nonnull
    private String username;
    @Column(unique = true)
    @Nonnull
    private String email;
    private String password;

    @CreatedDate
    private Date createdAt;

    private Date updatedAt;
    private Date lastLoginAt;
    private Date lastOnlineAt;

}