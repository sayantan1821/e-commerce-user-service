package com.sayantan.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sayantan.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private final String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
