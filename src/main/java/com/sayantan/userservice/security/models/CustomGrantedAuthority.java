package com.sayantan.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sayantan.userservice.models.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor(force = true)
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
