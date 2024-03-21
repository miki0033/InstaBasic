package com.instabasic.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

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
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    @Nonnull
    private String username;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    @Nonnull
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    private String password;

    /*
     * @ManyToMany(fetch = FetchType.LAZY)
     * 
     * @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
     * inverseJoinColumns = @JoinColumn(name = "role_id"))
     * private Set<Role> roles = new HashSet<>();
     */

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lastOnlineAt;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.lastLoginAt = LocalDateTime.now();
        this.lastOnlineAt = LocalDateTime.now();
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        // this.roles.add(role);
    }

    public JsonNode toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("id", this.id);
            jsonNode.put("username", this.username);
            jsonNode.put("email", this.email);
            jsonNode.put("createdAt", objectMapper.valueToTree(this.createdAt));
            jsonNode.put("updatedAt", objectMapper.valueToTree(this.updatedAt));
            jsonNode.put("lastLoginAt", objectMapper.valueToTree(this.lastLoginAt));
            jsonNode.put("lastOnlineAt", objectMapper.valueToTree(this.lastOnlineAt));
            return jsonNode;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return objectMapper.createObjectNode();
        }
    }

}