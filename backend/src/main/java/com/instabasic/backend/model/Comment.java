package com.instabasic.backend.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.instabasic.backend.model.project.CommentProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany
    private Set<Profile> likes;

    @JsonIgnore
    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonIgnore
    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String text) {
        this.text = text;
    }

    public Comment(CommentProject project) {
        this.text = project.getText();
    }

    public int countLikes() {
        try {
            return likes.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            // Converti l'oggetto in una stringa JSON
            String json = objectMapper.writeValueAsString(this);
            JsonNode node = objectMapper.readTree(json);
            ((ObjectNode) node).put("likes", countLikes());
            // ((ObjectNode) node).put("profile", this.profile.getId());
            // Restituisci il JSON modificato
            return objectMapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public JsonNode toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("id", this.id);
            jsonNode.put("text", this.text);
            jsonNode.put("createdAt", objectMapper.valueToTree(this.createdAt));
            jsonNode.put("likes", this.countLikes());
            jsonNode.put("profile", this.profile.getId());
            jsonNode.put("post", this.post.getId());
            return jsonNode;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return objectMapper.createObjectNode();
        }
    }

}