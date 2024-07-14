package com.sayantan.userservice.dtos;

import com.sayantan.userservice.models.Role;
import com.sayantan.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class UserDTO {
    public UserDTO(String name, String email, boolean verified, List<Role> roles, Date createdAt, Date updatedAt) {
        this.name = name;
        this.email = email;
        this.verified = verified;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private String name;
    private String email;
    private boolean verified;
    private List<Role> roles;
    private Date createdAt;
    private Date updatedAt;

    public static UserDTO form(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.isVerified(), user.getRoles(), user.getCreatedAt(), user.getUpdatedAt());
    }

}
