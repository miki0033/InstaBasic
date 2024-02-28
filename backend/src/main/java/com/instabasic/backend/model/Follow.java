package com.instabasic.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "follower")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Profile followed;

    @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Profile follower;

    @CreatedDate
    private LocalDateTime createdAt;
}
