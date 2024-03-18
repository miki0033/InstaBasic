package com.instabasic.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
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

    public Follow(Profile followed, Profile follower) {
        this.followed = followed;
        this.follower = follower;
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followed=" + followed.getId() +
                ", follower=" + follower.getId() +
                ", createdAt=" + createdAt +
                '}';
    }

    public String toJSON() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("followed", this.followed.getProfilename());
            jsonNode.put("follower", this.follower.getProfilename());
            jsonNode.put("createdAt", objectMapper.valueToTree(this.createdAt));
            return objectMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Ritorna un JSON vuoto in caso di errore
        }
    }
}
