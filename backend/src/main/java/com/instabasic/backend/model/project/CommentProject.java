package com.instabasic.backend.model.project;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class CommentProject {
    String text;
    @NonNull
    Long profile;
    @NonNull
    Long post;
}
