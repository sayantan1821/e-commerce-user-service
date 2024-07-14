package com.sayantan.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;
    @ManyToOne
    private User user;
    private Date expiresAt;

    public Token(String value, User user, Date expiresAt) {
        this.value = value;
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public Token() {

    }
}
