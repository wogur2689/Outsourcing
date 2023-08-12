package com.example.project.config.auth;

import com.example.project.domain.User;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements OAuth2User {
    private User user;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getName() {
        return "name";
    }

    public User getUser() {
        return this.user;
    }

    public String toString() {
        User var10000 = this.getUser();
        return "PrincipalDetails(user=" + var10000 + ", attributes=" + this.getAttributes() + ")";
    }
}
