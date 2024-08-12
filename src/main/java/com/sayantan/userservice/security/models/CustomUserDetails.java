package com.sayantan.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sayantan.userservice.models.Role;
import com.sayantan.userservice.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(force = true)
@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final List<CustomGrantedAuthority> authorities;


    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPasswordHash();
        this.accountNonExpired = true;
        this.enabled = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        authorities = new ArrayList<>();
        for(Role role: user.getRoles()) {
            authorities.add(new CustomGrantedAuthority(role));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
