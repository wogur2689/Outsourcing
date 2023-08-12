package com.example.project.config.auth;

import com.example.project.domain.User;

public class UserProfile {
    private String name;
    private String email;
    private String provider;
    private String providerId;

    public UserProfile() {
    }

    public User toUser() {
        return User.builder().name(this.name).email(this.email).provider(this.provider).build();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }
}
