package com.instabasic.backend.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

    @JsonIgnore
    @ManyToOne
    private User user;

    // Comment
    @JsonIgnore
    @OneToMany(mappedBy = "profile")
    private Set<Comment> comments;

    // FOLLOW
    @JsonIgnore
    @OneToMany(mappedBy = "followed")
    private Set<Follow> followers;
    @JsonIgnore
    @OneToMany(mappedBy = "follower")
    private Set<Follow> following;

    // Constructor
    public Profile(Long id) {
        this.id = id;
    }

    public Profile(String profilename, String firstName, String lastName, LocalDate birthday, User user) {
        this.profilename = profilename;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.user = user;
    }

    public Profile(String profilename, String firstName, String lastName, LocalDate birthday, String bio, User user) {
        this.profilename = profilename;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.bio = bio;
        this.user = user;
    }

    public static <T> int count(Collection<? extends T> collection) {
        try {
            return collection.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public JsonNode toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            String json = objectMapper.writeValueAsString(this);
            JsonNode node = objectMapper.readTree(json);
            ((ObjectNode) node).put("birthday", objectMapper.valueToTree(this.birthday));
            ((ObjectNode) node).put("comments", count(this.comments));
            ((ObjectNode) node).put("followers", count(this.followers));
            ((ObjectNode) node).put("following", count(this.following));
            return node;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return objectMapper.createObjectNode();
        }
    }
}
