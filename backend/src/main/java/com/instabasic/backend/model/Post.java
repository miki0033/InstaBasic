package com.instabasic.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    // forse va in @ManyToOne
    private ArrayList<String> imageUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany
    private Set<Profile> likes;

    private String type; // vedere se farlo con l'enum

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // Comment
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

}
