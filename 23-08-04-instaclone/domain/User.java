package com.goorm.mungge.instagram.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String email;
    private String phone;
    private String password;
    private String nickname;
    private String profileIntro;
    private String profilePhotoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
}

