package com.instabasic.backend.model.project;

import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileProject {
    @NonNull
    private String profilename;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String bio;
    private String avatarUrl;
    @NonNull
    private Long user;
}
