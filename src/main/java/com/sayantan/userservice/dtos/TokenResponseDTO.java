package com.sayantan.userservice.dtos;

import com.sayantan.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenResponseDTO {
    private String token;
    private Date expiresAt;
    private UserDTO user;
    private Date createdAt;
    private Date updatedAt;

    public TokenResponseDTO(String value, UserDTO user, Date expiresAt, Date createdAt, Date updatedAt) {
        this.token = value;
        this.user = user;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TokenResponseDTO form(Token token) {
        return new TokenResponseDTO(token.getValue(), UserDTO.form(token.getUser()), token.getExpiresAt(), token.getCreatedAt(), token.getUpdatedAt());
    }
}
