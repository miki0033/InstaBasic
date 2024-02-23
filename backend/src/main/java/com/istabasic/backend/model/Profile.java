package com.istabasic.backend.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Nonnull
    private String profilename;

    private String firstName;
    private String lastName;

    private LocalDate birthday;
    private String bio;

    private String avatarUrl;

    @ManyToOne
    private User userId;

    // Comment

    @OneToMany(mappedBy = "profile")
    private Set<Comment> comments;

    // FOLLOW

    @OneToMany(mappedBy = "profile")
    private Set<Follow> followers;

    @OneToMany(mappedBy = "follower")
    private Set<Follow> following;

    // Constructor
    public Profile(Long id) {
        this.id = id;
    }
}
