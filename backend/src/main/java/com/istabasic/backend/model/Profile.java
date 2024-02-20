package com.istabasic.backend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    private Date birthday;
    private String bio;

    private String avatarUrl;

    @OneToMany
    private User userId;

    // Comment
    @OneToMany(mappedBy = "profile")
    private Set<Comment> comments = new HashSet<>();

    // FOLLOW
    @ManyToMany
    @JoinTable(name = "follower", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<Profile> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<Profile> following = new HashSet<>();

}
